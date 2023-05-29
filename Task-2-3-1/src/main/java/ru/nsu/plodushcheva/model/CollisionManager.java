package ru.nsu.plodushcheva.model;

import ru.nsu.plodushcheva.model.snakes.EnemySnakeFood;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.model.snakes.Snake;

/**
 * The CollisionManager class handles collisions
 * between different elements in the game.
 * It checks for collisions between the player snake,
 * enemy snakes, and food items.
 */
public class CollisionManager {

    /**
     * Checks for collisions between the player snake, enemy snakes, and food items.
     * If a collision is detected, it updates the game state accordingly.
     *
     * @param snake The player snake.
     * @param enemySnakeFood The enemy snake that seeks food.
     * @param enemySnakeRandom The random enemy snake.
     */
    public void collision(Snake snake, EnemySnakeFood enemySnakeFood,
                          EnemySnakeRandom enemySnakeRandom) {
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

}
