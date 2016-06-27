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
    private JLabel playTime = new JLabel("Playing time: ");
    private Timer timer;

    private Minesweeper minesweeper = new Minesweeper();

    private int rowsValue;
    private int columnsValue;

    private ArrayList<ArrayList<GuiCell>> mineFieldButtons = new ArrayList<>();
    private String picsPath = "Minesweeper\\src\\ru\\academit\\narzikulov\\minesweeper\\pics\\";
    private Icon mineIcon = new ImageIcon(picsPath + "mine.png");
    private Icon flagIcon = new ImageIcon(picsPath + "flag.png");
    private Icon questionIcon = new ImageIcon(picsPath + "question.png");
    private Icon borderIcon = new ImageIcon(picsPath + "border.png");
    private Font font = new Font("Arial", Font.BOLD, 12);

    public MineFieldFrame(int rowsValue, int columnsValue, int minesNumValue, Dimension screenSize) {
        this.rowsValue = rowsValue;
        this.columnsValue = columnsValue;

        int x = (CELL_SIZE_X + 2) * this.columnsValue;
        //80 - ширина меню + JLabel для вывода времени
        int y = (CELL_SIZE_Y + 2) * this.rowsValue + 80;

        minesweeperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minesweeperFrame.setMinimumSize(new Dimension(x, y));
        minesweeperFrame.setSize(x, y);
        //minesweeperFrame.setResizable(false);
        minesweeperFrame.setLocation((int) (screenSize.getWidth() - x) / 2, (int) (screenSize.getHeight() - y) / 2);

        minesweeperPanel.setSize(x, y);
        minesweeperPanel.setLayout(new GridLayout(rowsValue, columnsValue, 2, 2));

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(x, y);

        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playTime.setText(String.format("Plaing time: %d seconds",
                        (System.currentTimeMillis() - minesweeper.getPlayingTime()) / 1000));
            }
        });

        JPanel playTimePanel = new JPanel();
        playTimePanel.setSize(x, 50);
        playTimePanel.add(playTime);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(minesweeperPanel, BorderLayout.CENTER);
        mainPanel.add(playTimePanel, BorderLayout.SOUTH);

        minesweeper = new Minesweeper(rowsValue, columnsValue, minesNumValue);

        fillMineField();
        createMenu();

        minesweeperFrame.setContentPane(mainPanel);
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
        JMenuItem newGameFileMenuItem = new JMenuItem("New game");
        newGameFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
        newGameFileMenuItem.addActionListener(new ActionListenerMenuFileNewGame());
        fileMenu.add(newGameFileMenuItem);

        JMenuItem highScoresFileMenuItem = new JMenuItem("High scores");
        highScoresFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        highScoresFileMenuItem.addActionListener(new ActionListenerMenuFileHighScores());
        fileMenu.add(highScoresFileMenuItem);

        JMenuItem aboutFileMenuItem = new JMenuItem("About");
        aboutFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        aboutFileMenuItem.addActionListener(new ActionListenerMenuFileAbout());
        fileMenu.add(aboutFileMenuItem);

        JMenuItem exitFileMenuItem = new JMenuItem("Exit");
        exitFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
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
                        if (!minesweeper.getCell(i, j).getIsMineFound() && !minesweeper.getCell(i, j).getUnderQuestion()) {
                            mineFieldButtons.get(i).get(j).setText(minesNum);
                        }
                    }
                }

            }
        }
    }

    private void showGameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(new JFrame(), "Game over! You lost!", "Game over", JOptionPane.WARNING_MESSAGE);
    }

    private void showGameIsWon() throws IOException {
        timer.stop();
        JOptionPane.showMessageDialog(minesweeperFrame, "You won the game!", "You won the game!", JOptionPane.WARNING_MESSAGE);
        minesweeper.setPlayerName(JOptionPane.showInputDialog("Input player name:"));
        JOptionPane.showMessageDialog(minesweeperFrame, minesweeper.getHighScoresFile().highScoresTableToString(),
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
        private boolean mouseLeavedCell = false;

        public MouseListenerForMineFieldButtons(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            mouseLeavedCell = true;
            markAllCellsAround(i, j, null);

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            mouseLeavedCell = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                timer.start();
            }

            if (e.getButton() == MouseEvent.BUTTON2) {
                markAllCellsAround(i, j, borderIcon);
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
            if (minesweeper.getGameIsWon() || minesweeper.getGameIsLost()) {
                return;
            }

            if (e.getButton() == MouseEvent.BUTTON1 && !mouseLeavedCell) {
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

            if (e.getButton() == MouseEvent.BUTTON2 && !mouseLeavedCell) {
                markAllCellsAround(i, j, null);
                minesweeper.openCellsAroundForOpenedCell(i, j);
                if (minesweeper.getGameIsLost()) {
                    minesweeper.openAllCell();
                    updateMineField();
                    showGameOver();
                } else {
                    updateMineField();
                }
            }

            if (e.getButton() == MouseEvent.BUTTON3 && !mouseLeavedCell) {
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
        public void mouseClicked(MouseEvent e) {
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
            JOptionPane.showMessageDialog(minesweeperFrame, minesweeper.about(), "About", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class ActionListenerMenuFileNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minesweeperFrame.dispose();
            InitialMinesweeperFrame minesweeperField = new InitialMinesweeperFrame();
            minesweeperField.showInitialFrame();
        }
    }

    private class ActionListenerMenuFileHighScores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(minesweeperFrame, minesweeper.getHighScoresFile().highScoresTableToString(),
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
