package ru.nsu.plodushcheva.environment;

import ru.nsu.plodushcheva.snakes.EnemySnakeFood;
import ru.nsu.plodushcheva.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.snakes.Snake;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Food {
    private final int MAX_FOOD;
    private final int HEIGHT;
    private final int WIDTH;

    private final List<Point> food;


    public Food(GameField gameField, int MAX_FOOD) {
        this.MAX_FOOD = MAX_FOOD;
        HEIGHT = gameField.getROWS();
        WIDTH = gameField.getCOLUMNS();
        food = new ArrayList<>();

    }

    public List<Point> getFood() {
        return food;
    }

    Snake snake;
    public void addSnake(Snake snake) {
        this.snake = snake;
    }
    EnemySnakeRandom enemySnakeRandom;
    public void addEnemySnakeRandom(EnemySnakeRandom enemySnakeRandom) {
        this.enemySnakeRandom = enemySnakeRandom;
    }
    EnemySnakeFood enemySnakeFood;
    public void addEnemySnakeFood(EnemySnakeFood enemySnakeFood) {
        this.enemySnakeFood = enemySnakeFood;
    }

    public void generateFood(Walls walls) {
        while (food.size() < MAX_FOOD) {
            Point newFoodItem;
            newFoodItem = new Point((int) (Math.random() * HEIGHT), ((int) (Math.random() * WIDTH)));
            if (!walls.getWalls().contains(newFoodItem)
                    && !snake.getSnake().contains(newFoodItem)
                    && !enemySnakeRandom.getSnake().contains(newFoodItem)
                    && !enemySnakeFood.getSnake().contains(newFoodItem)
                    && !food.contains(newFoodItem)) {
                food.add(newFoodItem);
            }
        }
    }



}
