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
    private static int width;
    private static int height;
    private static int columns;
    private static int rows;
    private static int squareSize;

    /**
     * Constructs an instance of the Graphics class.
     *
     * @param gameField the game field to be associated with the graphics
     * @param width the width of the graphics
     * @param height the height of the graphics
     * @param columns the number of columns in the graphics
     * @param rows the number of rows in the graphics
     */
    public Graphics(GameField gameField, int width, int height, int columns, int rows) {
        Graphics.width = width;
        Graphics.height = height;
        Graphics.columns = columns;
        Graphics.rows = rows;
        this.gameField = gameField;
        squareSize = width / rows;
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
            default -> gc.setFill(Color.web("FA7900"));
        }
        for (Point point : snake) {
            gc.fillRoundRect(point.getX() * gameField.getPointSize(),
                    point.getY() * gameField.getPointSize(),
                    gameField.getPointSize() - 1,
                    gameField.getPointSize() - 1,
                    25, 25);
        }

    }

    /**
     * Draws the background of the game field.
     *
     * @param gc the GraphicsContext object
     */
    public void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("FDE74C"));
                } else {
                    gc.setFill(Color.web("FDEC68"));
                }
                gc.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
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
            gc.fillRoundRect(point.getX() * gameField.getPointSize(),
                    point.getY() * gameField.getPointSize(),
                    gameField.getPointSize(), gameField.getPointSize(), 45, 45);
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
            gc.fillRect(wall.x * squareSize,
                    wall.y * squareSize, squareSize, squareSize);
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
        gc.fillRect(0, 0, 100, height);
        gc.setFill(Color.web("5BC0EB"));
        gc.fillText("Score: ", 15, 30);
        gc.fillText(score + "/" + scoreForWin, 30, 50);

        gc.fillText("Enemy1 score: ", 15, 70);
        gc.fillText(score1 + "/" + scoreForWin, 30, 90);

        gc.fillText("Enemy2 score: ", 15, 110);
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
        gc.fillText("Game Over", width / 3.5, height / 2);

    }

    /**
     * Draws the "You won" message on the game field.
     *
     * @param gc the GraphicsContext object
     */
    public void drawGameWon(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("You won", width / 3.5, height / 2);
    }
}
