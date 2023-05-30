package ru.nsu.plodushcheva.model.snakes;

import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.model.Walls;
import ru.nsu.plodushcheva.view.GameField;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.nsu.plodushcheva.model.snakes.BaseEnemySnake.Direction.*;

public abstract class BaseEnemySnake {

    public final GameField gameField;
    public final Food food;
    public List<Point> snake;
    public Point snakeHead;
    public int rows;
    public int columns;
    public final Walls walls;
    public boolean gameOver = false;
    public int score;
    public BaseEnemySnake.Direction currentDirection;
    public Point goalFood;



    /**
     * Represents the possible directions for the enemy snake.
     */
    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    /**
     * Constructor.
     *
     * @param gameField the game field
     * @param food the food object
     * @param walls game walls
     */
    public BaseEnemySnake(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        this.rows = gameField.getRows();
        this.columns = gameField.getColumns();
    }

    /**
     * Runs the enemy snake's actions.
     */
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



    /**
     * Makes the enemy snake crawl by adjusting its body segments.
     */
    public void crawling() {
        if (snake.size() > 1) {
            Point crawling = snake.get(snake.size() - 1);
            crawling.x = snakeHead.x;
            crawling.y = snakeHead.y;
            snake.add(1, crawling);
            snake.remove(snake.size() - 1);
        }
    }

    /**
     * Moves the snake to the next position.
     */
    public void movingNext() {
        BaseEnemySnake.Direction direction = getRandomDirection(directions());
        if (direction != null) {
            switch (direction) {
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case UP -> moveUp();
                case DOWN -> moveDown();
                default -> throw new IllegalStateException("Invalid direction: " + direction);
            }
        } else {
            System.err.println("EnemySnakeRandom should die");
            gameOver = true;
        }
    }

    /**
     * Generates the list of possible directions for the snake.
     *
     * @return The list of directions.
     */
    List<BaseEnemySnake.Direction> directions() {

        List<BaseEnemySnake.Direction> directions = new ArrayList<>();

        BaseEnemySnake.Direction randomDirection;

        randomDirection = RIGHT;
        Point point = new Point(snakeHead.x + 1, snakeHead.y);
        if (currentDirection != LEFT && noWall(point) && notBorder(point) && notSelf(point)) {
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

    /**
     * Returns a random direction from the given list.
     *
     * @param directions The list of directions
     * @return A random direction
     */
    public static BaseEnemySnake.Direction getRandomDirection(List<BaseEnemySnake.Direction> directions) {
        if (directions.size() > 0) {
            int randomIndex = new Random().nextInt(directions.size());
            return directions.get(randomIndex);
        } else {
            return null;
        }
    }

    /**
     * Checks if the given point is not occupied by the snake itself.
     *
     * @param point The point to check.
     * @return True if the point is not occupied by the snake itself, false otherwise.
     */
    private boolean notSelf(Point point) {
        for (Point value : snake) {
            if (point.getX() == value.getX() && point.getY() == value.getY()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given point is not outside the game field.
     *
     * @param point  The point to check.
     * @return True if the point is not outside the game field, false otherwise.
     */
    private boolean notBorder(Point point) {
        return point.getX() != -1 && point.getX() != columns
                && point.getY() != -1 && point.getY() != rows;
    }

    /**
     * Checks if the given point does not contain a wall.
     *
     * @param point The point to check.
     * @return True if the point does not contain a wall, false otherwise.
     */
    private boolean noWall(Point point) {
        for (int i = 0; i < walls.getWallsList().size(); i++) {
            if (point.getX() == walls.getWallsList().get(i).getX()
                    && point.getY() == walls.getWallsList().get(i).getY()) {
                return false;
            }
        }
        return true;
    }

    /* override
    /**
     * Initializes the snake's initial position.
     */
    public void initSnake() {
        snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(29, i + rows / 2));
        }
        snakeHead = snake.get(0);

    }

    /**
     * Checks if the enemy snake has eaten the food and performs the necessary actions.
     */
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
    /**
     * moves snake's head to the right.
     */
    public void moveRight() {
        currentDirection = RIGHT;
        snakeHead.x++;
    }

    /**
     * moves snake's head to the left.
     */
    public void moveLeft() {
        currentDirection = LEFT;
        snakeHead.x--;
    }

    /**
     * moves snake's head up.
     */
    public void moveUp() {
        currentDirection = UP;
        snakeHead.y--;
    }

    /**
     * moves snake's head down.
     */
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
