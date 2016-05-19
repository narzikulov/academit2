package ru.academit.narzikulov.gui;

import ru.academit.narzikulov.Minesweeper;
import ru.academit.narzikulov.text.MinesweeperText;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperGui {
    private JFrame minesweeperFrame = new JFrame("Minesweeper");
    private JPanel minesweeperPanel = new JPanel();
    private JTextField rows = new JTextField();
    int rowsValue;
    private JTextField columns = new JTextField();
    int columnsValue;
    private JTextField numOfMines = new JTextField();
    int numOfMinesValue;
    private JPanel gameField = new JPanel();
    private JButton okButton = new JButton("OK");
    private Minesweeper minesweeper = new Minesweeper();
    private Dimension cellSize = new Dimension();
    private ArrayList<ArrayList<JButton>> mineFieldButtons = new ArrayList<ArrayList<JButton>>();

    public MinesweeperGui(int x, int y) {
        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minesweeperFrame.setSize(x, y);
        cellSize.height = 50;
        cellSize.width = 50;

        //Значения по умолчанию
        rows.setText("9");
        columns.setText("9");
        numOfMines.setText("10");

        minesweeperFrame.setLayout(new GridLayout(2, 2));
        rows.setBorder(new TitledBorder("Rows"));
        minesweeperFrame.add(rows);
        columns.setBorder(new TitledBorder("Columns"));
        minesweeperFrame.add(columns);
        numOfMines.setBorder(new TitledBorder("Mines"));
        minesweeperFrame.add(numOfMines);
        minesweeperFrame.add(okButton);

        okButton.addActionListener(new ActionListenerForButton());

    }

    public void setFrame() {
        minesweeperFrame.setVisible(true);
    }

    private void fillTheMineField() {
        //Заполнение списка кнопок игрового поля
        minesweeper = new Minesweeper(rowsValue, columnsValue, numOfMinesValue);
        for (int i = 0; i < rowsValue; ++i) {
            mineFieldButtons.add(i, new ArrayList<JButton>());
            for (int j = 0; j < columnsValue; ++j) {
                /*if (minesweeper.getMineField().get(i).get(j) == 1) {
                    mineFieldButtons.get(i).add(j, new JButton("1"));
                } else*/
                    mineFieldButtons.get(i).add(j, new JButton());
            }
        }
        //debug OK
        //MinesweeperText.printMineField(minesweeper);
    }

    private class ActionListenerForButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            rowsValue = Integer.valueOf(rows.getText());
            columnsValue = Integer.valueOf(columns.getText());
            numOfMinesValue = Integer.valueOf(numOfMines.getText());

            //Заполнение двумерного списка кнопок типа JButton игрового поля
            fillTheMineField();

            minesweeperFrame.setSize((int) cellSize.getWidth() * rowsValue, (int) cellSize.getHeight() * columnsValue);
            minesweeperPanel.setSize((int) cellSize.getWidth() * rowsValue, (int) cellSize.getHeight() * columnsValue);
            minesweeperPanel.setLayout(new GridLayout(rowsValue, columnsValue, 5, 5));

            //Заполнение игрового поля кнопками
            for (int i = 0; i < rowsValue; ++i) {
                for (int j = 0; j < columnsValue; ++j) {
                    minesweeperPanel.add(mineFieldButtons.get(i).get(j));
                    mineFieldButtons.get(i).get(j).addActionListener(new ActionListenerForMineFieldButtons());
                }
            }
            minesweeperFrame.setContentPane(minesweeperPanel);
        }
    }

    private class ActionListenerForMineFieldButtons implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //int rowClicked = mineFieldButtons.
            //int columnClicked =
            //if (minesweeper.getMineField())
        }
    }
}
