package ru.nsu.plodushcheva.model.json;

import java.awt.Point;
import java.util.List;

/**
 * The JsonData class represents the JSON data used for configuring the game.
 * It contains various properties such as width, height, food settings, and wall positions
 * for different levels.
 */
public class JsonData {
    public final int width;
    public final int height;
    public final int columns;
    public final int rows;
    public final int squareSize;
    public final int maxFoodForLevel1;
    public final int maxFoodForLevel2;
    public final int maxFoodForLevel3;

    public final int speedFoodForLevel1;

    public final int speedFoodForLevel2;

    public final int speedFoodForLevel3;
    public final List<Point> wallsFoodForLevel1;
    public final List<Point> wallsFoodForLevel2;
    public final List<Point> wallsFoodForLevel3;


    /**
     * Represents the JSON data used in the game.
     *
     * @param width The width of the game field.
     * @param height The height of the game field.
     * @param columns The number of columns in the game field.
     * @param rows The number of rows in the game field.
     * @param squareSize The size of each square in the game field.
     * @param maxFoodForLevel1 The maximum number of food for level 1.
     * @param maxFoodForLevel2 The maximum number of food for level 2.
     * @param maxFoodForLevel3 The maximum number of food for level 3.
     * @param speedFoodForLevel1 The speed of food for level 1.
     * @param speedFoodForLevel2 The speed of food for level 2.
     * @param speedFoodForLevel3 The speed of food for level 3.
     * @param wallsFoodForLevel1 The list of wall coordinates for level 1.
     * @param wallsFoodForLevel2 The list of wall coordinates for level 2.
     * @param wallsFoodForLevel3 The list of wall coordinates for level 3.
     */
    public JsonData(int width, int height, int columns, int rows, int squareSize,
                    int maxFoodForLevel1, int maxFoodForLevel2, int maxFoodForLevel3,
                    int speedFoodForLevel1, int speedFoodForLevel2, int speedFoodForLevel3,
                    List<Point> wallsFoodForLevel1, List<Point> wallsFoodForLevel2,
                    List<Point> wallsFoodForLevel3) {
        this.width = width;
        this.height = height;
        this.columns = columns;
        this.rows = rows;
        this.squareSize = squareSize;
        this.maxFoodForLevel1 = maxFoodForLevel1;
        this.maxFoodForLevel2 = maxFoodForLevel2;
        this.maxFoodForLevel3 = maxFoodForLevel3;
        this.speedFoodForLevel1 = speedFoodForLevel1;
        this.speedFoodForLevel2 = speedFoodForLevel2;
        this.speedFoodForLevel3 = speedFoodForLevel3;
        this.wallsFoodForLevel1 = wallsFoodForLevel1;
        this.wallsFoodForLevel2 = wallsFoodForLevel2;
        this.wallsFoodForLevel3 = wallsFoodForLevel3;
    }


    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public int getMaxFoodForLevel1() {
        return maxFoodForLevel1;
    }

    public int getMaxFoodForLevel2() {
        return maxFoodForLevel2;
    }

    public int getMaxFoodForLevel3() {
        return maxFoodForLevel3;
    }

    public int getSpeedFoodForLevel1() {
        return speedFoodForLevel1;
    }

    public int getSpeedFoodForLevel2() {
        return speedFoodForLevel2;
    }

    public int getSpeedFoodForLevel3() {
        return speedFoodForLevel3;
    }

    public List<Point> getWallsFoodForLevel1() {
        return wallsFoodForLevel1;
    }

    public List<Point> getWallsFoodForLevel2() {
        return wallsFoodForLevel2;
    }

    public List<Point> getWallsFoodForLevel3() {
        return wallsFoodForLevel3;
    }
}
