package ru.nsu.plodushcheva.model.json;

import java.awt.Point;
import java.util.List;

/**
 * The JsonData class represents the JSON data used for configuring the game.
 * It contains various properties such as width, height, food settings, and wall positions
 * for different levels.
 *
 * @param width              The width of the game field.
 * @param height             The height of the game field.
 * @param columns            The number of columns in the game field.
 * @param rows               The number of rows in the game field.
 * @param squareSize         The size of each square in the game field.
 * @param maxFoodForLevel1   The maximum number of food for level 1.
 * @param maxFoodForLevel2   The maximum number of food for level 2.
 * @param maxFoodForLevel3   The maximum number of food for level 3.
 * @param speedFoodForLevel1 The speed of food for level 1.
 * @param speedFoodForLevel2 The speed of food for level 2.
 * @param speedFoodForLevel3 The speed of food for level 3.
 * @param wallsFoodForLevel1 The list of wall coordinates for level 1.
 * @param wallsFoodForLevel2 The list of wall coordinates for level 2.
 * @param wallsFoodForLevel3 The list of wall coordinates for level 3.
 */
public record JsonData(int width, int height, int columns, int rows, int squareSize,
                       int maxFoodForLevel1, int maxFoodForLevel2, int maxFoodForLevel3,
                       int speedFoodForLevel1, int speedFoodForLevel2, int speedFoodForLevel3,
                       List<Point> wallsFoodForLevel1, List<Point> wallsFoodForLevel2,
                       List<Point> wallsFoodForLevel3) {
}
