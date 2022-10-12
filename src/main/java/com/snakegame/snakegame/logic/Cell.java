package com.snakegame.snakegame.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Cell {
    private int row;
    private int col;
    private CellType cellType;

    public boolean compareByPosition(Cell cell) {
        return this.getRow() == cell.getRow() && this.getCol() == cell.getCol();
    }
}
