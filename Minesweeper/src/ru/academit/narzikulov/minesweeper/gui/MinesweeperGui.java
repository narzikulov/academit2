package ru.academit.narzikulov.minesweeper.gui;

import ru.academit.narzikulov.minesweeper.Minesweeper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperGui {
    private JFrame minesweeperFrame = new JFrame("Minesweeper");
    private JPanel minesweeperPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");

    private JTextField rows = new JTextField();
    private JTextField columns = new JTextField();
    private JTextField numOfMines = new JTextField();

    private int rowsValue;
    private int columnsValue;

    private Minesweeper minesweeper = new Minesweeper();
    private Dimension cellSize = new Dimension();

    private ArrayList<ArrayList<GuiCell>> mineFieldButtons = new ArrayList<>();
    //size of the screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public MinesweeperGui(int x, int y) {
        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        minesweeperFrame.setLocation((int) screenSize.getWidth() / 2 - x, (int) screenSize.getHeight() / 3 - y);
        minesweeperFrame.setMinimumSize(new Dimension(x, y));
        minesweeperFrame.setSize(x, y);
        cellSize.height = 50;
        cellSize.width = 50;

        //Значения по умолчанию
        rows.setText(Integer.toString(Minesweeper.ROWS));
        columns.setText(Integer.toString(Minesweeper.COLUMNS));
        numOfMines.setText(Integer.toString(Minesweeper.MINES));

        minesweeperFrame.setLayout(new GridLayout(2, 2));
        rows.setBorder(new TitledBorder("Rows"));
        minesweeperFrame.add(rows);
        columns.setBorder(new TitledBorder("Columns"));
        minesweeperFrame.add(columns);
        numOfMines.setBorder(new TitledBorder("Mines"));
        minesweeperFrame.add(numOfMines);
        JButton okButton = new JButton("OK");
        minesweeperFrame.add(okButton);

        okButton.addActionListener(new ActionListenerForButtonOK());
    }

    public void showFrame() {
        minesweeperFrame.setVisible(true);
    }

    private class ActionListenerForButtonOK implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            rowsValue = Integer.valueOf(rows.getText());
            columnsValue = Integer.valueOf(columns.getText());
            int numOfMinesValue = Integer.valueOf(numOfMines.getText());

            int x = (int) cellSize.getWidth() * rowsValue;
            int y = (int) cellSize.getHeight() * columnsValue;
            minesweeperFrame.setMinimumSize(new Dimension(x, y));
            minesweeperFrame.setLocation((int) (screenSize.getWidth() - x) / 2, (int) (screenSize.getHeight() - y) / 2);
            minesweeperPanel.setSize((int) cellSize.getWidth() * rowsValue, (int) cellSize.getHeight() * columnsValue);
            minesweeperPanel.setLayout(new GridLayout(rowsValue, columnsValue, 5, 5));

            //System.out.printf("rowsValue %d, columnsValue %d, numOfMinesValue %d", rowsValue, columnsValue, numOfMinesValue);
            minesweeper = new Minesweeper(rowsValue, columnsValue, numOfMinesValue);

            createMenu();
            fillMineField();

            minesweeperFrame.setContentPane(minesweeperPanel);
        }
    }

    private void fillMineField() {
        //Заполнение игрового поля кнопками
        for (int i = 0; i < rowsValue; ++i) {
            mineFieldButtons.add(new ArrayList<GuiCell>());
            for (int j = 0; j < columnsValue; ++j) {
                mineFieldButtons.get(i).add(new GuiCell("", i, j));
                minesweeperPanel.add(mineFieldButtons.get(i).get(j));
                mineFieldButtons.get(i).get(j).addMouseListener(new MouseListenerForMineFieldButtons(i, j));
            }
        }
    }

    private void createMenu() {
        JMenuItem aboutFileMenuItem = new JMenuItem("About");
        aboutFileMenuItem.addActionListener(new ActionListenerMenuFileAbout());
        fileMenu.add(aboutFileMenuItem);

        JMenuItem newGameFileMenuItem = new JMenuItem("New game");
        newGameFileMenuItem.addActionListener(new ActionListenerMenuFileNewGame());
        fileMenu.add(newGameFileMenuItem);

        JMenuItem highScoresFileMenuItem = new JMenuItem("High scores");
        highScoresFileMenuItem.addActionListener(new ActionListenerMenuFileHighScores());
        fileMenu.add(highScoresFileMenuItem);

        JMenuItem exitFileMenuItem = new JMenuItem("Exit");
        exitFileMenuItem.addActionListener(new ActionListenerMenuFileExit());
        fileMenu.add(exitFileMenuItem);

        menuBar.add(fileMenu);
        minesweeperFrame.setJMenuBar(menuBar);
    }

    private void updateMineField() {
        //Обновление кнопок игрового поля
        for (int i = 0; i < rowsValue; ++i) {
            for (int j = 0; j < columnsValue; ++j) {
                if (minesweeper.getCell(i, j).getIsOpen()) {
                    if (minesweeper.getCell(i, j).getIsMineFound()) {
                        mineFieldButtons.get(i).get(j).setText("M");
                    }
                    if (minesweeper.getCell(i, j).getIsMine()) {
                        mineFieldButtons.get(i).get(j).setText("*");
                    }
                    if (!minesweeper.getCell(i, j).getIsMine()) {
                        int mines = minesweeper.getCell(i, j).getMinesAround();
                        if (mines == 0) {
                            mineFieldButtons.get(i).get(j).setVisible(false);
                        } else {
                            String numOfmines = Integer.toString(minesweeper.getCell(i, j).getMinesAround());
                            mineFieldButtons.get(i).get(j).setText(numOfmines);
                        }
                    }
                }
            }
        }
    }

    private void showGameOver() {
        JOptionPane.showMessageDialog(new JFrame(), "Game over! You lost!", "Game over", JOptionPane.WARNING_MESSAGE);
    }

    private void showGameIsWon() {
        JOptionPane.showMessageDialog(new JFrame(), "You won the game!", "You won the game!", JOptionPane.WARNING_MESSAGE);
    }

    private class MouseListenerForMineFieldButtons extends MouseAdapter {
        private int i;
        private int j;

        MouseListenerForMineFieldButtons(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (minesweeper.getCell(i, j).getIsOpen() || minesweeper.getCell(i, j).getIsMineFound()) {
                return;
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (minesweeper.getCell(i, j).getIsMineFound()) {
                    return;
                }
                if (minesweeper.getCell(i, j).getIsMine()) {
                    minesweeper.openAllCell();
                    updateMineField();
                    showGameOver();
                } else {
                    minesweeper.openCell(i, j);
                    updateMineField();
                }
                if (minesweeper.gameIsWon()) {
                    showGameIsWon();
                }
            }

            if (e.getButton() == MouseEvent.BUTTON2) {
                if (!minesweeper.getCell(i, j).getUnderQuestion()) {
                    minesweeper.getCell(i, j).setUnderQuestion(true);
                    mineFieldButtons.get(i).get(j).setText("?");
                } else {
                    minesweeper.getCell(i, j).setUnderQuestion(false);
                    mineFieldButtons.get(i).get(j).setText("");
                }
            }

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (minesweeper.getCell(i, j).getIsOpen()) {
                    return;
                }
                if (!minesweeper.getCell(i, j).getIsMineFound()) {
                    minesweeper.getCell(i, j).setIsMineFound(true);
                    mineFieldButtons.get(i).get(j).setText("M");
                    //updateMineField();
                    if (minesweeper.gameIsWon()) {
                        showGameIsWon();
                    }
                    return;
                } else {
                    minesweeper.getCell(i, j).setIsMineFound(false);
                    mineFieldButtons.get(i).get(j).setText("");
                    //updateMineField();
                }
                if (minesweeper.gameIsWon()) {
                    showGameIsWon();
                }
            }

        }

    }

    private class ActionListenerMenuFileAbout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(new JButton(), minesweeper.about(), "About", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class ActionListenerMenuFileNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minesweeperFrame.dispose();
            MinesweeperGui minesweeperField = new MinesweeperGui(200, 150);
            minesweeperField.showFrame();
        }
    }

    private class ActionListenerMenuFileHighScores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class ActionListenerMenuFileExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }
    }
}