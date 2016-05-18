package ru.academit.narzikulov.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperGui {
    private JFrame minesweeperFrame = new JFrame("Minesweeper");
    private JPanel minesweeperPanel = new JPanel();
    private JTextField rows = new JTextField();
    private JTextField columns = new JTextField();
    private JTextField numOfMines = new JTextField();
    private JPanel gameField = new JPanel();

    public MinesweeperGui(int x, int y) {
        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minesweeperFrame.setSize(x, y);
        minesweeperPanel.setSize(x, y);
        minesweeperFrame.add(minesweeperPanel);
        minesweeperFrame.setContentPane(minesweeperPanel);

        minesweeperPanel.setLayout(new GridLayout(2, 1));

        Box panelForSizeAndMinesNumTextFields = Box.createHorizontalBox();
        rows.setBorder(new TitledBorder("Rows"));
        panelForSizeAndMinesNumTextFields.add(rows);
        columns.setBorder(new TitledBorder("Columns"));
        panelForSizeAndMinesNumTextFields.add(columns);
        numOfMines.setBorder(new TitledBorder("Mines"));
        panelForSizeAndMinesNumTextFields.add(numOfMines);
        minesweeperPanel.add(panelForSizeAndMinesNumTextFields);

        gameField.setLayout(new GridLayout(5, 5));
        for (int i = 0; i <= 20; ++i) {
            gameField.add(new JButton());
        }
        minesweeperPanel.add(gameField);

    }

    public void setFrame() {
        minesweeperFrame.setVisible(true);
    }
}
