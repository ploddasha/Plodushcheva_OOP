package ru.nsu.plodushcheva;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Food {
    private final int MAX_FOOD;
    private final int HEIGHT;
    private final int WIDTH;

    private List<Point> food;


    public Food(GameField gameField, int MAX_FOOD) {
        this.MAX_FOOD = MAX_FOOD;
        HEIGHT = gameField.getROWS();
        WIDTH = gameField.getCOLUMNS();
        food = new ArrayList<>();

    }
/*

    private void generateFood2() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }

            for (int i=0; i < walls.getWalls().size(); i++) {
                if (walls.getWalls().get(i).getX() == foodX &&
                        walls.getWalls().get(i).getY() == foodY) {
                    continue start;

                }
            }

            break;
        }
    } */
    public void generateFood(Walls walls, Snake snake) {
        while (food.size() < MAX_FOOD) {
            Point newFoodItem;
            newFoodItem = new Point((int) (Math.random() * HEIGHT), ((int) (Math.random() * WIDTH)));
            if (!walls.getWalls().contains(newFoodItem) && !snake.getSnake().contains(newFoodItem) && !food.contains(newFoodItem)) {
                food.add(newFoodItem);
            }
        }
    }


    public List<Point> getFood() {
        return food;
    }

    public void remove() {
        food.removeAll(food);
    }
}
