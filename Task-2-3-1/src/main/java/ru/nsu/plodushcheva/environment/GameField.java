package ru.nsu.plodushcheva.environment;

public class GameField {
    private final int WIDTH;
    private final int HEIGHT;
    private final int COLUMNS;
    private final int ROWS;
    private final int POINT_SIZE;

    public GameField(int width, int height, int columns, int rows, int point_size) {
        WIDTH = width;
        HEIGHT = height;
        COLUMNS = columns;
        ROWS = rows;
        POINT_SIZE = point_size;
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getROWS() {
        return ROWS;
    }


    public int getPOINT_SIZE() {
        return POINT_SIZE;
    }
}
