package ru.academit.narzikulov.minesweeper;

/**
 * Created by tim on 24.05.2016.
 */
public class Cell {
    private int minesAround;
    private boolean isMine;
    private boolean isMineFound;
    private boolean isOpen;
    private boolean underQuestion;

    public Cell(int minesAround) {
        this.minesAround = minesAround;
        isMine = false;
        isMineFound = false;
        isOpen = false;
        underQuestion = false;
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

    public void setUnderQuestion(boolean underQuestion) {
        this.underQuestion = underQuestion;
    }

    public boolean getUnderQuestion() {
        return underQuestion;
    }
}

