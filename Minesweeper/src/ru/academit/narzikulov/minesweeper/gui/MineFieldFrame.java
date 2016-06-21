package ru.academit.narzikulov.minesweeper.gui;

import ru.academit.narzikulov.minesweeper.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.KeyStroke.*;

/**
 * Created by tim on 21.06.2016.
 */
public class MineFieldFrame {
    private final static int CELL_SIZE_X = 50;
    private final static int CELL_SIZE_Y = 50;

    private JFrame minesweeperFrame = new JFrame("Minesweeper");
    private JPanel minesweeperPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");

    private Minesweeper minesweeper = new Minesweeper();

    private int rowsValue;
    private int columnsValue;

    private ArrayList<ArrayList<GuiCell>> mineFieldButtons = new ArrayList<>();

    public MineFieldFrame(int rowsValue, int columnsValue, int minesNumValue, Dimension screenSize) {
        this.rowsValue = rowsValue;
        this.columnsValue = columnsValue;

        int x = (int) CELL_SIZE_X * rowsValue;
        int y = (int) CELL_SIZE_Y * columnsValue;

        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minesweeperFrame.setMinimumSize(new Dimension(x, y));
        minesweeperFrame.setLocation((int) (screenSize.getWidth() - x) / 2, (int) (screenSize.getHeight() - y) / 2);
        minesweeperPanel.setSize((int) CELL_SIZE_X * rowsValue, (int) CELL_SIZE_Y * columnsValue);
        minesweeperPanel.setLayout(new GridLayout(rowsValue, columnsValue, 5, 5));

        //System.out.printf("rowsValue %d, columnsValue %d, minesNumValue %d", rowsValue, columnsValue, minesNumValue);
        minesweeper = new Minesweeper(rowsValue, columnsValue, minesNumValue);

        createMenu();
        fillMineField();

        minesweeperFrame.setContentPane(minesweeperPanel);
        minesweeperFrame.setVisible(true);

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
        JMenuItem newGameFileMenuItem = new JMenuItem(String.format("%-20s %20s","New game","n"), KeyEvent.VK_N);
        newGameFileMenuItem.addActionListener(new ActionListenerMenuFileNewGame());
        fileMenu.add(newGameFileMenuItem);

        JMenuItem highScoresFileMenuItem = new JMenuItem(String.format("%-20s %20s", "High scores", "s"), KeyEvent.VK_S);
        highScoresFileMenuItem.addActionListener(new ActionListenerMenuFileHighScores());
        fileMenu.add(highScoresFileMenuItem);

        JMenuItem aboutFileMenuItem = new JMenuItem(String.format("%-26s %20s", "About", "a"), KeyEvent.VK_A);
        aboutFileMenuItem.addActionListener(new ActionListenerMenuFileAbout());
        fileMenu.add(aboutFileMenuItem);

        JMenuItem exitFileMenuItem = new JMenuItem(String.format("%-29s %20s", "Exit", "e"), KeyEvent.VK_ALT + KeyEvent.VK_E);
        exitFileMenuItem.addActionListener(new ActionListenerMenuFileExit());
        fileMenu.add(exitFileMenuItem);

        menuBar.add(fileMenu);
        minesweeperFrame.setJMenuBar(menuBar);
    }

    private void updateMineField() {
        //Обновление кнопок игрового поля
        for (int i = 0; i < rowsValue; ++i) {
            for (int j = 0; j < columnsValue; ++j) {
                if (!minesweeper.getCell(i, j).getIsOpen()) {
                    continue;
                }
                if (minesweeper.getCell(i, j).getIsMineFound()) {
                    mineFieldButtons.get(i).get(j).setText("M");
                }
                if (minesweeper.getCell(i, j).getIsMine()) {
                    mineFieldButtons.get(i).get(j).setText("*");
                } else {
                    int mines = minesweeper.getCell(i, j).getMinesAround();
                    if (mines == 0) {
                        mineFieldButtons.get(i).get(j).setVisible(false);
                    } else {
                        String minesNum = Integer.toString(minesweeper.getCell(i, j).getMinesAround());
                        mineFieldButtons.get(i).get(j).setText(minesNum);
                    }
                }

            }
        }
    }

    private void showGameOver() {
        JOptionPane.showMessageDialog(new JFrame(), "Game over! You lost!", "Game over", JOptionPane.WARNING_MESSAGE);
    }

    private void showGameIsWon() throws IOException {
        JOptionPane.showMessageDialog(new JFrame(), "You won the game!", "You won the game!", JOptionPane.WARNING_MESSAGE);
        minesweeper.setPlayerName(JOptionPane.showInputDialog("Input player name:"));
        JOptionPane.showMessageDialog(new JButton(), minesweeper.highScoresTableToString(),
                "High scores table", JOptionPane.WARNING_MESSAGE);
    }

    private class MouseListenerForMineFieldButtons extends MouseAdapter {
        private int i;
        private int j;

        public MouseListenerForMineFieldButtons(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (minesweeper.getCell(i, j).getIsOpen()) {
                return;
            }

            if (minesweeper.getGameIsWon()) {
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
                if (!minesweeper.getCell(i, j).getIsMineFound()) {
                    minesweeper.getCell(i, j).setIsMineFound(true);
                    mineFieldButtons.get(i).get(j).setText("M");
                } else {
                    minesweeper.getCell(i, j).setIsMineFound(false);
                    mineFieldButtons.get(i).get(j).setText("");
                }
            }

            if (minesweeper.gameIsWon()) {
                try {
                    showGameIsWon();
                } catch (IOException e1) {
                    e1.printStackTrace();
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
            InitialMinisweeperFrame minesweeperField = new InitialMinisweeperFrame();
            minesweeperField.showInitialFrame();
        }
    }

    private class ActionListenerMenuFileHighScores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(new JButton(), minesweeper.highScoresTableToString(),
                    "High scores table", JOptionPane.WARNING_MESSAGE);
        }

    }

    private class ActionListenerMenuFileExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
