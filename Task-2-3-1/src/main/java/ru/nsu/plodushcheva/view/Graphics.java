package ru.nsu.plodushcheva.view;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.nsu.plodushcheva.model.Walls;

/**
 * Class responsible for drawing game graphics.
 */
public class Graphics {

    private final GameField gameField;
    private static int WIDTH;
    private static int HEIGHT;
    private static int COLUMNS;
    private static int ROWS;
    private static int SQUARE_SIZE;

    public Graphics(GameField gameField, int WIDTH, int HEIGHT, int COLUMNS, int ROWS) {
        Graphics.WIDTH = WIDTH;
        Graphics.HEIGHT = HEIGHT;
        Graphics.COLUMNS = COLUMNS;
        Graphics.ROWS = ROWS;
        this.gameField = gameField;
        SQUARE_SIZE = WIDTH/ROWS;

    }

    /**
     * Draws the snake on the game field.
     *
     * @param gc the GraphicsContext object
     * @param snake the list of snake points
     */
    public void drawSnake(GraphicsContext gc, java.util.List<Point> snake, int index) {
        switch (index) {
            case 1 -> gc.setFill(Color.web("9BC53D"));
            case 2 -> gc.setFill(Color.web("5BC0EB"));
            case 3 -> gc.setFill(Color.web("FA7921"));
        }
        //gc.setFill(Color.web("9BC53D"));
        for (Point point : snake) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE() - 1,
                    gameField.getPOINT_SIZE() - 1,
                    25, 25);
        }

    }

    /**
     * Draws the background of the game field.
     *
     * @param gc the GraphicsContext object
     */
    public void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("FDE74C"));
                } else {
                    gc.setFill(Color.web("FDEC68"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    /**
     * Draws the food on the game field.
     *
     * @param gc the GraphicsContext object
     * @param food the list of food points
     */
    public void drawFood(GraphicsContext gc, java.util.List<Point> food) {
        gc.setFill(Color.web("E55934"));
        for (Point point : food) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 45, 45);
        }
    }


    /**
     * Draws the walls on the game field.
     *
     * @param gc the GraphicsContext object
     * @param walls the Walls object representing the walls
     */
    public void drawWalls(GraphicsContext gc, Walls walls) {
        gc.setFill(Color.web("5BC0EB"));

        for (Point wall : walls.getWallsList()) {
            gc.fillRect(wall.x * SQUARE_SIZE,
                    wall.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }


    /**
     * Draws the score on the game field.
     *
     * @param gc the GraphicsContext object
     * @param score the player's score
     * @param score1 the score of enemy 1
     * @param score2 the score of enemy 2
     * @param scoreForWin the score needed to win the game
     */
    public void drawScore(GraphicsContext gc, int score, int score1, int score2, int scoreForWin) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 100, HEIGHT);
        gc.setFill(Color.web("5BC0EB"));
        gc.fillText("Score: " , 15, 30);
        gc.fillText(score + "/" + scoreForWin, 30, 50);

        gc.fillText("Enemy1 score: " , 15, 70);
        gc.fillText(score1 + "/" + scoreForWin, 30, 90);

        gc.fillText("Enemy2 score: " , 15, 110);
        gc.fillText(score2 + "/" + scoreForWin, 30, 130);

    }

    /**
     * Draws the level on the game field.
     *
     * @param gc the GraphicsContext object
     * @param level the current level
     */
    public void drawLevel(GraphicsContext gc, int level) {
        gc.setFill(Color.web("5BC0EB"));
        gc.fillText("Level " + level, 15, 150);
    }

    /**
     * Draws the "Game Over" message on the game field.
     *
     * @param gc the GraphicsContext object
     */
    public void drawGameOver(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);

    }

    /**
     * Draws the "You won" message on the game field.
     *
     * @param gc the GraphicsContext object
     */
    public void drawGameWon(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("You won", WIDTH / 3.5, HEIGHT / 2);
    }
}
