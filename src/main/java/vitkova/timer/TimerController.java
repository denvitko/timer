package vitkova.timer;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TimerController implements Initializable {

    @FXML
    private ComboBox<Integer> combo_hours;

    @FXML
    private ComboBox<Integer> combo_mins;

    @FXML
    private ComboBox<Integer> combo_secs;

    @FXML
    private Button btn_start;

    @FXML
    private AnchorPane settings;

    @FXML
    private AnchorPane countdown;


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

    public void startTimer() {
        slideUp();
    }

    public void stopTimer() {
        slideDown();
    }

    public void slideUp() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.millis(200));
        tt1.setToX(0);
        tt1.setToY(-216);
        tt1.setNode(settings);
        tt1.play();
    }

    public void slideDown() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.millis(200));
        tt1.setToX(0);
        tt1.setToY(0);
        tt1.setNode(settings);
        tt1.play();
    }



}