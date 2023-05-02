package ru.nsu.plodushcheva;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class SnakeGame extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int COLUMNS = 30;
    private static final int ROWS = 30;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    private static int SCORE_FOR_WIN = 20;
    private Graphics graphics;

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    private Direction direction;

    private GraphicsContext gc;
    //private List<Point> snakeBody = new ArrayList<>();

    Walls walls;
    private int currentDirection;
    //private Point snakeHead;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int score = 0;
    private GameField gameField;
    private Food food;

    int MAX_FOOD = 10;
    int Speed = 130;
    Snake snake;





    @Override
    public void start(Stage primaryStage) throws IOException {

        // --------------
        Label level = new Label("Choose you level");

        //TextField scoreDesire = new TextField("initial text");
        Label chosenScore = new Label("Choose your desire score");
        final Spinner<Integer> spinner = new Spinner<Integer>();
        final int initialValue = 20;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 50, initialValue);

        spinner.setValueFactory(valueFactory);
        
/*
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                chosenScore.setText(scoreDesire.getText());
            }
        };
        scoreDesire.setOnAction(event);
*/


        Button buttonLevel = new Button();
        buttonLevel.setText("Choose level 1");
        buttonLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MAX_FOOD = 30;
                Speed = 200;
                level.setText("You chose level 1");

            }
        });

        Button buttonLevel2 = new Button();
        buttonLevel2.setText("Choose level 2");
        buttonLevel2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MAX_FOOD = 20;
                Speed = 150;
                level.setText("You chose level 2");


            }
        });

        Button buttonLevel3 = new Button();
        buttonLevel3.setText("Choose level 3");
        buttonLevel3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MAX_FOOD = 10;
                Speed = 100;
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

                gameField = new GameField(600, 600, COLUMNS, ROWS, SQUARE_SIZE);
                food = new Food(gameField, MAX_FOOD);
                walls = new Walls(gameField, 5);
                snake = new Snake(gameField, food, walls);
                graphics = new Graphics(gameField);

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



                walls.addWalls();
                food.generateFood(walls, snake);
                snake.gameOver();

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(Speed), e -> run(gc)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();


                EnemySnakeTwo enemySnakeTwo = new EnemySnakeTwo( gameField, food, walls);
                Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(Speed), e -> enemySnakeTwo.run(gc)));
                timeline2.setCycleCount(Animation.INDEFINITE);
                timeline2.play();
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

        Scene scene = new Scene(vbox, 450, 250);

        primaryStage.setTitle("Snake game menu");
        primaryStage.setScene(scene);
        primaryStage.show();


        // --------------



        /*
        EnemySnake enemySnake = new EnemySnake(ROWS, COLUMNS, SQUARE_SIZE, gc, walls);
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(130), e -> enemySnake.run(gc)));
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();
        */
        
        /*
        EnemySnake enemySnake2 = new EnemySnake(ROWS, COLUMNS, SQUARE_SIZE, gc, walls);
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(130), e -> enemySnake2.run()));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play(); */

    }

    private void run(GraphicsContext gc) {
        if (snake.isGameOver()) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
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
        graphics.drawScore(gc, score, SCORE_FOR_WIN);

        //drawBackground(gc);
        //walls.drawWalls();
        //drawSnake(gc, );
        //drawFood(gc);
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