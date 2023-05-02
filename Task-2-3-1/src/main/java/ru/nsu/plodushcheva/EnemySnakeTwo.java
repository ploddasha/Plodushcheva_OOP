package ru.nsu.plodushcheva;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static ru.nsu.plodushcheva.EnemySnake.Direction.*;
import static ru.nsu.plodushcheva.EnemySnake.Direction.DOWN;

public class EnemySnakeTwo {
    private final GameField gameField;
    private final Food food;
    private List<Point> snake;
    private Point snakeHead;
    //private GameField gameField;
    //private FoodGenerator foodGenerator;
    private int ROWS = 30;
    private int COLUMNS = 30;
    private int SQUARE_SIZE;
    private Walls walls;
    boolean gameOver = false;
    private int score;
    private GraphicsContext graphicsContext;


    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    private EnemySnake.Direction direction;

    public EnemySnakeTwo(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        //STARTING_X = gameField.getCOLUMNS() / 2;
        //STARTING_Y = gameField.getROWS() / 2;
        snake = new ArrayList<>();
        //score = 0;
        initSnake();
    }

    public void run(GraphicsContext gc) {
        if (gameOver) {
            return;
        }

        running();
        drawSnake(gc);
        eatFood();

        if (snake.size() > 1) {
            Point crawling = snake.get(snake.size() - 1);
            crawling.x = snakeHead.x;
            crawling.y = snakeHead.y;
            snake.add(1, crawling);
            snake.remove(snake.size() - 1);
        }


        switch (direction) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        //gameOver();

    }
    private void running () {
        EnemySnake.Direction randomDirection = getRandomDirection();

        if (randomDirection == RIGHT ) {
            Point point = new Point(snakeHead.x + 1, snakeHead.y);
            if (direction != LEFT && noWall(point) && notBorder(point) && notSelf(point) ) {
                System.out.println(snakeHead.x + " " + snakeHead.y);
                direction = RIGHT;
            }
        } else if (randomDirection == LEFT) {
            Point point = new Point(snakeHead.x - 1, snakeHead.y);
            if (direction != RIGHT && noWall(point) && notBorder(point) && notSelf(point)) {
                System.out.println(snakeHead.x + " " + snakeHead.y);

                direction = LEFT;
            }
        } else if (randomDirection == UP) {
            Point point = new Point(snakeHead.x, snakeHead.y + 1);
            if (direction != DOWN && noWall(point) && notBorder(point) && notSelf(point)) {
                System.out.println(snakeHead.x + " " + snakeHead.y);

                direction = UP;
            }
        } else if (randomDirection == DOWN) {
            Point point = new Point(snakeHead.x, snakeHead.y - 1);
            if (direction != UP && noWall(point) && notBorder(point) && notSelf(point)) {
                System.out.println(snakeHead.x + " " + snakeHead.y);

                direction = DOWN;
            }
        }
    }

    private boolean notSelf(Point point) {
        for (int i = 3; i < snake.size(); i++) {
            if (point.getX() == snake.get(i).getX() && point.getY() == snake.get(i).getY()) {
                return false;
            }
        }
        return true;
    }

    private boolean notBorder(Point point) {
        return point.getX() != 0 && point.getX() != COLUMNS
                && point.getY() != 0 && point.getY() != ROWS;
    }

    private boolean noWall(Point point) {
        for (int i = 0 ; i < walls.getWalls().size(); i++) {
            if (point.getX() == walls.getWalls().get(i).getX() &&
                    point.getY() == walls.getWalls().get(i).getY()) {
                return false;
            }
        }
        return true;
    }

    public static EnemySnake.Direction getRandomDirection() {

        /*EnemySnake.Direction[] directions = EnemySnake.Direction.values();
        int randomIndex = new Random().nextInt(directions.length);
        return directions[randomIndex];
         */
        EnemySnake.Direction[] directions = EnemySnake.Direction.values();
        int[] weights = {1, 1, 1, 2}; // DOWN будет выбран в два раза чаще
        int sumOfWeights = Arrays.stream(weights).sum();
        int randomIndex = -1;
        int randomValue = new Random().nextInt(sumOfWeights);
        for (int i = 0; i < weights.length; i++) {
            randomValue -= weights[i];
            if (randomValue < 0) {
                randomIndex = i;
                break;
            }
        }
        return directions[randomIndex];
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("5BC0EB"));
        for (Point point : snake) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE() - 1,
                    gameField.getPOINT_SIZE() - 1,
                    25, 25);
        }
    }

    private void initSnake() {
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(5, i + ROWS / 2));
        }
        snakeHead = snake.get(0);
    }

    public void eatFood() {
        for (int i = 0; i < food.getFood().size(); i++) {
            if (snakeHead.getX() == food.getFood().get(i).getX()
                    && snakeHead.getY() == food.getFood().get(i).getY()) {
                food.getFood().remove(i);
                //food.generateFood(walls, this);
                snake.add(new Point(-1, -1));
                score++;
            }
        }
    }

    public void moveRight() {
        if (snakeHead.getX() == COLUMNS) {
            snakeHead.x = 0;
        } else {
            snakeHead.x++;
        }
    }

    public void moveLeft() {
        if (snakeHead.getX() == 0) {
            snakeHead.x = COLUMNS;
        } else {
            snakeHead.x--;
        }
    }

    public void moveUp() {
        if (snakeHead.getY() == 0) {
            snakeHead.y = ROWS;
        } else {
            snakeHead.y--;
        }
    }

    public void moveDown() {
        if (snakeHead.getY() == ROWS) {
            snakeHead.y = 0;
        } else {
            snakeHead.y++;
        }
    }



    public List<Point> getSnake() {
        return snake;
    }
    public Point getSnakeHead() {
        return snakeHead;
    }
    public int getScore() {
        return score;
    }
    public boolean isGameOver() {
        return gameOver;
    }
}
