package ru.nsu.plodushcheva;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.plodushcheva.environment.Food;
import ru.nsu.plodushcheva.environment.GameField;
import ru.nsu.plodushcheva.environment.Walls;
import ru.nsu.plodushcheva.snakes.EnemySnakeFood;
import ru.nsu.plodushcheva.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.snakes.Snake;


import java.awt.Point;
import java.util.Objects;


public class SnakeGame extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int COLUMNS = 30;
    private static final int ROWS = 30;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    private int SCORE_FOR_WIN = 20;
    private Graphics graphics;

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    private Direction direction;

    private GraphicsContext gc;
    private GraphicsContext gcInfo;

    Walls walls;
    private int score = 0;
    private GameField gameField;
    private Food food;

    int MAX_FOOD = 10;
    int Speed = 130;
    Snake snake;
    int gameLevel = 2;

    EnemySnakeRandom enemySnakeRandom;
    EnemySnakeFood enemySnakeFood;



    @Override
    public void start(Stage primaryStage) {

        Label level = new Label("Choose your level");

        Label chosenScore = new Label("Choose your desire score");
        final Spinner<Integer> spinner = new Spinner<>();
        final int initialValue = 20;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 50, initialValue);

        spinner.setValueFactory(valueFactory);

        Button buttonLevel = new Button();
        buttonLevel.setText("Choose level 1");
        buttonLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameLevel = 1;
                level.setText("You chose level 1");

            }
        });

        Button buttonLevel2 = new Button();
        buttonLevel2.setText("Choose level 2");
        buttonLevel2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameLevel = 2;
                level.setText("You chose level 2");
            }
        });

        Button buttonLevel3 = new Button();
        buttonLevel3.setText("Choose level 3");
        buttonLevel3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameLevel = 3;
                level.setText("You chose level 3");

            }
        });

        Button button = new Button();
        button.setText("Start game");

        button.setLayoutX(30);
        button.setLayoutY(50);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (gameLevel == 1) {
                    MAX_FOOD = 30;
                    Speed = 200;
                }else if (gameLevel == 2){
                    MAX_FOOD = 20;
                    Speed = 150;
                } else if (gameLevel == 3){
                    MAX_FOOD = 10;
                    Speed = 100;
                }
                SCORE_FOR_WIN = spinner.getValue();


                gameField = new GameField(WIDTH, HEIGHT, COLUMNS, ROWS, SQUARE_SIZE);
                food = new Food(gameField, MAX_FOOD);
                walls = new Walls();
                snake = new Snake(gameField, food, walls);
                graphics = new Graphics(gameField, WIDTH, HEIGHT, COLUMNS, ROWS);

                food.addSnake(snake);

                HBox root = new HBox();

                Canvas canvas = new Canvas(WIDTH, HEIGHT);
                Canvas info = new Canvas(100, HEIGHT);


                root.getChildren().add(info);
                root.getChildren().add(canvas);

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                gc = canvas.getGraphicsContext2D();
                gcInfo = info.getGraphicsContext2D();


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

                if (gameLevel == 1) {
                    walls.addWallsForLevelOne();
                }else if (gameLevel == 2){
                    walls.addWallsForLevelTwo();
                } else if (gameLevel == 3){
                    walls.addWallsForLevelThree();
                }
                //walls.addWalls();
                snake.gameOver();

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(Speed), e -> run(gc, gcInfo)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();


                enemySnakeRandom = new EnemySnakeRandom( gameField, food, walls);
                enemySnakeFood = new EnemySnakeFood(gameField, food, walls);

                food.addEnemySnakeFood(enemySnakeFood);
                food.addEnemySnakeRandom(enemySnakeRandom);

                food.generateFood(walls);


            }
        });


        VBox vbox = new VBox();

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

        Scene scene = new Scene(vbox, 450, 250);
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm());


        primaryStage.setTitle("Snake game menu");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private void run(GraphicsContext gc, GraphicsContext gcInfo) {
        collision();
        if (enemySnakeFood.getScore() == SCORE_FOR_WIN
                || enemySnakeRandom.getScore() == SCORE_FOR_WIN) {
            snake.setGameOver();
        }
        if (snake.isGameOver()) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5,  HEIGHT / 2);
            return;
        }
        if (score == SCORE_FOR_WIN) {
            gc.setFill(Color.GREEN);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("You won", WIDTH / 3.5, HEIGHT / 2);
            return;
        }
        graphics.drawBackground(gc);
        graphics.drawWalls(gc, walls.getWalls2());
        graphics.drawSnake(gc, snake.getSnake());

        snake.eatFood();
        graphics.drawFood(gc, food.getFood());

        score = snake.getScore();
        int scoreEnemyFood = enemySnakeFood.getScore();
        int scoreEnemyRandom = enemySnakeRandom.getScore();
        graphics.drawScore(gcInfo, score, scoreEnemyFood, scoreEnemyRandom, SCORE_FOR_WIN);
        graphics.drawLevel(gcInfo, gameLevel);

        if (!snake.isGameOver()) {
            enemySnakeRandom.drawSnake(gc);
            enemySnakeFood.drawSnake(gc);
        }

        if (snake.getSnake().size() > 1) {
            Point crawling = snake.getSnake().get(snake.getSnake().size() - 1);
            crawling.x = snake.getSnakeHead().x;
            crawling.y = snake.getSnakeHead().y;
            snake.getSnake().add(1, crawling);
            snake.getSnake().remove(snake.getSnake().size() - 1);
        }


        if (direction != null) {
            switch (direction) {
                case RIGHT -> snake.moveRight();
                case LEFT -> snake.moveLeft();
                case UP -> snake.moveUp();
                case DOWN -> snake.moveDown();
            }
        }

        snake.gameOver();

        if (!snake.isGameOver()) {
            enemySnakeRandom.run();
            enemySnakeFood.run();
        }
    }

    public void collision() {
        for (int i = 0; i < snake.getSnake().size(); i++) {
            if (enemySnakeFood.getSnakeHead().x == snake.getSnake().get(i).x
                    && enemySnakeFood.getSnakeHead().y == snake.getSnake().get(i).y) {
                snake.setGameOver();
                break;
            }
            if (enemySnakeRandom.getSnakeHead().x == snake.getSnake().get(i).x
                    && enemySnakeRandom.getSnakeHead().y == snake.getSnake().get(i).y) {
                snake.setGameOver();
                break;
            }
        }
        for (int i = 0; i < enemySnakeRandom.getSnake().size(); i++) {
            if (snake.getSnakeHead().x == enemySnakeRandom.getSnake().get(i).x
                    && snake.getSnakeHead().y == enemySnakeRandom.getSnake().get(i).y) {
                snake.setScore();
                enemySnakeRandom.setGameOver();
                break;
            }
        }
        for (int i = 0; i < enemySnakeFood.getSnake().size(); i++) {
            if (snake.getSnakeHead().x == enemySnakeFood.getSnake().get(i).x
                    && snake.getSnakeHead().y == enemySnakeFood.getSnake().get(i).y) {
                snake.setScore();
                enemySnakeFood.setGameOver();
                break;
            }
        }

    }



    public static void main(String[] args) {
        launch(args);
    }
}

