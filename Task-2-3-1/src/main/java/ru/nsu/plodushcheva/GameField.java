package ru.nsu.plodushcheva;

public class GameField {
    private final int WIDTH;
    private final int HEIGHT;
    private final int COLUMNS;
    private final int ROWS;
    private final int POINT_SIZE;

    /**
     * Constructor for the game field
     *
     * @param width      - width of the game field on X axis
     * @param height     - height of the game on Y axis
     * @param columns    - amount of columns
     * @param rows       - amount of the rows
     * @param point_size - point size
     */
    public GameField(int width, int height, int columns, int rows, int point_size) {
        WIDTH = width;
        HEIGHT = height;
        COLUMNS = columns;
        ROWS = rows;
        POINT_SIZE = point_size;
    }

    /**
     * Getter for WIDTH
     *
     * @return size of the field on X axis
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * Getter for WIDTH
     *
     * @return size of the field on Y axis
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * Getter for COLUMNS
     *
     * @return amount of columns
     */
    public int getCOLUMNS() {
        return COLUMNS;
    }

    /**
     * Getter for ROWS
     *
     * @return amount of rows
     */
    public int getROWS() {
        return ROWS;
    }

    /**
     * Getter for POINT_SIZE
     *
     * @return size of the point
     */
    public int getPOINT_SIZE() {
        return POINT_SIZE;
    }
}
