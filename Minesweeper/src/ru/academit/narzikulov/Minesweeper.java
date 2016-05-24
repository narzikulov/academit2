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
    //emptyCellIndex - начальное значение, введенное для обнаружения и отметки всех пустых областей
    //первая область помечается значением по умолчанию, все последующие увеличивают индекс на 1
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
        findAndMarkAllFreeSpaces();
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
                    setNumbersAroundCellsWithMine(i, j);
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
            if (mineField.get(i).get(j) == 0 && mineField.get(i).get(j) != emptyCellIndex) {
                cellCoordinate.add(new CellCoordinate(i, j));
                //System.out.println("addCellToList i = " + i + ", j = " + j);
            }
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

    private void findFirstEmptyCell() {
        //Поиск первой пустой ячейки и добавление ее индексов в список
        boolean firstFreeCellFound = false;
        for (int i = 0; i < mineField.size(); ++i) {
            if (firstFreeCellFound) { break;}
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j) == 0) {
                    cellCoordinate.add(new CellCoordinate(i, j));
                    findFreeCellsAroundIJCell(cellCoordinate.get(0));
                    firstFreeCellFound = true;
                    break;
                }
            }
        }
    }

    private boolean isAnyUnmarkedFreeSpace() {
        //Наличие хотя бы одной пустой ячейки
        for (ArrayList<Integer> aMineField : mineField) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (aMineField.get(j) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void markFreeSpace() {
        //Цикл по списку пустых ячеек и изменение их значений с 0 на emptyCellIndex
        // с последующим удалением ячейки из списка
        for (int k = 0; k < cellCoordinate.size(); ++k) {
            findFreeCellsAroundIJCell(cellCoordinate.get(k));
            mineField.get(cellCoordinate.get(k).getI()).set(cellCoordinate.get(k).getJ(), emptyCellIndex);
            cellCoordinate.remove(k);
            k = 0;
        }
        cellCoordinate.clear();
    }

    private void findAndMarkAllFreeSpaces() {
        int i = 0;
        do {
            findFirstEmptyCell();
            markFreeSpace();
            ++emptyCellIndex;
        } while(isAnyUnmarkedFreeSpace());
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
