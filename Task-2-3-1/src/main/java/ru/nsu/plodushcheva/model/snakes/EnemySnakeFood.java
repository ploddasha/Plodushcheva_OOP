package ru.nsu.plodushcheva.model.snakes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.plodushcheva.model.Food;
import ru.nsu.plodushcheva.view.GameField;
import ru.nsu.plodushcheva.model.Walls;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.nsu.plodushcheva.model.snakes.EnemySnakeFood.Direction.*;


public class EnemySnakeFood {
    private final GameField gameField;
    private final Food food;
    private List<Point> snake;
    private Point snakeHead;
    private final int ROWS;
    private final int COLUMNS;
    private final Walls walls;
    boolean gameOver = false;
    private int score;


    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    private EnemySnakeFood.Direction currentDirection;

    public EnemySnakeFood(GameField gameField, Food food, Walls walls) {
        this.gameField = gameField;
        this.food = food;
        this.walls = walls;
        this.ROWS = gameField.getROWS();
        this.COLUMNS = gameField.getCOLUMNS();
        snake = new ArrayList<>();
        initSnake();
    }

    public void run() {
        if (!gameOver) {
            crawling();
            movingNext();
            eatFood();
        } else {
            snake = new ArrayList<>();
            initSnake();
            gameOver = false;
        }

    }

    public void crawling() {
        if (snake.size() > 1) {
            Point crawling = snake.get(snake.size() - 1);
            crawling.x = snakeHead.x;
            crawling.y = snakeHead.y;
            snake.add(1, crawling);
            snake.remove(snake.size() - 1);
        }
    }

    Point goalFood;

    public void movingNext() {
        List<Point> foods = food.getFood();
        if (goalFood == null || !foods.contains(goalFood)) {
            goalFood = getRandomFood(foods);
        }

        if (goalFood.x > snakeHead.x) {
            currentDirection = RIGHT;
        } else if (goalFood.x == snakeHead.x) {
            if (goalFood.y > snakeHead.y) {
                currentDirection = DOWN;
            } else if (goalFood.y  < snakeHead.y) {
                currentDirection = UP;
            }
        } else {
            currentDirection = LEFT;
        }
        Direction direction;
        List<Direction> directions = directions();
        if (!directions.contains(currentDirection)) {
            direction = getRandomDirection(directions);
            //System.out.println("from directions " + direction);
        } else {
            direction = currentDirection;
            //System.out.println("from current " + direction);
        }
        if (direction != null) {
            switch (direction) {
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case UP -> moveUp();
                case DOWN -> moveDown();
            }
        } else {
            System.err.println("EnemySnakeFood should die");
            gameOver = true;
        }
    }

    private Point getRandomFood(List<Point> foods) {
        int randomIndex = new Random().nextInt(foods.size());
        return foods.get(randomIndex);
    }

    private List<Direction> directions () {

        List<Direction> directions = new ArrayList<>();

        Direction randomDirection;

        randomDirection = RIGHT;
        Point point = new Point(snakeHead.x + 1, snakeHead.y);
        if (currentDirection != LEFT && noWall(point) && notBorder(point) && notSelf(point) ) {
            directions.add(randomDirection);
        }

        randomDirection = LEFT;
        point = new Point(snakeHead.x - 1, snakeHead.y);
        if (currentDirection != RIGHT && noWall(point) && notBorder(point) && notSelf(point)) {
            directions.add(randomDirection);
        }

        randomDirection = UP;
        point = new Point(snakeHead.x, snakeHead.y - 1);
        if (currentDirection != DOWN && noWall(point) && notBorder(point) && notSelf(point)) {
            directions.add(randomDirection);
        }

        randomDirection = DOWN;
        point = new Point(snakeHead.x, snakeHead.y + 1);
        if (currentDirection != UP && noWall(point) && notBorder(point) && notSelf(point)) {
            directions.add(randomDirection);
        }


        return directions;
    }

    public static Direction getRandomDirection(List<Direction> directions) {
        if (directions.size() > 0) {
            int randomIndex = new Random().nextInt(directions.size());
            return directions.get(randomIndex);
        } else {
            return null;
        }
    }

    private boolean notSelf(Point point) {
        for (Point value : snake) {
            if (point.getX() == value.getX() && point.getY() == value.getY()) {
                return false;
            }
        }
        return true;
    }

    private boolean notBorder(Point point) {
        return point.getX() != -1 && point.getX() != COLUMNS
                && point.getY() != -1 && point.getY() != ROWS;
    }

    private boolean noWall(Point point) {
        for (int i = 0 ; i < walls.getWallsList().size(); i++) {
            if (point.getX() == walls.getWallsList().get(i).getX() &&
                    point.getY() == walls.getWallsList().get(i).getY()) {
                return false;
            }
        }
        return true;
    }



    public void drawSnake(GraphicsContext gc) {

        gc.setFill(Color.web("FA7921"));

        for (Point point : snake) {
            gc.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE() - 1,
                    gameField.getPOINT_SIZE() - 1,
                    25, 25);
        }


    }

    private void initSnake() {
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(29, i + ROWS / 2));
        }
        snakeHead = snake.get(0);

    }

    public void eatFood() {
        for (int i = 0; i < food.getFood().size(); i++) {
            if (snakeHead.getX() == food.getFood().get(i).getX()
                    && snakeHead.getY() == food.getFood().get(i).getY()) {
                food.getFood().remove(i);
                food.generateFood(walls);
                snake.add(new Point(-1, -1));
                score++;
            }
        }
    }

    public void moveRight() {
        currentDirection = RIGHT;
        snakeHead.x++;
    }

    public void moveLeft() {
        currentDirection = LEFT;
        snakeHead.x--;
    }

    public void moveUp() {
        currentDirection = UP;
        snakeHead.y--;
    }

    public void moveDown() {
        currentDirection = DOWN;
        snakeHead.y++;

    }



    public List<Point> getSnake() {
        return snake;
    }
    public Point getSnakeHead() {
        return snakeHead;
    }
    public int getScore() {
        return score;
    }
    public void setGameOver() {
        gameOver = true;
    }

}
