module ru.nsu.plodushcheva {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.nsu.plodushcheva to javafx.fxml;
    exports ru.nsu.plodushcheva;
    exports ru.nsu.plodushcheva.snakes;
    opens ru.nsu.plodushcheva.snakes to javafx.fxml;
    exports ru.nsu.plodushcheva.environment;
    opens ru.nsu.plodushcheva.environment to javafx.fxml;
}