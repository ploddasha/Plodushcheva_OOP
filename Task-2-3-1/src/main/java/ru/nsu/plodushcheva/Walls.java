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
        walls.add(new Point(10, 3));
        walls.add(new Point(10, 4));
        walls.add(new Point(10, 5));

        walls.add(new Point(20, 20));
        walls.add(new Point(21, 20));
        walls.add(new Point(22, 20));
        walls.add(new Point(23, 20));

        walls.add(new Point(8, 25));
        walls.add(new Point(9, 25));
        walls.add(new Point(10, 25));
        walls.add(new Point(11, 25));
    }

    public void addWallsForLevelOne() {
        walls.add(new Point(10, 3));
        walls.add(new Point(10, 4));
        walls.add(new Point(10, 5));
        walls.add(new Point(20, 20));
        walls.add(new Point(21, 20));
        walls.add(new Point(22, 20));
        walls.add(new Point(23, 20));
        walls.add(new Point(8, 25));
        walls.add(new Point(9, 25));
        walls.add(new Point(10, 25));
        walls.add(new Point(11, 25));
    }


    public void addWallsForLevelTwo() {


    }

    public void addWallsForLevelThree() {
        walls.add(new Point(2, 1));
        walls.add(new Point(3, 1));
        walls.add(new Point(4, 1));
        walls.add(new Point(5, 1));
        walls.add(new Point(6, 1));
        walls.add(new Point(2, 3));
        walls.add(new Point(2, 4));
        walls.add(new Point(2, 5));

        walls.add(new Point(11, 2));

        walls.add(new Point(14, 2));

        walls.add(new Point(20, 1));
        walls.add(new Point(21, 1));

        walls.add(new Point(1, 10));
        walls.add(new Point(1, 11));
        walls.add(new Point(1, 12));

        walls.add(new Point(5, 8));
        walls.add(new Point(5, 9));
        walls.add(new Point(5, 10));
        walls.add(new Point(5, 11));
        walls.add(new Point(5, 12));

        walls.add(new Point(5, 7));

        walls.add(new Point(8, 14));
        walls.add(new Point(9, 14));
        walls.add(new Point(10, 14));
        walls.add(new Point(11, 14));
        walls.add(new Point(12, 14));

        walls.add(new Point(15, 6));
        walls.add(new Point(15, 7));
        walls.add(new Point(15, 8));
        walls.add(new Point(15, 9));
        walls.add(new Point(15, 10));
        walls.add(new Point(15, 11));

        walls.add(new Point(4, 18));

        walls.add(new Point(7, 21));
        walls.add(new Point(7, 22));
        walls.add(new Point(7, 23));
        walls.add(new Point(7, 24));
        walls.add(new Point(6, 24));
        walls.add(new Point(5, 24));
        walls.add(new Point(4, 24));
        walls.add(new Point(3, 24));
        walls.add(new Point(3, 25));
        walls.add(new Point(3, 26));
        walls.add(new Point(3, 27));
        walls.add(new Point(3, 28));

        walls.add(new Point(20, 17));
        walls.add(new Point(21, 17));
        walls.add(new Point(22, 17));
        walls.add(new Point(23, 17));
        walls.add(new Point(24, 17));

        walls.add(new Point(28, 24));
        walls.add(new Point(28, 25));
        walls.add(new Point(28, 26));
        walls.add(new Point(28, 27));
        walls.add(new Point(28, 28));

        walls.add(new Point(12, 19));
        walls.add(new Point(12, 20));
        walls.add(new Point(12, 21));
        walls.add(new Point(13, 21));
        walls.add(new Point(14, 21));
        walls.add(new Point(15, 21));
        walls.add(new Point(16, 21));


        walls.add(new Point(23, 28));

        walls.add(new Point(28, 10));
        walls.add(new Point(28, 11));
        walls.add(new Point(28, 12));


    }



}
