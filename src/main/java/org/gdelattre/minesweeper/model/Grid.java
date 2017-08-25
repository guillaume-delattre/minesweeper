package org.gdelattre.minesweeper.model;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * A grid is defined with a height and a width
 */
public class Grid {

    public static final int width = 10;

    public static final int height = 10;

    public static final int numberMines = 10;

    private Cell[][] cells;

    public Grid() {
        // cells initialization
        initCells();

        // mines initialization
        initMines();
    }

    private void initCells() {
        cells = new Cell[width][height];

        IntStream.range(0, width).forEach(x ->
                IntStream.range(0, height).forEach(y ->
                        cells[x][y] = new Cell(x + 1, y + 1)));
    }

    private void initMines() {
        int count = 0;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (count == numberMines) {
                    return;
                }
                final boolean isMined = new Random().nextBoolean();
                if (isMined) {
                    cells[i][j].setMined(isMined);
                    ++count;
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
