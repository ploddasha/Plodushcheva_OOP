module ru.nsu.plodushcheva {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens ru.nsu.plodushcheva to javafx.fxml;
    exports ru.nsu.plodushcheva;
    exports ru.nsu.plodushcheva.model.snakes;
    opens ru.nsu.plodushcheva.model.snakes to javafx.fxml;
    exports ru.nsu.plodushcheva.model;
    opens ru.nsu.plodushcheva.model to javafx.fxml;
    exports ru.nsu.plodushcheva.view;
    opens ru.nsu.plodushcheva.view to javafx.fxml;
    exports ru.nsu.plodushcheva.controller;
    opens ru.nsu.plodushcheva.controller to javafx.fxml;
    exports ru.nsu.plodushcheva.model.json;
    opens ru.nsu.plodushcheva.model.json to javafx.fxml;
    exports ru.nsu.plodushcheva.controller.json;
    opens ru.nsu.plodushcheva.controller.json to javafx.fxml;
}