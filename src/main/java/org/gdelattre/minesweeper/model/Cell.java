package org.gdelattre.minesweeper.model;

/**
 * A cell is defined with (x,y) 2D-coordinates and has 2 states (mined or not, covered or not).
 */
public class Cell {

    private int x;

    private int y;

    private boolean mined;

    private boolean covered;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.covered = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCovered() {
        return covered;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }
}
