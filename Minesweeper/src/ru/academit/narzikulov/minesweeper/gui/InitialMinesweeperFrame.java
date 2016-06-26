package ru.academit.narzikulov.minesweeper.gui;

import ru.academit.narzikulov.minesweeper.Minesweeper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tim on 21.06.2016.
 */
public class InitialMinesweeperFrame {
    private final static int FRAME_X_SIZE = 250;
    private final static int FRAME_Y_SIZE = 200;

    private JFrame initialFrame = new JFrame("Minesweeper");

    private JTextField rows = new JTextField();
    private JTextField columns = new JTextField();
    private JTextField minesNum = new JTextField();

    //size of the screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public InitialMinesweeperFrame() {
        initialFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initialFrame.setLocation((int) screenSize.getWidth() / 2 - FRAME_X_SIZE, (int) screenSize.getHeight() / 3 - FRAME_Y_SIZE);
        initialFrame.setMinimumSize(new Dimension(FRAME_X_SIZE, FRAME_Y_SIZE));
        initialFrame.setSize(FRAME_X_SIZE, FRAME_Y_SIZE);

        //Значения по умолчанию
        rows.setText(Integer.toString(Minesweeper.ROWS));
        rows.setHorizontalAlignment(SwingConstants.CENTER);
        rows.select(0, rows.getText().length());

        columns.setText(Integer.toString(Minesweeper.COLUMNS));
        columns.setHorizontalAlignment(SwingConstants.CENTER);
        columns.select(0, columns.getText().length());

        minesNum.setText(Integer.toString(Minesweeper.MINES));
        minesNum.setHorizontalAlignment(SwingConstants.CENTER);
        minesNum.select(0, minesNum.getText().length());

        initialFrame.setLayout(new GridLayout(2, 2));
        rows.setBorder(new TitledBorder(String.format("Rows (%d - %d)",
                Minesweeper.MIN_ROWS, Minesweeper.MAX_ROWS)));
        initialFrame.add(rows);

        columns.setBorder(new TitledBorder(String.format("Columns (%d - %d)",
                Minesweeper.MIN_COLUMNS, Minesweeper.MAX_COLUMNS)));
        initialFrame.add(columns);

        minesNum.setBorder(new TitledBorder("Mines"));
        initialFrame.add(minesNum);

        JButton okButton = new JButton("OK");
        initialFrame.add(okButton);

        showInitialFrame();
        okButton.addActionListener(new ActionListenerForButtonOK());
    }

    private boolean isNumber(String str) {
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private class ActionListenerForButtonOK implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowsValue;
            if (isNumber(rows.getText())) {
                rowsValue = Integer.valueOf(rows.getText());
                if (rowsValue < Minesweeper.MIN_ROWS || rowsValue > Minesweeper.MAX_ROWS) {
                    String str = String.format("Input number beetwen %d and %d", Minesweeper.MIN_ROWS,
                            Minesweeper.MAX_ROWS);
                    JOptionPane.showMessageDialog(initialFrame, str, "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(initialFrame, "Input number of rows please", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int columnsValue;
            if (isNumber(columns.getText())) {
                columnsValue = Integer.valueOf(columns.getText());
                if (columnsValue < Minesweeper.MIN_COLUMNS || columnsValue > Minesweeper.MAX_COLUMNS) {
                    String str = String.format("Input number beetwen %d and %d", Minesweeper.MIN_COLUMNS,
                            Minesweeper.MAX_COLUMNS);
                    JOptionPane.showMessageDialog(initialFrame, str, "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(initialFrame, "Input number of columns please", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int minesNumValue;
            int maxMinesNum = (int) (rowsValue * columnsValue * 0.9);
            if (isNumber(minesNum.getText())) {
                minesNumValue = Integer.valueOf(minesNum.getText());
                if (minesNumValue < Minesweeper.MIN_MINES || minesNumValue > maxMinesNum) {
                    String str = String.format("Input number beetwen %d and %d", Minesweeper.MIN_MINES, maxMinesNum);
                    JOptionPane.showMessageDialog(initialFrame, str, "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(initialFrame, "Input number of mines please", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            new MineFieldFrame(rowsValue, columnsValue, minesNumValue, screenSize);
            initialFrame.dispose();
        }
    }

    public void showInitialFrame() {
        initialFrame.setVisible(true);
    }


}
