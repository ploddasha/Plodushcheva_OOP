package ru.nsu.plodushcheva.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * The Walls class represents a collection of walls in the game.
 * Walls are represented as a list of points on the game grid.
 */
public class Walls {
    private final List<Point> walls;

    /**
     * Constructs an empty Walls object.
     */
    public Walls() {
        this.walls = new ArrayList<>();
    }


    /**
     * Returns the list of walls.
     *
     * @return The list of walls.
     */
    public List<Point> getWallsList() {
        return walls;
    }

    /**
     * Returns the Walls object itself.
     *
     * @return The Walls object.
     */
    public Walls getWalls() {
        return this;
    }


    /**
     * Adds the specified list of walls to the current collection of walls.
     *
     * @param walls The list of walls to be added.
     */
    public void addWalls(List<Point> walls) {
        this.walls.addAll(walls);
    }

}