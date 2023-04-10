package com.example.task231;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int COLUMNS = 40;
    private static final int ROWS = 40;
    private static final int SQUARE_SIZE = WIDTH/ROWS;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private List<Light.Point> snakeBody = new ArrayList();

    private Light.Point snakeHead;


    @Override
    public void start(Stage primaryStage) throws IOException {


        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        /*
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                    }
                }
            }
        });
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0); */

        drawBackground(gc);
        drawWalls(gc);

    }

    private void drawWalls(GraphicsContext gc) {
        gc.setFill(Color.web("394032"));
        gc.fillRect(10 * SQUARE_SIZE, 3 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.fillRect(10 * SQUARE_SIZE, 4 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.fillRect(10 * SQUARE_SIZE, 5 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

        gc.fillRect(30 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.fillRect(31 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.fillRect(32 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.fillRect(33 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);


    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("9CB690"));
                } else {
                    gc.setFill(Color.web("8DAB7F"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("CFEE9E"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}