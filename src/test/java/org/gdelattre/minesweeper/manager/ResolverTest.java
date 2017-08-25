package org.gdelattre.minesweeper.manager;

import org.gdelattre.minesweeper.model.Cell;
import org.gdelattre.minesweeper.model.Grid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Test the Resolver class..
 */
public class ResolverTest {

    private Resolver resolver;

    private Grid grid;

    @Before
    public void init() {
        grid = new Grid();
        resolver = new Resolver(grid);
    }

    @Test
    public void testValidateWithOutOfBoundsCoordinates() {
        // given
        final int x = 12, y = 13;

        // when
        final boolean isValid = resolver.validate(x, y);

        // then
        Assert.assertEquals(isValid, false);
    }

    @Test
    public void testValidateWithNegativeCoordinates() {
        // given
        final int x = -1, y = 0;

        // when
        final boolean isValid = resolver.validate(x, y);

        // then
        Assert.assertEquals(isValid, false);
    }

    @Test
    public void testValidateWithUncoveredCell() {
        // given
        final int x = 2, y = 2;
        resolver.getGrid().getCells()[1][1].setCovered(false);

        // when
        final boolean isValid = resolver.validate(x, y);

        // then
        Assert.assertEquals(false, isValid);
    }

    @Test
    public void testValid() {
        // given
        final int x =1, y =1;
        resolver.setGrid(new Grid());

        // when
        final boolean isValid = resolver.validate(x, y);

        // then
        Assert.assertEquals(isValid, true);
    }

