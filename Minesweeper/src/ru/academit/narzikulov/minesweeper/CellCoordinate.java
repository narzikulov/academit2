package ru.academit.narzikulov.minesweeper;

/**
 * Created by tim on 23.05.2016.
 */
public class CellCoordinate {
    private int i;
    private int j;

    public CellCoordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public CellCoordinate() {
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String toString() {
        return String.format("i = %d, j = %d; ", i, j);
    }
}
