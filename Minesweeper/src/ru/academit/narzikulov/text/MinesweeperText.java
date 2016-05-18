package ru.academit.narzikulov.text;

import ru.academit.narzikulov.Minesweeper;

import java.util.ArrayList;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperText {
    private Minesweeper minesweeper = new Minesweeper();

    public MinesweeperText() {

    }

    public void printMineField() {
        Minesweeper minesweeper = new Minesweeper();
        ArrayList<ArrayList<Integer>> mineField = minesweeper.getMineField();
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                System.out.printf("%3d", mineField.get(i).get(j));
            }
            System.out.println();
        }
    }


}
