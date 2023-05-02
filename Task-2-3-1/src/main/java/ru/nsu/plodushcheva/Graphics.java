package ru.nsu.plodushcheva;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Graphics {

    private GameField gameField;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int COLUMNS = 30;
    private static final int ROWS = 30;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    //private List<Point> walls;

    public Graphics(GameField gameField) {
        this.gameField = gameField;

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
        //gc.fillRect(foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        for (Point point : food) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(), point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 45, 45);
        }
    }


    public void drawWalls(GraphicsContext gc, Walls walls) {
        gc.setFill(Color.web("5BC0EB"));
        gc.fillRect(10 * SQUARE_SIZE, 3 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(10, 3));
        //walls.addWall(new Point(10, 3));
        gc.fillRect(10 * SQUARE_SIZE, 4 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(10, 4));
        //walls.addWall(new Point(10, 4));
        gc.fillRect(10 * SQUARE_SIZE, 5 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(10, 5));
        //walls.addWall(new Point(10, 5));
        gc.fillRect(20 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(20, 20));
        //walls.addWall(new Point(20, 20));
        gc.fillRect(21 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(21, 20));
        //walls.addWall(new Point(21, 20));
        gc.fillRect(22 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(22, 20));
        //walls.addWall(new Point(22, 20));
        gc.fillRect(23 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(23, 20));
        //walls.addWall(new Point(23, 20));
        gc.fillRect(8 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(8, 25));
        //walls.addWall(new Point(8, 25));
        gc.fillRect(9 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(9, 25));
       // walls.addWall(new Point(9, 25));
        gc.fillRect(10 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(10, 25));
        //walls.addWall(new Point(10, 25));
        gc.fillRect(11 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //walls.add(new Point(11, 25));
        //walls.addWall(new Point(11, 25));
    }

    public void drawScore(GraphicsContext gc, int score, int scoreForWin) {
        gc.setFill(Color.web("5BC0EB"));
        //graphicsContext.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + score + "/" + scoreForWin, 10, 35);

    }
}
