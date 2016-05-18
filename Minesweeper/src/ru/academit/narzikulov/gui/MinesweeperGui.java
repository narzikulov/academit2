package ru.academit.narzikulov.gui;

import ru.academit.narzikulov.Minesweeper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton okButton = new JButton("OK");
    private Minesweeper minesweeper = new Minesweeper();
    private Dimension cellSize = new Dimension();

    public MinesweeperGui(int x, int y) {
        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minesweeperFrame.setSize(x, y);
        cellSize.height = 50;
        cellSize.width = 50;

        minesweeperFrame.setLayout(new GridLayout(2, 2));
        rows.setBorder(new TitledBorder("Rows"));
        minesweeperFrame.add(rows);
        columns.setBorder(new TitledBorder("Columns"));
        minesweeperFrame.add(columns);
        numOfMines.setBorder(new TitledBorder("Mines"));
        minesweeperFrame.add(numOfMines);
        minesweeperFrame.add(okButton);

        okButton.addActionListener(new ActionListenerForButton());

/*        gameField.setLayout(new GridLayout(5, 5));
        for (int i = 0; i <= 20; ++i) {
            gameField.add(new JButton());
        }
        minesweeperPanel.add(gameField);*/

    }

    public void setFrame() {
        minesweeperFrame.setVisible(true);
    }

    private class ActionListenerForButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer rowsValue = Integer.valueOf(rows.getText());
            Integer columnsValue = Integer.valueOf(columns.getText());
            Integer numOfMinesValue = Integer.valueOf(numOfMines.getText());

            minesweeperFrame.setSize((int) cellSize.getWidth() * rowsValue, (int) cellSize.getHeight() * columnsValue);
            minesweeperPanel.setSize((int) cellSize.getWidth() * rowsValue, (int) cellSize.getHeight() * columnsValue);
            minesweeperPanel.setLayout(new GridLayout(rowsValue, columnsValue, 5, 5));
            for (int i = 1; i <= rowsValue * columnsValue; ++i) {
                JButton button = new JButton(new String("" + i));
                button.setSize(cellSize);
                minesweeperPanel.add(button);
            }
            minesweeperFrame.setContentPane(minesweeperPanel);
        }
    }
}
