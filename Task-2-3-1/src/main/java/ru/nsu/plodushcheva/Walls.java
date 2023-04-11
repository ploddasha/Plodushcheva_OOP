package ru.nsu.plodushcheva;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Walls {
    private List<Point> walls = new ArrayList<>();
    private int SQUARE_SIZE;
    private GraphicsContext gc;


    public Walls(GraphicsContext gc, int SQUARE_SIZE) {
        this.gc = gc;
        this.SQUARE_SIZE = SQUARE_SIZE;
    }

    public void drawWalls() {
        gc.setFill(Color.web("5BC0EB"));
        gc.fillRect(10 * SQUARE_SIZE, 3 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 3));
        gc.fillRect(10 * SQUARE_SIZE, 4 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 4));
        gc.fillRect(10 * SQUARE_SIZE, 5 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 5));

        gc.fillRect(20 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(20, 20));
        gc.fillRect(21 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(21, 20));
        gc.fillRect(22 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(22, 20));
        gc.fillRect(23 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(23, 20));

        gc.fillRect(8 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(8, 25));
        gc.fillRect(9 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(9, 25));
        gc.fillRect(10 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 25));
        gc.fillRect(11 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(11, 25));
    }

    public List<Point> getWalls() {
        return walls;
    }

    public void addWalls(int score) {
        if (score == 3) {
            gc.setFill(Color.web("5BC0EB"));
            gc.fillRect(10 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            walls.add(new Point(10, 20));
            gc.fillRect(11 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            walls.add(new Point(11, 20));
            gc.fillRect(12 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            walls.add(new Point(12, 20));
        }
    }
}
