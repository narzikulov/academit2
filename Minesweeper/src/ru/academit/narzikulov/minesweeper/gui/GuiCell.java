package ru.academit.narzikulov.minesweeper.gui;

import javax.swing.*;

/**
 * Created by tim on 08.06.2016.
 */
class GuiCell extends JButton {
    //В ячейке минного поля хранятся координаты
    private int i;
    private int j;

    GuiCell(String str, int i, int j) {
        super(str);
        this.i = i;
        this.j = j;
    }

    public GuiCell(String str) {
        super(str);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
