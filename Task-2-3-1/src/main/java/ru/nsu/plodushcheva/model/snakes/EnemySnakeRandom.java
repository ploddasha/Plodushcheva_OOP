package ru.nsu.plodushcheva.model.snakes;

import java.awt.Point;
import java.util.ArrayList;
import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.model.Walls;
import ru.nsu.plodushcheva.view.GameField;


/**
 * Represents an enemy snake in the game.
 * No goal, random movements.
 */
public class EnemySnakeRandom extends BaseEnemySnake {


    /**
     * Constructs an instance of the EnemySnakeRandom class.
     *
     * @param gameField the game field
     * @param food the food object
     * @param walls game walls
     */
    public EnemySnakeRandom(GameField gameField, Food food, Walls walls) {
        super(gameField, food, walls);
        this.rows = gameField.getRows();
        this.columns = gameField.getColumns();
    }


    /**
     * Moves the snake to the next position.
     */
    public void movingNext() {
        Direction direction = getRandomDirection(directions());
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
     * Initializes the snake with starting positions.
     */
    @Override
    public void initSnake() {
        snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(1, 2));
        }
        snakeHead = snake.get(0);
    }


}
