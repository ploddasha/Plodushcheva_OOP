package ru.nsu.plodushcheva;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.plodushcheva.controller.SnakeGameController;
import ru.nsu.plodushcheva.model.CollisionManager;
import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.model.MovementManager;
import ru.nsu.plodushcheva.view.GameField;
import ru.nsu.plodushcheva.model.Walls;
import ru.nsu.plodushcheva.model.json.JsonData;
import ru.nsu.plodushcheva.controller.json.JsonParser;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeFood;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.model.snakes.Snake;
import ru.nsu.plodushcheva.view.Graphics;
import ru.nsu.plodushcheva.view.SnakeGameView;
import java.awt.Point;


public class SnakeGame extends Application {

    static JsonData jsonData = new JsonParser().getData("info.json");
    private static final int WIDTH = jsonData.getWidth();

    private static final int HEIGHT = jsonData.getHeight();
    private static final int COLUMNS = jsonData.getColumns();
    private static final int ROWS = jsonData.getRows();
    private static final int SQUARE_SIZE = jsonData.getSquareSize();
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

    Walls walls  = new Walls();
    private int score = 0;
    private GameField gameField;
    private Food food;

    int MAX_FOOD = 10;
    int Speed = 130;
    Snake snake;
    private Integer gameLevel = 2;

    EnemySnakeRandom enemySnakeRandom;
    EnemySnakeFood enemySnakeFood;

    SnakeGameController snakeGameController = new SnakeGameController();
    SnakeGameView snakeGameView = new SnakeGameView();


    @Override
    public void start(Stage primaryStage) {

        Label level;
        final Spinner<Integer> spinner = new Spinner<>();
        final int initialValue = 20;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 50, initialValue);
        spinner.setValueFactory(valueFactory);

        Button buttonLevel = new Button();
        Button buttonLevel2 = new Button();
        Button buttonLevel3 = new Button();
        level = snakeGameController.handleLevelButton(buttonLevel, buttonLevel2, buttonLevel3);

        Button button = new Button();
        button.setText("Start game");

        button.setLayoutX(30);
        button.setLayoutY(50);

        Label finalLevel = level;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String currentLevelText = finalLevel.getText();
                switch (currentLevelText) {
                    case "You chose level 1" -> gameLevel = 1;
                    case "You chose level 2" -> gameLevel = 2;
                    case "You chose level 3" -> gameLevel = 3;
                }

                if (gameLevel == 1) {
                    MAX_FOOD = jsonData.getMaxFoodForLevel1();
                    Speed = jsonData.getSpeedFoodForLevel1();
                    walls.addWalls(jsonData.getWallsFoodForLevel1());
                }else if (gameLevel == 2){
                    MAX_FOOD = jsonData.getMaxFoodForLevel2();
                    Speed = jsonData.getSpeedFoodForLevel2();
                    walls.addWalls(jsonData.getWallsFoodForLevel2());
                } else if (gameLevel == 3){
                    MAX_FOOD = jsonData.getMaxFoodForLevel3();
                    Speed = jsonData.getSpeedFoodForLevel3();
                    walls.addWalls(jsonData.getWallsFoodForLevel3());
                }
                SCORE_FOR_WIN = spinner.getValue();


                gameField = new GameField(WIDTH, HEIGHT, COLUMNS, ROWS, SQUARE_SIZE);
                food = new Food(gameField, MAX_FOOD);
                snake = new Snake(gameField, food, walls);
                graphics = new Graphics(gameField, WIDTH, HEIGHT, COLUMNS, ROWS);

                food.addSnake(snake);

                Scene scene = snakeGameView.createScene(WIDTH,HEIGHT);

                primaryStage.setScene(scene);
                primaryStage.setX(300);
                primaryStage.setY(20);
                primaryStage.show();

                gc = snakeGameView.getGraphicsContext();
                gcInfo = snakeGameView.getInfoGraphicsContext();

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        KeyCode code = event.getCode();
                        direction = snakeGameController.handleKeyPress(code, direction);
                    }
                });

                snake.gameOver();

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(Speed), e -> run(gc, gcInfo)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();

                enemySnakeRandom = new EnemySnakeRandom(gameField, food, walls);
                enemySnakeFood = new EnemySnakeFood(gameField, food, walls);

                food.addEnemySnakeFood(enemySnakeFood);
                food.addEnemySnakeRandom(enemySnakeRandom);

                food.generateFood(walls);
            }
        });

        Scene menuScene = snakeGameView.createMenuScene(spinner, level, button, buttonLevel, buttonLevel2, buttonLevel3);

        primaryStage.setTitle("Snake game menu");
        primaryStage.setScene(menuScene);
        primaryStage.show();

    }

    private void run(GraphicsContext gc, GraphicsContext gcInfo) {
        CollisionManager collisionManager = new CollisionManager();
        collisionManager.collision(snake, enemySnakeFood, enemySnakeRandom);

        if (enemySnakeFood.getScore() == SCORE_FOR_WIN
                || enemySnakeRandom.getScore() == SCORE_FOR_WIN) {
            snake.setGameOver();
        }
        if (snake.isGameOver()) {
            graphics.drawGameOver(gc);
            return;
        }
        if (score == SCORE_FOR_WIN) {
            graphics.drawGameWon(gc);
            return;
        }
        graphics.drawBackground(gc);
        graphics.drawWalls(gc, walls.getWalls());
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

        MovementManager movementManager = new MovementManager();
        movementManager.updateSnakePosition(snake);
        if (direction != null) {
            movementManager.moveSnake(snake, direction);
        }

        snake.gameOver();

        if (!snake.isGameOver()) {
            movementManager.moveEnemySnakes(enemySnakeRandom, enemySnakeFood);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}