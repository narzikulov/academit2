package ru.academit.narzikulov;

import javax.swing.*;

/**
 * Created by tim on 24.05.2016.
 */
public class Cell {
    private int minesAround;
    private boolean isMine;
    private boolean isOpen;
    private JButton guiMineFiled = new JButton();

    public Cell(int minesAround) {
        this.minesAround = minesAround;
        isMine = false;
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

    public JButton getGuiMineFiled() {
        return guiMineFiled;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

}