    @Test
    public void testAdjacentCellsWithLeftUp() {
        // given
        final int x = 1, y = 1;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(3, adjacentCells.length);
        Assert.assertEquals(true,
                            Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                                      cell.getY() == 2));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 1 &&
                                                          cell.getY() == 2));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 1));
    }

    @Test
    public void testAdjacentCellsWithLeftDown() {
        // given
        final int x = 1, y = Grid.height;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(3, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 1 &&
                                                          cell.getY() == Grid.height - 1));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == Grid.height));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == Grid.height - 1));
    }

    @Test
    public void testAdjacentCellsWithRightUp() {
        // given
        final int x = Grid.width, y = 1;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(3, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width &&
                                                          cell.getY() == 2));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == 1));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == 2));
    }

    @Test
    public void testAdjacentCellsWithRightDown() {
        // given
        final int x = Grid.width, y = Grid.height;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(3, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width &&
                                                          cell.getY() == Grid.height - 1));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == Grid.height));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == Grid.height - 1));
    }

    @Test
    public void testAdjacentCellsWithLeft() {
        // given
        final int x = 1, y = 3;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(5, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 1 &&
                                                          cell.getY() == 2));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 1 &&
                                                          cell.getY() == 4));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 2));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 3));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 4));
    }

    @Test
    public void testAdjacentCellsWithRight() {
        // given
        final int x = Grid.width, y = 4;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(5, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width &&
                                                          cell.getY() == 3));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width &&
                                                          cell.getY() == 5));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == 3));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == 4));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == Grid.width - 1 &&
                                                          cell.getY() == 5));
    }

    @Test
    public void testAdjacentCellsWithUp() {
        // given
        final int x = 3, y = 1;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(5, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 1));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == 1));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 2));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 3 &&
                                                          cell.getY() == 2));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == 2));
    }

    @Test
    public void testAdjacentCellsWithDown() {
        // given
        final int x = 3, y = Grid.height;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(5, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == Grid.height));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == Grid.height));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == Grid.height - 1));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 3 &&
                                                          cell.getY() == Grid.height - 1));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == Grid.height - 1));
    }

    @Test
    public void testAdjacentCells() {
        // given
        final int x = 3, y = 4;

        // when
        final Cell[] adjacentCells = resolver.adjacentCells(x, y);

        // then
        Assert.assertEquals(8, adjacentCells.length);
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 3));
        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 4));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 2 &&
                                                          cell.getY() == 5));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 3 &&
                                                          cell.getY() == 3));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 3 &&
                                                          cell.getY() == 5));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == 3));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == 4));

        Assert.assertEquals(true,
                Stream.of(adjacentCells).anyMatch(cell -> cell.getX() == 4 &&
                                                          cell.getY() == 5));
    }

    @Test
    public void testResolveWithZeroMinedAdjacentCells() {
        // given

        resolver.setGrid(new Grid());
        resolver.getGrid().getCells()[1][1].setMined(false);
        resolver.getGrid().getCells()[1][1].setCovered(true);

        resolver.getGrid().getCells()[1][2].setMined(false);
        resolver.getGrid().getCells()[1][2].setCovered(true);

        resolver.getGrid().getCells()[1][3].setMined(false);
        resolver.getGrid().getCells()[1][3].setCovered(true);

        resolver.getGrid().getCells()[2][1].setMined(false);
        resolver.getGrid().getCells()[2][1].setCovered(true);

        // selected cell
        resolver.getGrid().getCells()[2][2].setMined(false);
        resolver.getGrid().getCells()[2][2].setCovered(true);

        resolver.getGrid().getCells()[2][3].setMined(false);
        resolver.getGrid().getCells()[2][3].setCovered(true);

        resolver.getGrid().getCells()[3][1].setMined(false);
        resolver.getGrid().getCells()[3][1].setCovered(true);

        resolver.getGrid().getCells()[3][2].setMined(false);
        resolver.getGrid().getCells()[3][2].setCovered(true);

        resolver.getGrid().getCells()[3][3].setMined(false);
        resolver.getGrid().getCells()[3][3].setCovered(true);

        // when / then
        Assert.assertEquals(0, resolver.resolve(3, 3));
    }

    @Test
    public void testResolveWithSeveralMinedAdjacentCells() {
        // given

        resolver.setGrid(new Grid());
        resolver.getGrid().getCells()[1][1].setMined(true);
        resolver.getGrid().getCells()[1][1].setCovered(true);

        resolver.getGrid().getCells()[1][2].setMined(false);
        resolver.getGrid().getCells()[1][2].setCovered(true);

        resolver.getGrid().getCells()[1][3].setMined(true);
        resolver.getGrid().getCells()[1][3].setCovered(true);

        resolver.getGrid().getCells()[2][1].setMined(false);
        resolver.getGrid().getCells()[2][1].setCovered(true);

        // selected cell
        resolver.getGrid().getCells()[2][2].setMined(false);
        resolver.getGrid().getCells()[2][2].setCovered(true);

        resolver.getGrid().getCells()[2][3].setMined(false);
        resolver.getGrid().getCells()[2][3].setCovered(true);

        resolver.getGrid().getCells()[3][1].setMined(false);
        resolver.getGrid().getCells()[3][1].setCovered(true);

        resolver.getGrid().getCells()[3][2].setMined(true);
        resolver.getGrid().getCells()[3][2].setCovered(true);

        resolver.getGrid().getCells()[3][3].setMined(true);
        resolver.getGrid().getCells()[3][3].setCovered(true);

        // when / then
        Assert.assertEquals(4, resolver.resolve(3, 3));
    }

    @Test
    public void testResolveAndUncover() {
        // given
        final Grid newGrid = new Grid();
        for (int x = 0 ; x < Grid.width ; ++x) {
            for (int y = 0 ; y < Grid.height ; ++y) {
                newGrid.getCells()[x][y].setCovered(true);
                newGrid.getCells()[x][y].setMined(false);
            }
        }
        for (int x = 0 ; x < Grid.width ; ++x) {
            newGrid.getCells()[x][1].setMined(true);
        }
        resolver.setGrid(newGrid);

        // when
        resolver.resolve(5, 5);

        // then
        Assert.assertEquals(20, Arrays.stream(resolver.getGrid().getCells())
                                               .flatMap(Arrays::stream)
                                               .filter(c -> c.isCovered())
                                               .count());
    }

    @Test
    public void testWin() {
        // given
        for (int x = 0 ; x < Grid.width ; ++x) {
            for (int y = 0 ; y < Grid.height ; ++y) {
                if (!resolver.getGrid().getCells()[x][y].isMined()) {
                    resolver.getGrid().getCells()[x][y].setCovered(false);
                }
            }
        }

        // when / then
        Assert.assertEquals(true, resolver.win());
    }

    @Test
    public void testNotWin() {
        // given
        for (int x = 0 ; x < Grid.width ; ++x) {
            for (int y = 0 ; y < Grid.height - 1 ; ++y) {
                if (!resolver.getGrid().getCells()[x][y].isMined()) {
                    resolver.getGrid().getCells()[x][y].setCovered(false);
                }
            }
        }

        // when / then
        Assert.assertEquals(false, resolver.win());
    }
}