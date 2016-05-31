package ru.academit.narzikulov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tim on 18.05.2016.
 */
public class Minesweeper {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int MINES = 10;
    private int rows;
    private int columns;
    private int mines;
    private ArrayList<ArrayList<Cell>> mineField = new ArrayList<>();
    private ArrayList<CellCoordinate> cellCoordinate = new ArrayList<>();
    private boolean gameIsLost = false;

    public Minesweeper(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        //Если количество мин указано больше, чем количество игровых ячеек, тогда
        //пусть количество мин = количеству игровых ячеек поля - 1
        if (mines >= rows * columns) {
            this.mines = (rows - 1) * (columns - 1);
        }

        //Заполнение ячеек игрового поля нулями
        for (int i = 0; i < rows; ++i) {
            mineField.add(new ArrayList<Cell>());
            for (int j = 0; j < columns; ++j) {
                mineField.get(i).add(new Cell(0));
            }
        }
        setMines();
        //findAndMarkAllFreeSpaces();
        //printMineField();
    }

    public Minesweeper() {
        this(ROWS, COLUMNS, MINES);
    }

    //Расстановка мин на поле методом вычисления рандомного индекса в двумерном списке
    public void setMines() {
        Random randomNumber = new Random();
        for (int i = 1; i <= mines; ++i) {
            int iRandom = randomNumber.nextInt(mineField.size());
            int jRandom = randomNumber.nextInt(mineField.get(0).size());
            if (mineField.get(iRandom).get(jRandom).getIsMine()) {
                --i;
            }
            mineField.get(iRandom).get(jRandom).setIsMine(true);
        }

        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j).getIsMine()) {
                    setNumbersAroundMineCell(i, j);
                }

            }
        }
    }

    //Временный метод для debug
    public void printMineField() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (!mineField.get(i).get(j).getIsOpen()) {
                    if (mineField.get(i).get(j).getIsMine()) {
                        System.out.printf("%3s", "*");
                    } else {
                        System.out.printf("%3d", mineField.get(i).get(j).getMinesAround());
                    }
                } else System.out.printf("%3s", "x");
            }
            System.out.println();
        }
    }

    private void incValueInMineFieldCell(int i, int j) {
        if (i >= 0 && j >= 0 && i < mineField.size() && j < mineField.get(0).size() &&
                !mineField.get(i).get(j).getIsMine()) {
            mineField.get(i).get(j).setMinesAround(mineField.get(i).get(j).getMinesAround() + 1);
        }
    }

    private void setNumbersAroundMineCell(int i, int j) {
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
            if (mineField.get(i).get(j).getMinesAround() == 0) {
                cellCoordinate.add(new CellCoordinate(i, j));
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

    private void openFreeSpace() {
        //Цикл по списку пустых ячеек и изменение их значений на open
        // с последующим удалением ячейки из списка

        for (int k = 0; k < cellCoordinate.size(); ++k) {
            if (!mineField.get(cellCoordinate.get(k).getI()).get(cellCoordinate.get(k).getJ()).getIsOpen()) {
                findFreeCellsAroundIJCell(cellCoordinate.get(k));
            }
            mineField.get(cellCoordinate.get(k).getI()).get(cellCoordinate.get(k).getJ()).setIsOpen(true);
            cellCoordinate.remove(k);
            k = 0;
        }
        cellCoordinate.clear();
    }

    public boolean isCellIsMine(int i, int j) {
        if (i < 0 || j < 0 || i >= mineField.size() || j >= mineField.get(0).size()) {
            return false;
        }
        return mineField.get(i).get(j).getIsMine();
    }

    public void openCell(int iTurn, int jTurn) {
        if (iTurn < 0 || iTurn >= mineField.size() ||
                jTurn < 0 || jTurn >= mineField.get(0).size()) {
            return;
        }
        if (mineField.get(iTurn).get(jTurn).getIsMine()) {
            gameIsLost = true;
        }
        if (mineField.get(iTurn).get(jTurn).getMinesAround() == 0) {
            cellCoordinate.add(new CellCoordinate(iTurn, jTurn));
            findFreeCellsAroundIJCell(cellCoordinate.get(0));
            openFreeSpace();
        }
        mineField.get(iTurn).get(jTurn).setIsOpen(true);
    }

    public void openAllCell() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                mineField.get(i).get(j).setIsOpen(true);
            }
        }
    }

    public boolean isAllCellsOpen() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (!mineField.get(i).get(j).getIsMine() && !mineField.get(i).get(j).getIsOpen()) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Cell>> getMineField() {
        return mineField;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setGameIsLost(boolean gameIsLost) {
        this.gameIsLost = gameIsLost;
    }

    public boolean getGameIsLost() {
        return this.gameIsLost;
    }

    public String about() {
        return "Winesweeper v. 1.0\n";
    }
}
