package ru.nsu.plodushcheva;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SnakeGameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}