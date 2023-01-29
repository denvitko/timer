package vitkova.timer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    @FXML
    private ComboBox<Integer> combo_hours;

    @FXML
    private ComboBox<Integer> combo_mins;

    @FXML
    private ComboBox<Integer> combo_secs;


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}