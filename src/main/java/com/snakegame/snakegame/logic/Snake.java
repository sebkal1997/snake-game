package com.snakegame.snakegame.logic;

import javafx.scene.paint.Color;
import lombok.Data;

import java.util.LinkedList;

@Data
public class Snake {
    public static final Color BODY_COLOR = new Color(0, 0.7, 0.3, 1);
    public static final Color DEAD_COLOR = new Color(1, 0, 0.3, 1);
    private Direction direction = Direction.NOT_SET;
    private LinkedList<Cell> body = new LinkedList<>();
    private Cell head;
    private boolean dead;

    public Snake() {
        this.head = new Cell(Board.getRows()/2, Board.getCols()/2, CellType.SNAKE);
        body.add(head);
    }

    public void move() {
        Cell newHead;
        switch (direction){
            case TOP:
                newHead = new Cell(head.getRow(), head.getCol() - 1, CellType.SNAKE);
                if (eatSelfOrHitWall(newHead)) {
                    dead = true;
                    return;
                }
                head = newHead;
                break;
            case DOWN:
                newHead = new Cell(head.getRow(), head.getCol() + 1, CellType.SNAKE);
                if (eatSelfOrHitWall(newHead)) {
                    dead = true;
                    return;
                }
                head = newHead;
                break;
            case LEFT:
                newHead = new Cell(head.getRow() - 1, head.getCol(), CellType.SNAKE);
                if (eatSelfOrHitWall(newHead)) {
                    dead = true;
                    return;
                }
                head = newHead;
                break;
            case RIGHT:
                newHead = new Cell(head.getRow() + 1, head.getCol(), CellType.SNAKE);
                if (eatSelfOrHitWall(newHead)) {
                    dead = true;
                    return;
                }
                head = newHead;
                break;
            default:
                break;
        }
        body.add(head);
        body.removeFirst();
    }

    private boolean eatSelfOrHitWall(Cell cell) {
        if (Board.getCols() == cell.getCol()) return true;
        else if (Board.getRows() == cell.getRow()) return true;
        else if (0 > cell.getCol()) return true;
        else if (0 > cell.getRow()) return true;
        for (Cell bodyPart : body) {
            if (bodyPart.equals(cell)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkFood(Cell food) {
        for (Cell bodyPart : body) {
            if (bodyPart.compareByPosition(food)) {
                return true;
            }
        }
        return false;
    }
}
