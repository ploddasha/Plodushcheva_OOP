package ru.nsu.plodushcheva;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

public class Graphics {

    private GameField gameField;
    private static int WIDTH = 600;
    private static int HEIGHT = 600;
    private static int COLUMNS = 30;
    private static int ROWS = 30;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    //private List<Point> walls;

    public Graphics(GameField gameField, int WIDTH, int HEIGHT, int COLUMNS, int ROWS) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.COLUMNS = COLUMNS;
        this.ROWS = ROWS;
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
        //Image image = new Image("wall.png");
        //Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/wall.png")));

        List<Point> wallls = walls.getWalls();
        for (int i = 0; i < wallls.size(); i++){
            gc.fillRect(wallls.get(i).x * SQUARE_SIZE,
                    wallls.get(i).y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            //gc.drawImage(image, wallls.get(i).x * SQUARE_SIZE,
            //        wallls.get(i).y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }


    public void drawScore(GraphicsContext gc, int score, int scoreForWin) {
        gc.setFill(Color.web("5BC0EB"));
        gc.fillText("Score: " , 20, 35);
        gc.fillText(score + "/" + scoreForWin, 30, 50);

    }
}
