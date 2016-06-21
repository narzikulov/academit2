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
public class InitialMinisweeperFrame{
    private final static int FRAME_X_SIZE = 250;
    private final static int FRAME_Y_SIZE = 200;

    private JFrame initialFrame = new JFrame("Minesweeper");

    private JTextField rows = new JTextField();
    private JTextField columns = new JTextField();
    private JTextField minesNum = new JTextField();

    private int rowsValue;
    private int columnsValue;

    //size of the screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public InitialMinisweeperFrame() {
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
        rows.setBorder(new TitledBorder("Rows"));
        initialFrame.add(rows);
        columns.setBorder(new TitledBorder("Columns"));
        initialFrame.add(columns);
        minesNum.setBorder(new TitledBorder("Mines"));
        initialFrame.add(minesNum);
        JButton okButton = new JButton("OK");
        initialFrame.add(okButton);

        showInitialFrame();
        okButton.addActionListener(new ActionListenerForButtonOK());
    }

    private class ActionListenerForButtonOK implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initialFrame.dispose();

            rowsValue = Integer.valueOf(rows.getText());
            columnsValue = Integer.valueOf(columns.getText());
            int minesNumValue = Integer.valueOf(minesNum.getText());

            MineFieldFrame mineFieldFrame = new MineFieldFrame(rowsValue, columnsValue, minesNumValue, screenSize);

        }
    }

    public void showInitialFrame() {
        initialFrame.setVisible(true);
    }


}
