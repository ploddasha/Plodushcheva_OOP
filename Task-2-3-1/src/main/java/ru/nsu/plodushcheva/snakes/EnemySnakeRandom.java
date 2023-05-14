package ru.nsu.plodushcheva.snakes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.plodushcheva.environment.Food;
import ru.nsu.plodushcheva.environment.GameField;
import ru.nsu.plodushcheva.environment.Walls;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.nsu.plodushcheva.snakes.EnemySnakeRandom.Direction.*;


public class EnemySnakeRandom {
    private final GameField gameField;
    private final Food food;
    private List<Point> snake;
    private Point snakeHead;
    private final int ROWS = 30;
    private final int COLUMNS = 30;
    private int SQUARE_SIZE;
    private final Walls walls;
    boolean gameOver = false;
    private int score;
    //private GraphicsContext graphicsContext;


    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    private EnemySnakeRandom.Direction currentDirection;

    public EnemySnakeRandom(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        snake = new ArrayList<>();
        initSnake();
    }

    public void run() {
        if (!gameOver) {
            crawling();
            movingNext();
            eatFood();
        } else {
            snake = new ArrayList<>();
            initSnake();
            gameOver = false;
        }
    }

    public void crawling() {
        if (snake.size() > 1) {
            Point crawling = snake.get(snake.size() - 1);
            crawling.x = snakeHead.x;
            crawling.y = snakeHead.y;
            snake.add(1, crawling);
            snake.remove(snake.size() - 1);
        }
    }

    public void movingNext() {
        Direction direction = getRandomDirection(directions());
        if (direction != null) {
            switch (direction) {
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case UP -> moveUp();
                case DOWN -> moveDown();
            }
        } else {
            System.err.println("EnemySnakeRandom should die");
            gameOver = true;
        }
    }
    private List<Direction> directions () {

        List<Direction> directions = new ArrayList<>();

        Direction randomDirection;

        randomDirection = RIGHT;
        Point point = new Point(snakeHead.x + 1, snakeHead.y);
        if (currentDirection != LEFT && noWall(point) && notBorder(point) && notSelf(point) ) {
            directions.add(randomDirection);
        }

        randomDirection = LEFT;
        point = new Point(snakeHead.x - 1, snakeHead.y);
        if (currentDirection != RIGHT && noWall(point) && notBorder(point) && notSelf(point)) {
            directions.add(randomDirection);
        }

        randomDirection = UP;
        point = new Point(snakeHead.x, snakeHead.y - 1);
        if (currentDirection != DOWN && noWall(point) && notBorder(point) && notSelf(point)) {
            directions.add(randomDirection);
        }

        randomDirection = DOWN;
        point = new Point(snakeHead.x, snakeHead.y + 1);
        if (currentDirection != UP && noWall(point) && notBorder(point) && notSelf(point)) {
            directions.add(randomDirection);
        }

        return directions;
    }

    public static Direction getRandomDirection(List<Direction> directions) {
        if (directions.size() > 0) {
            int randomIndex = new Random().nextInt(directions.size());
            return directions.get(randomIndex);
        } else {
            return null;
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



    public void drawSnake(GraphicsContext gc) {

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
            snake.add(new Point(1, 2));
        }
        snakeHead = snake.get(0);
    }

    public void eatFood() {
        for (int i = 0; i < food.getFood().size(); i++) {
            if (snakeHead.getX() == food.getFood().get(i).getX()
                    && snakeHead.getY() == food.getFood().get(i).getY()) {
                food.getFood().remove(i);
                food.generateFood(walls);
                snake.add(new Point(-1, -1));
                score++;
            }
        }
    }

    public void moveRight() {
        currentDirection = RIGHT;
        snakeHead.x++;
    }

    public void moveLeft() {
        currentDirection = LEFT;
        snakeHead.x--;
    }

    public void moveUp() {
        currentDirection = UP;
        snakeHead.y--;
    }

    public void moveDown() {
        currentDirection = DOWN;
        snakeHead.y++;

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

    public void setGameOver() {
        gameOver = true;
    }

}
