package ru.nsu.plodushcheva.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Walls {
    private final List<Point> walls;

    public Walls() {
        this.walls = new ArrayList<>();
    }


    public List<Point> getWallsList() {
        return walls;
    }

    public Walls getWalls() {
        return this;
    }


    public void addWalls(List<Point> walls) {
        this.walls.addAll(walls);
    }

}