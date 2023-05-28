package ru.nsu.plodushcheva.model.snakes;

import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.view.GameField;
import ru.nsu.plodushcheva.model.Walls;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final GameField gameField;
    private final Food food;
    private final List<Point> snake;
    private Point snakeHead;
    private final int ROWS;
    private final int COLUMNS;
    private final Walls walls;
    boolean gameOver = false;
    private int score;

    public Snake(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        this.ROWS = gameField.getROWS();
        this.COLUMNS = gameField.getCOLUMNS();
        snake = new ArrayList<>();
        initSnake();
    }

    private void initSnake() {
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(5, i + ROWS / 2));
        }
        snakeHead = snake.get(0);
    }

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
        if (snakeHead.getX() == COLUMNS) {
            snakeHead.x = 0;
        } else {
            snakeHead.x++;
        }
    }

    public void moveLeft() {
        if (snakeHead.getX() == 0) {
            snakeHead.x = COLUMNS;
        } else {
            snakeHead.x--;
        }
    }

    public void moveUp() {
        if (snakeHead.getY() == 0) {
            snakeHead.y = ROWS;
        } else {
            snakeHead.y--;
        }
    }

    public void moveDown() {
        if (snakeHead.getY() == ROWS) {
            snakeHead.y = 0;
        } else {
            snakeHead.y++;
        }
    }

    public void gameOver() {

        if (snakeHead.x < 0 || snakeHead.y < 0 ||
                snakeHead.x * gameField.getPOINT_SIZE() >= gameField.getWIDTH() ||
                snakeHead.y * gameField.getPOINT_SIZE() >= gameField.getHEIGHT()) {
            gameOver = true;
            return;
        }
        for (int i = 3; i < snake.size(); i++) {
            if (snakeHead.getX() == snake.get(i).getX() && snakeHead.getY() == snake.get(i).getY()) {
                gameOver = true;
                return;
            }
        }
        for (int i = 0 ; i < walls.getWallsList().size(); i++) {
            if (snakeHead.getX() == walls.getWallsList().get(i).getX() &&
            snakeHead.getY() == walls.getWallsList().get(i).getY()) {
                System.out.println("r " + snakeHead.getX() + " " + walls.getWallsList().get(i).getX());
                gameOver = true;
                return;
            }
        }


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
    public void setScore() {
        score++;
    }
    public void setGameOver() {
        gameOver = true;
    }
    public boolean isGameOver() {
        return gameOver;
    }
}