package ru.academit.narzikulov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tim on 18.05.2016.
 */
public class Minesweeper {
    private int rows;
    private int columns;
    private int numOfMines;
    private ArrayList<ArrayList<Integer>> mineField = new ArrayList<ArrayList<Integer>>();
    private ArrayList<CellCoordinate> cellCoordinate = new ArrayList<CellCoordinate>();
    int emptyCellIndex = 100;


    public Minesweeper(int rows, int columns, int numOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.numOfMines = numOfMines;
        //Если количество мин указано больше, чем количество игровых ячеек, тогда
        //пусть количество мин = количеству игровых ячеек поля
        if (numOfMines >= rows * columns) {
            numOfMines = rows * columns;
        }

        //Заполнение ячеек игрового поля нулями
        for (int i = 0; i < rows; ++i) {
            mineField.add(new ArrayList<Integer>());
            for (int j = 0; j < columns; ++j) {
                mineField.get(i).add(0);
            }
        }
        setMines();
        markFreeSpaces();
        printMineField();
    }

    public Minesweeper() {
        this(9, 9, 10);
    }

    //Расстановка мин на поле методом вычисления рандомного индекса в двумерном списке
    //значение = -1 означает мину
    public void setMines() {
        Random randomNumber = new Random();
        for (int i = 1; i <= numOfMines; ++i) {
            int iRandom = randomNumber.nextInt(mineField.size());
            int jRandom = randomNumber.nextInt(mineField.get(0).size());
            if (mineField.get(iRandom).get(jRandom) == -1) {
                --i;
            }
            mineField.get(iRandom).set(jRandom, -1);
        }

        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j) == -1) {
                    //System.out.printf("i = %d, j = %d", i, j);
                    //System.out.println();
                    setNumbersAroundCellsWithMine(i, j);
                    //printMineField();
                    //System.out.println("----------------------------------");
                }

            }
        }
    }

    //Временный метод для debug
    public void printMineField() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                System.out.printf("%5d", mineField.get(i).get(j));
            }
            System.out.println();
        }
    }

    private void incValueInMineFieldCell(int i, int j) {
        if (i >= 0 && j >= 0 && i < mineField.size() && j < mineField.get(0).size()) {
            if (mineField.get(i).get(j) != -1) mineField.get(i).set(j, mineField.get(i).get(j) + 1);
        }
    }

    private void setNumbersAroundCellsWithMine(int i, int j) {
        incValueInMineFieldCell(i - 1, j - 1);
        incValueInMineFieldCell(i - 1, j);
        incValueInMineFieldCell(i - 1, j + 1);
        incValueInMineFieldCell(i, j - 1);
        incValueInMineFieldCell(i, j + 1);
        incValueInMineFieldCell(i + 1, j - 1);
        incValueInMineFieldCell(i + 1, j);
        incValueInMineFieldCell(i + 1, j + 1);
    }

    private void addCellToList(int i, int j) {
        if (i >= 0 && j >= 0 && i < mineField.size() && j < mineField.get(0).size()) {
            if (mineField.get(i).get(j) == 0 && mineField.get(i).get(j) != emptyCellIndex) cellCoordinate.add(new CellCoordinate(i, j));
        }
    }

    private void findFreeCellsAroundIJCell(CellCoordinate cellCoordinate) {
        int i = cellCoordinate.getI();
        int j = cellCoordinate.getJ();
        addCellToList(i - 1, j - 1);
        addCellToList(i - 1, j);
        addCellToList(i - 1, j + 1);
        addCellToList(i, j - 1);
        addCellToList(i, j + 1);
        addCellToList(i + 1, j - 1);
        addCellToList(i + 1, j);
        addCellToList(i + 1, j + 1);
    }

    private void markFreeSpaces() {
        //Поиск первой пустой ячейки и добавление ее индексов в список
        boolean firstFreeCellFound = false;
        for (int i = 0; i < mineField.size(); ++i) {
            if (firstFreeCellFound) { break;}
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j) == 0) {
                    cellCoordinate.add(new CellCoordinate(i, j));
                    System.out.println("i = " + i + ", j = " + j);
                    firstFreeCellFound = true;
                    break;
                }
            }
        }

        for (int k = 0; k < cellCoordinate.size(); ++k) {
            findFreeCellsAroundIJCell(cellCoordinate.get(k));
            mineField.get(cellCoordinate.get(k).getI()).set(cellCoordinate.get(k).getJ(), emptyCellIndex);
            //++emptyCellIndex;
            k = 0;
            cellCoordinate.remove(k);
        }

    }

    public boolean isChoosenCellIsMine(int i, int j) {
        return mineField.get(i).get(j) == 1;
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
