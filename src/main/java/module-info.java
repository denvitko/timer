module vitkova.timer {
    requires javafx.controls;
    requires javafx.fxml;


    opens vitkova.timer to javafx.fxml;
    exports vitkova.timer;
}