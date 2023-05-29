package ru.nsu.plodushcheva.view;

/**
 * Represents the game field in the Snake game.
 */
public class GameField {
    private final int width;
    private final int height;
    private final int columns;
    private final int rows;
    private final int POINT_SIZE;

    public GameField(int width, int height, int columns, int rows, int point_size) {
        this.width = width;
        this.height = height;
        this.columns = columns;
        this.rows = rows;
        this.POINT_SIZE = point_size;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


    public int getPOINT_SIZE() {
        return POINT_SIZE;
    }
}
