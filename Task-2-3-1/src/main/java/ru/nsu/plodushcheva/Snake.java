package ru.nsu.plodushcheva;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final GameField gameField;
    private final Food food;
    private List<Point> snake;
    private Point snakeHead;
    //private GameField gameField;
    //private FoodGenerator foodGenerator;
    private int ROWS = 30;
    private int COLUMNS = 30;
    private Walls walls;
    boolean gameOver = false;
    private int score;

    public Snake(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        //STARTING_X = gameField.getCOLUMNS() / 2;
        //STARTING_Y = gameField.getROWS() / 2;
        snake = new ArrayList<>();
        //score = 0;
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
                food.generateFood(walls, this);
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
            //collisionPoint = snake.get(0);
            gameOver = true;
            return;
        }
        for (int i = 3; i < snake.size(); i++) {
            if (snakeHead.getX() == snake.get(i).getX() && snakeHead.getY() == snake.get(i).getY()) {
                //collisionPoint = snake.get(i);
                gameOver = true;
                return;
            }
        }
        //System.out.println(walls.getWalls());
        /*
        for (Point point : walls.getWalls()) {
            System.out.println("r " + point.getX() + " " + point.x + " " + point.getX());
            if (point.getX() == snakeHead.getX() && point.getY() == snakeHead.getY()) {
                //collisionPoint = snake.get(0);
                return true;
            }
        } */
        for (int i = 0 ; i < walls.getWalls().size(); i++) {
            //System.out.println("r " + snakeHead.getX() + " " + walls.getWalls().get(i).getX());
            if (snakeHead.getX() == walls.getWalls().get(i).getX() &&
            snakeHead.getY() == walls.getWalls().get(i).getY()) {
                System.out.println("r " + snakeHead.getX() + " " + walls.getWalls().get(i).getX());
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
    public boolean isGameOver() {
        return gameOver;
    }
}
