package ru.academit.narzikulov.minesweeper;

import ru.academit.narzikulov.minesweeper.gui.MinesweeperGui;

import javax.swing.*;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperMain {
    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MinesweeperGui minesweeper = new MinesweeperGui();
            }
        });

        //MinesweeperText minesweeperText = new MinesweeperText();
        //minesweeperText.printMineField();
        //minesweeperText.startGame();

    }
}
