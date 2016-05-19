package ru.academit.narzikulov;

import ru.academit.narzikulov.gui.MinesweeperGui;
import ru.academit.narzikulov.text.MinesweeperText;

import javax.swing.*;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperMain {
    public static void main() {
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Размер поля и кол-во строк и столбцов
                MinesweeperGui minesweeperField = new MinesweeperGui(200, 150);
                minesweeperField.setFrame();
            }
        });*/

        MinesweeperText minesweeperText = new MinesweeperText();
        minesweeperText.printMineField();
        //minesweeperText.startGame();

    }
}
