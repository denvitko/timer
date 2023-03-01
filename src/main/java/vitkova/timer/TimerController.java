package vitkova.timer;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class TimerController implements Initializable, Runnable {

    @FXML
    private ComboBox<Integer> combo_hours;

    @FXML
    private ComboBox<Integer> combo_mins;

    @FXML
    private ComboBox<Integer> combo_secs;

    @FXML
    private Label label_hours;

    @FXML
    private Label label_mins;

    @FXML
    private Label label_secs;

    @FXML
    private AnchorPane settings;

    @FXML
    private AnchorPane countdown;

    private Thread t;
    private boolean running;
    private int seconds;
    private boolean isPaused = false;
    Object monitor = new Object();

    MediaPlayer mp;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        ObservableList<Integer> minsList = FXCollections.observableArrayList();
        ObservableList<Integer> secsList = FXCollections.observableArrayList();

        for (int i = 0; i < 24; i++) {
            hoursList.add(i);
        }
        for (int i = 0; i < 60; i++) {
            minsList.add(i);
            secsList.add(i);
        }

        combo_hours.setItems(hoursList);
        combo_hours.setValue(0);
        combo_mins.setItems(minsList);
        combo_mins.setValue(0);
        combo_secs.setItems(secsList);
        combo_secs.setValue(0);
    }

    public void startTimer(ActionEvent actionEvent) {
        slideUp();
        t = new Thread(this);
        t.setDaemon(true);
        running = true;
        getTime();
        t.start();
    }

    public void getTime() {
        seconds = combo_hours.getValue() * 3600 + combo_mins.getValue() * 60 + combo_secs.getValue();
    }

    public void stopTimer(ActionEvent actionEvent) {
        slideDown();
        running = false;
        //resumeTimer();
    }

    public void slideUp() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.millis(500));
        tt1.setToX(0);
        tt1.setToY(-216);
        tt1.setNode(settings);
        tt1.play();
    }

    public void slideDown() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.millis(500));
        tt1.setToX(0);
        tt1.setToY(0);
        tt1.setNode(settings);
        tt1.play();
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        long time = System.currentTimeMillis() + 1000;
        while(running) {
                if (System.currentTimeMillis() >= time && !isPaused) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTime();
                        }
                    });
                    if (seconds <= 0) {
                        running = false;
                        Media bell = new Media(getClass().getResource("bell.wav").toString());
                        mp = new MediaPlayer(bell);
                        mp.play();
                    } else {
                        seconds--;
                    }
                    time = System.currentTimeMillis() + 1000;
                }
        }
    }

    private void updateTime() {
        short hours = (short) (seconds / 3600);
        label_hours.setText(String.format("%02d", hours));
        short mins = (short) ((seconds % 3600) / 60);
        label_mins.setText(String.format("%02d", mins));
        short secs = (short) (seconds % 60);
        label_secs.setText(String.format("%02d", secs));
    }

    public void pauseTimer(ActionEvent actionEvent) {
        isPaused = !isPaused;
    }

    public void resetTimer(ActionEvent actionEvent) {
        resumeTimer();
        getTime();
    }

    public void resumeTimer() {
        synchronized(monitor) {
            monitor.notify();
            isPaused = false;
        }
    }
}