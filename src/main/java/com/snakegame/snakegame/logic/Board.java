package com.snakegame.snakegame.logic;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class Board {
    public static final int SIZE = 10;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Color COLOR = new Color(0.1, 0.1, 0.1, 1);
    public static final Color FOOD_COLOR = new Color(1,1,0.3, 1);
    private final Snake snake;
    private Cell food;

    public Board() {
        this.snake = new Snake();
        this.food = generateFood();
    }

    public static int getRows() {
        return WIDTH / SIZE;
    }

    public static int getCols() {
        return HEIGHT / SIZE;
    }

    public Cell generateFood() {
        Cell food;
        do {
            food = new Cell(getRandomRow(), getRandomCol(), CellType.FOOD);
        } while (snake.checkFood(food));
        return food;
    }

    public void growSnake() {
        snake.setHead(food);
        snake.getBody().add(food);
        food = generateFood();
    }

    private int getRandomRow() {
        return (int) (Math.random() * ((getRows())));
    }

    private int getRandomCol() {
        return (int) (Math.random() * ((getCols())));
    }
}
