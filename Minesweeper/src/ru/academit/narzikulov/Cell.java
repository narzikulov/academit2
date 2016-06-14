package ru.academit.narzikulov;

/**
 * Created by tim on 24.05.2016.
 */
public class Cell {
    private int minesAround;
    private boolean isMine;
    private boolean isMineFound;
    private boolean isOpen;

    public Cell(int minesAround) {
        this.minesAround = minesAround;
        isMine = false;
        isMineFound = false;
        isOpen = false;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsMineFound(boolean isMineFound) {
        isOpen = false;
        this.isMineFound = isMineFound;
    }

    public boolean getIsMineFound() {
        return isMineFound;
    }
}

