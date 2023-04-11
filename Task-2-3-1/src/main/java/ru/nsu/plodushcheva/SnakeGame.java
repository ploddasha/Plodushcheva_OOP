package ru.nsu.plodushcheva;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;


public class SnakeGame extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int COLUMNS = 30;
    private static final int ROWS = 30;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    private Direction direction;

    private GraphicsContext gc;
    private List<Point> snakeBody = new ArrayList<>();

    Walls walls;
    private int currentDirection;
    private Point snakeHead;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int score = 0;





    @Override
    public void start(Stage primaryStage) throws IOException {


        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                }
            }
        });
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, i + ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        walls = new Walls(gc, SQUARE_SIZE);



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        EnemySnake enemySnake = new EnemySnake(ROWS, COLUMNS, SQUARE_SIZE, gc, walls);
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(130), e -> enemySnake.run()));
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();

        /*
        EnemySnake enemySnake2 = new EnemySnake(ROWS, COLUMNS, SQUARE_SIZE, gc, walls);
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(130), e -> enemySnake2.run()));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play(); */

    }

    private void run(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
            return;
        }
        drawBackground(gc);
        walls.drawWalls();
        drawSnake(gc);
        drawFood(gc);

        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

        switch (direction) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        gameOver();
        eatFood();

    }



    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("FDE74C"));
                } else {
                    gc.setFill(Color.web("FDEC68"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("9BC53D"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE,
                snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE,
                    snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }

            for (int i=0; i < walls.getWalls().size(); i++) {
                if (walls.getWalls().get(i).getX() == foodX &&
                        walls.getWalls().get(i).getY() == foodY) {
                    continue start;

                }
            }

            break;
        }
    }

    private void drawFood(GraphicsContext gc) {
        gc.setFill(Color.web("E55934"));
        gc.fillRect(foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }


    public void gameOver() {

        for (int i = 0; i < walls.getWalls().size(); i++) {
            if (snakeHead.getX() == walls.getWalls().get(i).getX() &&
                    snakeHead.getY() == walls.getWalls().get(i).getY() ) {
                gameOver = true;
            }
        }

        //destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }
    private void moveRight() {
        if (snakeHead.getX() == COLUMNS) {
            snakeHead.x = 0;
        } else {
            snakeHead.x++;
        }
    }

    private void moveLeft() {
        if (snakeHead.getX() == 0) {
            snakeHead.x = COLUMNS;
        } else {
            snakeHead.x--;
        }
    }

    private void moveUp() {
        if (snakeHead.getY() == 0) {
            snakeHead.y = ROWS;
        } else {
            snakeHead.y--;
        }
    }

    private void moveDown() {
        if (snakeHead.getY() == ROWS) {
            snakeHead.y = 0;
        } else {
            snakeHead.y++;
        }
    }

    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            score++;
            generateFood();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
    -> при нажатии в дургом направлении на границах, змейка уходит в заграничное пространство
    сделать checkPosition и проверять голову

    добавление стены

    -> враг проходит сквозь стены, не ест еду, не убивает гг
    пусть ест еду
    пусть проигрывает если гг его ест
    пусть гг проигрывает если враг его ест

    -> враг ратсет после проигрыша
 */