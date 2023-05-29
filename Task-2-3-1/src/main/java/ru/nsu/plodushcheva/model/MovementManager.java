package ru.nsu.plodushcheva.model;

import java.awt.Point;
import ru.nsu.plodushcheva.SnakeGame;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeFood;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.model.snakes.Snake;



/**
 *The MovementManager class handles the movement of snakes in the game.
 * It provides methods to move the player snake, move the enemy snakes,
 * and update the snake's position.
 */
public class MovementManager {

    /**
     * Moves the specified snake in the given direction.
     *
     * @param snake The snake to be moved.
     * @param direction The direction in which the snake should move.
     */
    public void moveSnake(Snake snake, SnakeGame.Direction direction) {
        switch (direction) {
            case RIGHT -> snake.moveRight();
            case LEFT -> snake.moveLeft();
            case UP -> snake.moveUp();
            case DOWN -> snake.moveDown();
        }
    }

    /**
     * Moves the enemy snakes in their respective strategies.
     *
     * @param enemySnakeRandom The random enemy snake.
     * @param enemySnakeFood The food-seeking enemy snake.
     */
    public void moveEnemySnakes(EnemySnakeRandom enemySnakeRandom, EnemySnakeFood enemySnakeFood) {
        enemySnakeRandom.run();
        enemySnakeFood.run();
    }

    /**
     * Updates the position of the snake's body segments based on the movement of the head.
     *
     * @param snake The snake to update the position for.
     */
    public void updateSnakePosition(Snake snake) {
        if (snake.getSnake().size() > 1) {
            Point crawling = snake.getSnake().get(snake.getSnake().size() - 1);
            crawling.x = snake.getSnakeHead().x;
            crawling.y = snake.getSnakeHead().y;
            snake.getSnake().add(1, crawling);
            snake.getSnake().remove(snake.getSnake().size() - 1);
        }
    }
}
