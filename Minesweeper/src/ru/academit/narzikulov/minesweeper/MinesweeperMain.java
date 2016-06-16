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
                // Размер поля и кол-во строк и столбцов
                MinesweeperGui minesweeperField = new MinesweeperGui(200, 150);
                minesweeperField.showFrame();
            }
        });

        //MinesweeperText minesweeperText = new MinesweeperText();
        //minesweeperText.printMineField();
        //minesweeperText.startGame();

    }
}
