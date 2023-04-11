package ru.nsu.plodushcheva;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.nsu.plodushcheva.EnemySnake.Direction.*;

public class EnemySnake{

    private List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private int score = 0;
    private int ROWS;
    private int COLUMNS;
    private int SQUARE_SIZE;
    private GraphicsContext gc;
    private boolean gameOver;
    private Walls walls;

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    private Direction direction;

    public EnemySnake(int ROWS, int COLUMNS, int SQUARE_SIZE,
                      GraphicsContext graphicsContext, Walls walls){
        this.ROWS = ROWS;
        this.COLUMNS = COLUMNS;
        this.SQUARE_SIZE = SQUARE_SIZE;
        this.gc = graphicsContext;
        this.walls = walls;

        //int p = new Random().nextInt(20);
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(15, i + ROWS / 2));
        }
        snakeHead = snakeBody.get(0);

        //Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run()));
        //timeline.setCycleCount(Animation.INDEFINITE);
        //timeline.play();
    }

    public void run() {
        if (gameOver) {
            return;
        }

        running();
        drawSnake(gc);
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

        switch (direction) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        //eatFood();
        gameOver();

    }

    private void running () {
        Direction randomDirection = getRandomDirection();

        if (randomDirection == RIGHT ) {
            if (direction != LEFT) {
                direction = RIGHT;
            }
        } else if (randomDirection == LEFT) {
            if (direction != RIGHT) {
                direction = LEFT;
            }
        } else if (randomDirection == UP) {
            if (direction != DOWN) {
                direction = UP;
            }
        } else if (randomDirection == DOWN) {
            if (direction != UP) {
                direction = DOWN;
            }
        }
    }

    public static Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        int randomIndex = new Random().nextInt(directions.length);
        return directions[randomIndex];
    }


    private void moveRight() {
        if (snakeHead.getX() == COLUMNS) {
            snakeHead.x = 0;
        } else {
            snakeHead.x++;
        }
    }

    private void moveLeft() {
        if (snakeHead.getX() == 0) {
            snakeHead.x = COLUMNS;
        } else {
            snakeHead.x--;
        }
    }

    private void moveUp() {
        if (snakeHead.getY() == 0) {
            snakeHead.y = ROWS;
        } else {
            snakeHead.y--;
        }
    }

    private void moveDown() {
        if (snakeHead.getY() == ROWS) {
            snakeHead.y = 0;
        } else {
            snakeHead.y++;
        }
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("FA7921"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE,
                snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE,
                    snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }
/*
    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            score++;
            generateFood();
        }
    } */

    public void gameOver() {

        for (int i = 0; i < walls.getWalls().size(); i++) {
            if (snakeHead.getX() == walls.getWalls().get(i).getX() &&
                    snakeHead.getY() == walls.getWalls().get(i).getY() ) {
                gameOver = true;
            }
        }

        //destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

}
