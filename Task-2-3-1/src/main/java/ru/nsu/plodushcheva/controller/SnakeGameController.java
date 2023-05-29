package ru.nsu.plodushcheva.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import ru.nsu.plodushcheva.SnakeGame.Direction;


/**
 * Controller for the Snake game.
 */
public class SnakeGameController {
    private Direction newDirection;

    /**
     * Handles the level selection buttons.
     *
     * @param buttonLevel The button for level 1.
     * @param buttonLevel2 The button for level 2.
     * @param buttonLevel3 The button for level 3.
     * @return The label indicating the chosen level.
     */
    public Label handleLevelButton(Button buttonLevel, Button buttonLevel2, Button buttonLevel3) {
        Label level = new Label("Choose your level");
        buttonLevel.setText("Choose level 1");

        buttonLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level.setText("You chose level 1");
            }
        });

        buttonLevel2.setText("Choose level 2");
        buttonLevel2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level.setText("You chose level 2");
            }
        });

        buttonLevel3.setText("Choose level 3");
        buttonLevel3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level.setText("You chose level 3");
            }
        });
        return level;
    }

    /**
     * Handles key press events to determine the new direction of the snake.
     *
     * @param code The KeyCode corresponding to the pressed key.
     * @param direction The current direction of the snake.
     * @return The new direction of the snake.
     */
    public Direction handleKeyPress(KeyCode code, Direction direction) {
        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            if (direction != Direction.LEFT) {
                newDirection = Direction.RIGHT;
            }
        } else if (code == KeyCode.LEFT || code == KeyCode.A) {
            if (direction != Direction.RIGHT) {
                newDirection = Direction.LEFT;
            }
        } else if (code == KeyCode.UP || code == KeyCode.W) {
            if (direction != Direction.DOWN) {
                newDirection = Direction.UP;
            }
        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
            if (direction != Direction.UP) {
                newDirection = Direction.DOWN;
            }
        }
        return newDirection;
    }

}

