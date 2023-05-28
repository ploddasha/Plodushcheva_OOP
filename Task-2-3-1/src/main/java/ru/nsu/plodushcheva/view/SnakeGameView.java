package ru.nsu.plodushcheva.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SnakeGameView {
    GraphicsContext gc;
    GraphicsContext gcInfo;

    public Scene createScene(int WIDTH, int HEIGHT) {
        HBox root = new HBox();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Canvas info = new Canvas(100, HEIGHT);

        root.getChildren().add(info);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();
        gcInfo = info.getGraphicsContext2D();

        return new Scene(root);
    }

    public Scene createMenuScene(Spinner spinner, Label level, Button button,
                                 Button buttonLevel, Button buttonLevel2, Button buttonLevel3) {
        VBox vbox = new VBox();

        Label chosenScore = new Label("Choose your desire score");

        vbox.getChildren().add(chosenScore);
        vbox.getChildren().add(spinner);
        vbox.getChildren().add(level);
        vbox.getChildren().add(buttonLevel);
        vbox.getChildren().add(buttonLevel2);
        vbox.getChildren().add(buttonLevel3);
        vbox.getChildren().add(button);

        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        String hexColor = "#9BC53D";
        Color color = Color.web(hexColor);
        vbox.setBackground(new Background(new BackgroundFill(color, null, null)));

        return new Scene(vbox, 450, 250);
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public GraphicsContext getInfoGraphicsContext() {
        return gcInfo;
    }

}
