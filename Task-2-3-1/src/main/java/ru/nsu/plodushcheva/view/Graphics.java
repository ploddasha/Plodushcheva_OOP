package ru.nsu.plodushcheva.view;

import javafx.scene.text.Font;
import ru.nsu.plodushcheva.model.Walls;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public void drawSnake(GraphicsContext gc, java.util.List<Point> snake) {
        gc.setFill(Color.web("9BC53D"));
        for (Point point : snake) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE() - 1,
                    gameField.getPOINT_SIZE() - 1,
                    25, 25);
        }
    }

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

    public void drawFood(GraphicsContext gc, java.util.List<Point> food) {
        gc.setFill(Color.web("E55934"));
        for (Point point : food) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 45, 45);
        }
    }


    public void drawWalls(GraphicsContext gc, Walls walls) {
        gc.setFill(Color.web("5BC0EB"));

        for (Point wall : walls.getWallsList()) {
            gc.fillRect(wall.x * SQUARE_SIZE,
                    wall.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }


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

    public void drawLevel(GraphicsContext gc, int level) {
        gc.setFill(Color.web("5BC0EB"));
        gc.fillText("Level " + level, 15, 150);
    }

    public void drawGameOver(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);

    }

    public void drawGameWon(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("You won", WIDTH / 3.5, HEIGHT / 2);
    }
}
