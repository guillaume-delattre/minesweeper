package org.gdelattre.minesweeper.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the Grid class.
 */
public class GridTest {

    @Test
    public void testInit() {
        // given / when
        final Grid grid = new Grid();
        // then
        int numberMinedCells = 0;
        int numberCoveredCells = 0;
        for (int i = 0 ; i < Grid.width ; ++i) {
            for (int j = 0 ; j < Grid.height ; ++j) {
                Assert.assertEquals(grid.getCells()[i][j].getX(), i + 1);
                Assert.assertEquals(grid.getCells()[i][j].getY(), j + 1);
                if (grid.getCells()[i][j].isCovered()) {
                    ++numberCoveredCells;
                }
                if (grid.getCells()[i][j].isMined()) {
                    ++numberMinedCells;
                }
            }
        }
        Assert.assertEquals(numberMinedCells, Grid.numberMines);
        Assert.assertEquals(Grid.height * Grid.width, numberCoveredCells);
    }

}