package ru.nsu.plodushcheva.model;

import ru.nsu.plodushcheva.SnakeGame;
import ru.nsu.plodushcheva.model.snakes.Snake;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeFood;
import java.awt.Point;


public class MovementManager {
    public void moveSnake(Snake snake, SnakeGame.Direction direction) {
        switch (direction) {
            case RIGHT:
                snake.moveRight();
                break;
            case LEFT:
                snake.moveLeft();
                break;
            case UP:
                snake.moveUp();
                break;
            case DOWN:
                snake.moveDown();
                break;
        }
    }

    public void moveEnemySnakes(EnemySnakeRandom enemySnakeRandom, EnemySnakeFood enemySnakeFood) {
        enemySnakeRandom.run();
        enemySnakeFood.run();
    }

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
