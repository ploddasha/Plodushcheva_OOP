package ru.nsu.plodushcheva.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeFood;
import ru.nsu.plodushcheva.model.snakes.EnemySnakeRandom;
import ru.nsu.plodushcheva.model.snakes.Snake;
import ru.nsu.plodushcheva.view.GameField;



/**
 * The Food class represents the food items in the game.
 * It is responsible for generating and managing the food items on the game field.
 */
public class Food {
    private final int maxFood;
    private final int height;
    private final int width;
    Snake snake;
    EnemySnakeRandom enemySnakeRandom;
    EnemySnakeFood enemySnakeFood;

    private final List<Point> food;


    /**
     * Constructs a Food object with the specified game field and maximum number of food items.
     *
     * @param gameField The game field.
     * @param maxFood The maximum number of food items.
     */
    public Food(GameField gameField, int maxFood) {
        this.maxFood = maxFood;
        height = gameField.getRows();
        width = gameField.getColumns();
        food = new ArrayList<>();

    }

    public List<Point> getFood() {
        return food;
    }

    /**
     * Adds the player snake to the food manager.
     *
     * @param snake The player snake.
     */
    public void addSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * Adds the enemy random snake to the food manager.
     *
     * @param enemySnakeRandom The enemy random snake.
     */
    public void addEnemySnakeRandom(EnemySnakeRandom enemySnakeRandom) {
        this.enemySnakeRandom = enemySnakeRandom;
    }

    /**
     * Adds the food-seeking enemy snake to the food manager.
     *
     * @param enemySnakeFood The food-seeking enemy snake.
     */
    public void addEnemySnakeFood(EnemySnakeFood enemySnakeFood) {
        this.enemySnakeFood = enemySnakeFood;
    }

    /**
     * Generates the food items on the game field,
     * considering the walls and other snakes' positions.
     *
     * @param walls The walls in the game.
     */
    public void generateFood(Walls walls) {
        while (food.size() < maxFood) {
            Point newFoodItem;
            newFoodItem = new Point((int) (Math.random() * height),
                    ((int) (Math.random() * width)));
            if (!walls.getWallsList().contains(newFoodItem)
                    && !snake.getSnake().contains(newFoodItem)
                    && !enemySnakeRandom.getSnake().contains(newFoodItem)
                    && !enemySnakeFood.getSnake().contains(newFoodItem)
                    && !food.contains(newFoodItem)) {
                food.add(newFoodItem);
            }
        }
    }
}
