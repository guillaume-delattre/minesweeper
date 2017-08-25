package org.gdelattre.minesweeper.manager;

import org.gdelattre.minesweeper.model.Cell;
import org.gdelattre.minesweeper.model.Grid;

import java.util.Arrays;

/**
 * Minesweeper resolver class.
 */
public class Resolver {

    public static final int INVALID_COORDINATES = - 3;

    public static final int SUCCESS = - 2;

    public static final int GAME_OVER = -1;

    private Grid grid;

    public Resolver(Grid grid) {
        this.grid = grid;
    }

    /**
     * Validates the coordinates values depending on the grid's size.
     * @param x
     * @param y
     * @return true if coordinates values are valid
     */
    protected boolean validate(final int x, final int y) {
        if (x <= 0 || y <= 0) {
            return false;
        }
        if (x > Grid.width || y > Grid.height) {
            return false;
        }
        return grid.getCells()[x - 1][y - 1].isCovered();
    }

    /**
     * Return all the adjacent cells of the given coordinates.
     * @param x
     * @param y
     * @return an array of the adjacent cells
     */
    protected Cell[] adjacentCells(final int x, final int y) {
        if (x == 1 && y == 1) {
            return new Cell[] { grid.getCells()[0][1],
                                grid.getCells()[1][1],
                                grid.getCells()[1][0] };
        }

        if (x == Grid.width && y == 1) {
            return new Cell[] { grid.getCells()[(Grid.width - 1) - 1][0],
                                grid.getCells()[(Grid.width - 1) - 1][1],
                                grid.getCells()[Grid.width - 1][1] };
        }

        if (x == 1 && y == Grid.height) {
            return new Cell[] { grid.getCells()[0][(Grid.height - 1) - 1],
                                grid.getCells()[1][(Grid.height - 1) - 1],
                                grid.getCells()[1][Grid.height - 1] };
        }

        if (x == Grid.width && y == Grid.height) {

            return new Cell[] { grid.getCells()[(Grid.width - 1) - 1][Grid.height - 1],
                                grid.getCells()[(Grid.width - 1) - 1][(Grid.height - 1) - 1],
                                grid.getCells()[Grid.width - 1][(Grid.height - 1) - 1] };
        }

        if (x == 1) {
            return new Cell[]{ grid.getCells()[x - 1][(y - 1) - 1],
                               grid.getCells()[x - 1][(y - 1) + 1],
                               grid.getCells()[(x - 1) + 1][(y - 1) - 1],
                               grid.getCells()[(x -1) + 1][(y - 1)],
                               grid.getCells()[(x - 1) + 1][(y - 1) + 1]};
        }
        if (x == Grid.width) {
            return new Cell[]{ grid.getCells()[Grid.width - 1][(y - 1) - 1],
                               grid.getCells()[Grid.width - 1][(y - 1) + 1],
                               grid.getCells()[(Grid.width - 1) - 1][(y - 1) - 1],
                               grid.getCells()[(Grid.width - 1) - 1][(y - 1)],
                               grid.getCells()[(Grid.width - 1) - 1][(y - 1) + 1] };
        }
        if (y == 1) {
            return new Cell[]{ grid.getCells()[(x - 1) - 1][y - 1],
                               grid.getCells()[(x - 1) + 1][y - 1],
                               grid.getCells()[(x - 1) - 1][(y - 1) + 1],
                               grid.getCells()[x - 1][(y - 1) + 1],
                               grid.getCells()[(x - 1) + 1][(y - 1) + 1] };
        }

        if (y == Grid.height) {
            return new Cell[]{ grid.getCells()[(x - 1) - 1][(Grid.height - 1)],
                               grid.getCells()[(x - 1) + 1][(Grid.height - 1)],
                               grid.getCells()[(x - 1) - 1][(Grid.height - 1) - 1],
                               grid.getCells()[x - 1][(Grid.height - 1) - 1],
                               grid.getCells()[(x - 1) + 1][(Grid.height - 1) - 1] };
        }

        return new Cell[]{ grid.getCells()[(x - 1) - 1][(y - 1) - 1],
                           grid.getCells()[x - 1][(y - 1) - 1],
                           grid.getCells()[(x - 1) + 1][(y - 1) - 1],
                           grid.getCells()[(x - 1) - 1][y - 1],
                           grid.getCells()[(x - 1) + 1][y - 1],
                           grid.getCells()[(x - 1) - 1][(y - 1) + 1],
                           grid.getCells()[x - 1][(y - 1) + 1],
                           grid.getCells()[(x - 1) + 1][(y - 1) + 1] };
    }

    /**
     * Return true if the cell defined by (x, y) is mined.
     * @param x
     * @param y
     * @return true if mined
     */
    private boolean holdsAMine(final int x, final int y) {
        return grid.getCells()[x - 1][y - 1].isMined();
    }

    /**
     * Return the number of mined adjacent cells of the given coordinates.
     * @param x
     * @param y
     * @return
     */
    public int resolve(final int x, final int y) {
        if (!validate(x, y)) {
            return INVALID_COORDINATES;
        }

        final Cell selectedCell = grid.getCells()[x - 1][y -1];
        selectedCell.setCovered(false);

        if (holdsAMine(x, y)) {
            return GAME_OVER;
        }

        final Cell[] adjacentCells = adjacentCells(x, y);

        final int numberMinedAdjacentCells = (int) Arrays.stream(adjacentCells)
                .filter(Cell::isMined)
                .count();

        if (numberMinedAdjacentCells > 0) {
            return numberMinedAdjacentCells;
        }

        uncover(adjacentCells);

        if (win()) {
            return SUCCESS;
        }

        return numberMinedAdjacentCells;
    }

    protected void uncover(final Cell[] adjacentCells) {
        for (Cell cell : adjacentCells) {
            resolve(cell.getX(), cell.getY());
        }
    }

    /**
     * Returns true if the game ends with success.
     * @return
     */
    public boolean win() {
        final int numberUncoveredCells = (int) Arrays.stream(grid.getCells())
                                                     .flatMap(Arrays::stream)
                                                     .filter(cell -> !cell.isCovered() &&
                                                                     !cell.isMined())
                                                     .count();

        return numberUncoveredCells + Grid.numberMines == Grid.height * Grid.width;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
