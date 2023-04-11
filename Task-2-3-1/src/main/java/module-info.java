module ru.nsu.plodushcheva {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.nsu.plodushcheva to javafx.fxml;
    exports ru.nsu.plodushcheva;
}