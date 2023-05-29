package ru.nsu.plodushcheva.model.snakes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.model.Walls;
import ru.nsu.plodushcheva.view.GameField;

import static ru.nsu.plodushcheva.model.snakes.EnemySnakeFood.Direction.UP;
import static ru.nsu.plodushcheva.model.snakes.EnemySnakeFood.Direction.DOWN;
import static ru.nsu.plodushcheva.model.snakes.EnemySnakeFood.Direction.LEFT;
import static ru.nsu.plodushcheva.model.snakes.EnemySnakeFood.Direction.RIGHT;


/**
 * Represents an enemy snake that interacts with the game.
 * The goal is to eat food.
 */
public class EnemySnakeFood {
    private final GameField gameField;
    private final Food food;
    private List<Point> snake;
    private Point snakeHead;
    private final int rows;
    private final int columns;
    private final Walls walls;
    boolean gameOver = false;
    private int score;
    private EnemySnakeFood.Direction currentDirection;


    /**
     * Represents the possible directions for the enemy snake.
     */
    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    public EnemySnakeFood(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        this.rows = gameField.getRows();
        this.columns = gameField.getColumns();
        snake = new ArrayList<>();
        initSnake();
    }

    /**
     *  Runs the enemy snake's actions, including crawling, moving, and eating.
     *  If the game is over, resets the snake.
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

    Point goalFood;

    /**
     * Moves the enemy snake to the next position
     * based on its current direction and the location of the goal food.
     */
    public void movingNext() {
        List<Point> foods = food.getFood();
        if (goalFood == null || !foods.contains(goalFood)) {
            goalFood = getRandomFood(foods);
        }

        if (goalFood.x > snakeHead.x) {
            currentDirection = RIGHT;
        } else if (goalFood.x == snakeHead.x) {
            if (goalFood.y > snakeHead.y) {
                currentDirection = DOWN;
            } else if (goalFood.y  < snakeHead.y) {
                currentDirection = UP;
            }
        } else {
            currentDirection = LEFT;
        }
        Direction direction;
        List<Direction> directions = directions();
        if (!directions.contains(currentDirection)) {
            direction = getRandomDirection(directions);
        } else {
            direction = currentDirection;
        }
        if (direction != null) {
            switch (direction) {
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case UP -> moveUp();
                case DOWN -> moveDown();
            }
        } else {
            System.err.println("EnemySnakeFood should die");
            gameOver = true;
        }
    }

    /**
     * Returns a random food point from the given list of food points.
     *
     * @param foods the list of food points
     * @return a random food point
     */
    private Point getRandomFood(List<Point> foods) {
        int randomIndex = new Random().nextInt(foods.size());
        return foods.get(randomIndex);
    }

    /**
     * Returns a list of possible directions for the snake to move in.
     *
     * @return the list of possible directions
     */
    private List<Direction> directions() {

        List<Direction> directions = new ArrayList<>();

        Direction randomDirection;

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
     * Returns a random direction from the given list of directions.
     *
     * @param directions the list of directions
     * @return a random direction
     */
    public static Direction getRandomDirection(List<Direction> directions) {
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

    /**
     * Initializes the snake's initial position.
     */
    private void initSnake() {
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
