package ru.academit.narzikulov;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tim on 18.05.2016.
 */
public class Minesweeper {
    private int rows;
    private int columns;
    private int minesNum;
    private ArrayList<ArrayList<Integer>> mineField = new ArrayList<ArrayList<Integer>>();


    public Minesweeper(int rows, int columns, int minesNum) {
        this.rows = rows;
        this.columns = columns;
        this.minesNum = minesNum;
        //Заполнение поля нулями
        for (int i = 0; i < rows; ++i) {
            mineField.add(new ArrayList<Integer>());
            for (int j = 0; j < columns; ++j) {
                mineField.get(i).add(0);
            }
        }
        setMines();
    }

    public Minesweeper() {
        this(9, 9, 10);
    }

    //Расстановка мин на поле методом вычисления рандомного индекса в двумерном списке
    public void setMines() {
        Random randomNumber = new Random();
        for (int i = 1; i <= minesNum; ++i) {
            int iRandom = randomNumber.nextInt(mineField.size());
            int jRandom = randomNumber.nextInt(mineField.get(0).size());
            if (mineField.get(iRandom).get(jRandom) == 1) {
                --i;
            }
            mineField.get(iRandom).set(jRandom, 1);
        }
    }

    public ArrayList<ArrayList<Integer>> getMineField() {
        return mineField;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
