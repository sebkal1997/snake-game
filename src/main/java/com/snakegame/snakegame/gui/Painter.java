package com.snakegame.snakegame.gui;

import static com.snakegame.snakegame.logic.Board.FOOD_COLOR;
import static com.snakegame.snakegame.logic.Board.HEIGHT;
import static com.snakegame.snakegame.logic.Board.SIZE;
import static com.snakegame.snakegame.logic.Board.WIDTH;

import com.snakegame.snakegame.logic.Board;
import com.snakegame.snakegame.logic.Cell;
import com.snakegame.snakegame.logic.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {

    public static void paint(Board board, GraphicsContext graphicsContext) {
        //Fill board with empty cells
        graphicsContext.setFill(Board.COLOR);
        graphicsContext.fillRect(0, 0, Board.WIDTH, Board.HEIGHT);
        //Draw snake
        graphicsContext.setFill(Snake.BODY_COLOR);
        for (Cell bodyPart : board.getSnake().getBody()) {
            paintCell(bodyPart, graphicsContext);
        }
        if (board.getSnake().isDead()) {
            graphicsContext.setFill(Snake.DEAD_COLOR);
            paintCell(board.getSnake().getHead(), graphicsContext);
        }
        //Draw food
        graphicsContext.setFill(FOOD_COLOR);
        paintCell(board.getFood(), graphicsContext);

        //Display score
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("Score: " + (board.getSnake().getBody().size() * 10 - 10), 10, Board.HEIGHT - 10);
    }

    public static void paintGameOver(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("GameOver!", WIDTH/2.0 - 30, HEIGHT/2.0);
        graphicsContext.fillText("Press ENTER to start again...", WIDTH/2.0 - 70, HEIGHT/2.0 + 20);
    }

    private static void paintCell(Cell cell, GraphicsContext graphicsContext) {
        graphicsContext.fillRect(cell.getRow() * SIZE, cell.getCol() * SIZE, SIZE, SIZE);
    }
}
