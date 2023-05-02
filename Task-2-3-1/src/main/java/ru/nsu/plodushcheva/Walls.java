package ru.nsu.plodushcheva;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Walls {
    private List<Point> walls;
    private int SQUARE_SIZE;
    private GraphicsContext gc;
    private final GameField gameField;


    public Walls(GameField gameField, int SQUARE_SIZE) {
        this.gameField = gameField;
        this.SQUARE_SIZE = SQUARE_SIZE;
        this.walls = new ArrayList<>();

    }


    public List<Point> getWalls() {
        return walls;
    }
    public Walls getWalls2() {
        return this;
    }
    public void addWall(Point point) {
        walls.add(point);
    }

    public void addWalls() {
        //gc.setFill(Color.web("5BC0EB"));
        //gc.fillRect(10 * SQUARE_SIZE, 3 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 3));
        //walls.addWall(new Point(10, 3));
        //gc.fillRect(10 * SQUARE_SIZE, 4 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 4));
        //walls.addWall(new Point(10, 4));
        //gc.fillRect(10 * SQUARE_SIZE, 5 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 5));
        //walls.addWall(new Point(10, 5));
        //gc.fillRect(20 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(20, 20));
        //walls.addWall(new Point(20, 20));
        //gc.fillRect(21 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(21, 20));
        //walls.addWall(new Point(21, 20));
        //gc.fillRect(22 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(22, 20));
        //walls.addWall(new Point(22, 20));
        //gc.fillRect(23 * SQUARE_SIZE, 20 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(23, 20));
        //walls.addWall(new Point(23, 20));
        //gc.fillRect(8 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(8, 25));
        //walls.addWall(new Point(8, 25));
        //gc.fillRect(9 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(9, 25));
        //walls.addWall(new Point(9, 25));
        //gc.fillRect(10 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(10, 25));
        //walls.addWall(new Point(10, 25));
        //gc.fillRect(11 * SQUARE_SIZE, 25 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        walls.add(new Point(11, 25));
        //walls.addWall(new Point(11, 25));
    }
}
