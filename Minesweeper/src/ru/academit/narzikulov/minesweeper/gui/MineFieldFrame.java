package ru.academit.narzikulov.minesweeper.gui;

import ru.academit.narzikulov.minesweeper.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tim on 21.06.2016.
 */
public class MineFieldFrame {
    private final static int CELL_SIZE_X = 25;
    private final static int CELL_SIZE_Y = 25;

    private JFrame minesweeperFrame = new JFrame("Minesweeper");
    private JPanel minesweeperPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");

    private Minesweeper minesweeper = new Minesweeper();

    private int rowsValue;
    private int columnsValue;

    private ArrayList<ArrayList<GuiCell>> mineFieldButtons = new ArrayList<>();
    private Icon mineIcon = new ImageIcon("Minesweeper\\src\\ru\\academit\\narzikulov\\minesweeper\\pics\\mine.png");
    private Icon flagIcon = new ImageIcon("Minesweeper\\src\\ru\\academit\\narzikulov\\minesweeper\\pics\\flag.png");
    private Icon questionIcon = new ImageIcon("Minesweeper\\src\\ru\\academit\\narzikulov\\minesweeper\\pics\\question.png");
    private Icon borderIcon = new ImageIcon("Minesweeper\\src\\ru\\academit\\narzikulov\\minesweeper\\pics\\border.png");
    private Font font = new Font("Arial", Font.BOLD, 12);

    public MineFieldFrame(int rowsValue, int columnsValue, int minesNumValue, Dimension screenSize) {
        this.rowsValue = rowsValue;
        this.columnsValue = columnsValue;

        int x = (CELL_SIZE_X + 2) * this.columnsValue;
        int y = (CELL_SIZE_Y + 2) * this.rowsValue + 45;

        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minesweeperFrame.setMinimumSize(new Dimension(x, y));
        minesweeperFrame.setSize(x, y);
        minesweeperFrame.setResizable(false);
        minesweeperFrame.setLocation((int) (screenSize.getWidth() - x) / 2, (int) (screenSize.getHeight() - y) / 2);
        minesweeperPanel.setSize(x, y);
        minesweeperPanel.setLayout(new GridLayout(rowsValue, columnsValue, 2, 2));

        minesweeper = new Minesweeper(rowsValue, columnsValue, minesNumValue);

        fillMineField();
        createMenu();

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
                mineFieldButtons.get(i).get(j).setFont(font);
                mineFieldButtons.get(i).get(j).setMargin(new Insets(0, 0, 0, 0));
            }
        }
    }

    private void createMenu() {
        JMenuItem newGameFileMenuItem = new JMenuItem(String.format("%-20s %20s", "New game", "n"), KeyEvent.VK_N);
        newGameFileMenuItem.addActionListener(new ActionListenerMenuFileNewGame());
        fileMenu.add(newGameFileMenuItem);

        JMenuItem highScoresFileMenuItem = new JMenuItem(String.format("%-20s %20s", "High scores", "s"), KeyEvent.VK_S);
        highScoresFileMenuItem.addActionListener(new ActionListenerMenuFileHighScores());
        fileMenu.add(highScoresFileMenuItem);

        JMenuItem aboutFileMenuItem = new JMenuItem(String.format("%-26s %20s", "About", "a"), KeyEvent.VK_A);
        aboutFileMenuItem.addActionListener(new ActionListenerMenuFileAbout());
        fileMenu.add(aboutFileMenuItem);

        JMenuItem exitFileMenuItem = new JMenuItem(String.format("%-29s %20s", "Exit", "e"), KeyEvent.VK_E);
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
                    mineFieldButtons.get(i).get(j).setIcon(flagIcon);
                }
                if (minesweeper.getCell(i, j).getIsMine()) {
                    mineFieldButtons.get(i).get(j).setIcon(mineIcon);

                } else {
                    int mines = minesweeper.getCell(i, j).getMinesAround();
                    if (mines == 0) {
                        mineFieldButtons.get(i).get(j).setVisible(false);
                    } else {
                        String minesNum = Integer.toString(minesweeper.getCell(i, j).getMinesAround());
                        if (minesweeper.getCell(i, j).getIsMineFound() || minesweeper.getCell(i, j).getUnderQuestion()) {
                        } else {
                            mineFieldButtons.get(i).get(j).setText(minesNum);
                        }
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

    private boolean indexesAreNotOutOfBounds(int i, int j) {
        return i >= 0 && j >= 0 && i < mineFieldButtons.size() && j < mineFieldButtons.get(0).size();
    }

    private void markAllCellsAround(int iTurn, int jTurn, Icon icon) {
        for (int i = iTurn - 1; i <= iTurn + 1; ++i) {
            for (int j = jTurn - 1; j <= jTurn + 1; ++j) {
                //if (i != iTurn && j != jTurn) {
                if (indexesAreNotOutOfBounds(i, j)
                        && !minesweeper.getCell(i, j).getIsOpen()
                        && !minesweeper.getCell(i, j).getIsMineFound()
                        && !minesweeper.getCell(i, j).getUnderQuestion()) {
                    mineFieldButtons.get(i).get(j).setIcon(icon);
                }
                //}
            }
        }
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
            if (minesweeper.getGameIsWon() || minesweeper.getGameIsLost()) {
                return;
            }

            if (e.getButton() == MouseEvent.BUTTON1) {
                if (minesweeper.getCell(i, j).getIsOpen()) {
                    return;
                }

                if (minesweeper.getCell(i, j).getIsMineFound()) {
                    return;
                }
                if (minesweeper.getCell(i, j).getIsMine() && minesweeper.getIsGameStarted()) {
                    minesweeper.openAllCell();
                    updateMineField();
                    showGameOver();
                } else {
                    minesweeper.openCell(i, j);
                    updateMineField();
                }
            }

            if (e.getButton() == MouseEvent.BUTTON2) {
                if (e.getButton() == MouseEvent.BUTTON2) {
                    markAllCellsAround(i, j, borderIcon);
                    //updateMineField();
                }
            }

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (minesweeper.getCell(i, j).getIsOpen()) {
                    return;
                }

                if (!minesweeper.getCell(i, j).getIsMineFound() && !minesweeper.getCell(i, j).getUnderQuestion()) {
                    minesweeper.getCell(i, j).setIsMineFound(true);
                    mineFieldButtons.get(i).get(j).setIcon(flagIcon);
                    minesweeper.getCell(i, j).setUnderQuestion(false);
                } else if (minesweeper.getCell(i, j).getIsMineFound()) {
                    minesweeper.getCell(i, j).setIsMineFound(false);
                    minesweeper.getCell(i, j).setUnderQuestion(true);
                    mineFieldButtons.get(i).get(j).setIcon(questionIcon);
                } else {
                    minesweeper.getCell(i, j).setUnderQuestion(false);
                    mineFieldButtons.get(i).get(j).setIcon(null);
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

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON2) {
                markAllCellsAround(i, j, null);
                updateMineField();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON2) {
                minesweeper.openCellsAroundForOpenedCell(i, j);
                if (minesweeper.getGameIsLost()) {
                    minesweeper.openAllCell();
                    updateMineField();
                    showGameOver();
                } else {
                    updateMineField();
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
