package ru.academit.narzikulov.gui;

import ru.academit.narzikulov.Cell;

import javax.swing.*;

/**
 * Created by tim on 08.06.2016.
 */
public class GuiCell extends JButton {
    private int i;
    private int j;

    public GuiCell(String str, int i, int j) {
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
