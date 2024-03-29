package ru.nsu.plodushcheva.model.snakes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.model.Walls;
import ru.nsu.plodushcheva.view.GameField;

/**
 *The Snake class represents the player-controlled snake in the game.
 * It manages the snake's movement, collision detection, and score.
 */
public class Snake {
    private final GameField gameField;
    private final Food food;
    private final List<Point> snake;
    private Point snakeHead;
    private final int rows;
    private final int columns;
    private final Walls walls;
    boolean gameOver = false;
    private int score;

    /**
     * Represents the Snake in the game.
     *
     * @param gameField The game field where the snake moves.
     * @param food The food that the snake can eat.
     * @param walls The walls that the snake needs to avoid.
     */
    public Snake(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        this.rows = gameField.getRows();
        this.columns = gameField.getColumns();
        snake = new ArrayList<>();
        initSnake();
    }

    /**
     * Initializes the snake's initial position.
     */
    private void initSnake() {
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(5, i + rows / 2));
        }
        snakeHead = snake.get(0);
    }

    /**
     * Checks if the snake's head has eaten any food.
     */
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

    /**
     * Moves the snake to the right.
     */
    public void moveRight() {
        if (snakeHead.getX() == columns) {
            snakeHead.x = 0;
        } else {
            snakeHead.x++;
        }
    }

    /**
     * Moves the snake to the left.
     */
    public void moveLeft() {
        if (snakeHead.getX() == 0) {
            snakeHead.x = columns;
        } else {
            snakeHead.x--;
        }
    }

    /**
     * Moves the snake up.
     */
    public void moveUp() {
        if (snakeHead.getY() == 0) {
            snakeHead.y = rows;
        } else {
            snakeHead.y--;
        }
    }

    /**
     * Moves the snake down.
     */
    public void moveDown() {
        if (snakeHead.getY() == rows) {
            snakeHead.y = 0;
        } else {
            snakeHead.y++;
        }
    }

    /**
     * Checks if the game is over by evaluating various conditions.
     * The game is considered over if:
     * - The snake's head is outside the game field boundaries.
     * - The snake's head collides with its own body.
     * - The snake's head collides with a wall.
     */
    public void gameOver() {

        if (snakeHead.x < 0 || snakeHead.y < 0
                || snakeHead.x * gameField.getPointSize() >= gameField.getWidth()
                || snakeHead.y * gameField.getPointSize() >= gameField.getHeight()) {
            gameOver = true;
            return;
        }
        for (int i = 3; i < snake.size(); i++) {
            if (snakeHead.getX() == snake.get(i).getX()
                    && snakeHead.getY() == snake.get(i).getY()) {
                gameOver = true;
                return;
            }
        }
        for (int i = 0; i < walls.getWallsList().size(); i++) {
            if (snakeHead.getX() == walls.getWallsList().get(i).getX()
                    && snakeHead.getY() == walls.getWallsList().get(i).getY()) {
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
