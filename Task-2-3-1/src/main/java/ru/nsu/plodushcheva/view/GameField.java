package ru.nsu.plodushcheva.view;

/**
 * Represents the game field in the Snake game.
 */
public class GameField {
    private final int width;
    private final int height;
    private final int columns;
    private final int rows;
    private final int pointSize;

    /**
     * Constructs an instance of the GameField class.
     *
     * @param width the width of the game field
     * @param height the height of the game field
     * @param columns the number of columns in the game field
     * @param rows the number of rows in the game field
     * @param pointSize the size of each point in the game field
     */
    public GameField(int width, int height, int columns, int rows, int pointSize) {
        this.width = width;
        this.height = height;
        this.columns = columns;
        this.rows = rows;
        this.pointSize = pointSize;
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


    public int getPointSize() {
        return pointSize;
    }
}
