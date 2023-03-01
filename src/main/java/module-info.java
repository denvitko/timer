module vitkova.timer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens vitkova.timer to javafx.fxml;
    exports vitkova.timer;
}