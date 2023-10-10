package ru.nsu.plodushcheva.model.snakes;

import static ru.nsu.plodushcheva.model.snakes.BaseEnemySnake.Direction.DOWN;
import static ru.nsu.plodushcheva.model.snakes.BaseEnemySnake.Direction.LEFT;
import static ru.nsu.plodushcheva.model.snakes.BaseEnemySnake.Direction.RIGHT;
import static ru.nsu.plodushcheva.model.snakes.BaseEnemySnake.Direction.UP;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.model.Walls;
import ru.nsu.plodushcheva.view.GameField;


/**
 * Represents an enemy snake that interacts with the game.
 * The goal is to eat food.
 */
public class EnemySnakeFood extends BaseEnemySnake {

    /**
     * Constructor.
     *
     * @param gameField the game field
     * @param food the food object
     * @param walls game walls
     */
    public EnemySnakeFood(GameField gameField, Food food, Walls walls) {
        super(gameField, food, walls);
        this.rows = gameField.getRows();
        this.columns = gameField.getColumns();
    }



    /**
     * Moves the enemy snake to the next position
     * based on its current direction and the location of the goal food.
     */
    @Override
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
        BaseEnemySnake.Direction direction;
        List<BaseEnemySnake.Direction> directions = directions();
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
                default -> throw new IllegalStateException("Invalid direction: " + direction);
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
     * Initializes the snake's initial position.
     */
    @Override
    public void initSnake() {
        snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(29, i + rows / 2));
        }
        snakeHead = snake.get(0);
    }


}
