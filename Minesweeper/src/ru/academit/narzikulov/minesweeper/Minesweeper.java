package ru.academit.narzikulov.minesweeper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tim on 18.05.2016.
 */
public class Minesweeper {
    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    public static final int MINES = 10;
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
    private void setMines() {
        Random randomNumber = new Random();
        for (int i = 1; i <= mines; ++i) {
            int iRandom = randomNumber.nextInt(mineField.size());
            int jRandom = randomNumber.nextInt(mineField.get(0).size());
            if (getCell(iRandom, jRandom).getIsMine()) {
                --i;
            }
            getCell(iRandom, jRandom).setIsMine(true);
        }

        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (getCell(i, j).getIsMine()) {
                    setNumbersAroundMineCell(i, j);
                }
            }
        }
    }

    private boolean indexesAreNotOutOfBounds(int i, int j) {
        return i >= 0 && j >= 0 && i < mineField.size() && j < mineField.get(0).size();
    }

    private void incValueInMineFieldCell(int i, int j) {
        if (indexesAreNotOutOfBounds(i, j) && !getCell(i, j).getIsMine()) {
            getCell(i, j).setMinesAround(getCell(i, j).getMinesAround() + 1);
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
        if (indexesAreNotOutOfBounds(i, j)) {
            if (getCell(i, j).getMinesAround() == 0 && !getCell(i, j).getIsMineFound()) {
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

    private void openOneCellAround(int i, int j) {
        if (indexesAreNotOutOfBounds(i, j)) {
            if (!getCell(i, j).getIsMine() && getCell(i, j).getMinesAround() != 0) {
                getCell(i, j).setIsOpen(true);
            }
        }
    }

    private void openCellsAround(CellCoordinate cellCoordinate) {
        int i = cellCoordinate.getI();
        int j = cellCoordinate.getJ();
        openOneCellAround(i - 1, j);
        openOneCellAround(i - 1, j - 1);
        openOneCellAround(i - 1, j + 1);
        openOneCellAround(i, j - 1);
        openOneCellAround(i, j + 1);
        openOneCellAround(i + 1, j - 1);
        openOneCellAround(i + 1, j);
        openOneCellAround(i + 1, j + 1);
    }

    private void openFreeSpace() {
        //Цикл по списку пустых ячеек и изменение их значений на open
        // с последующим удалением ячейки из списка
        for (int k = 0; k < cellCoordinate.size(); ++k) {
            if (!getCell(cellCoordinate.get(k).getI(), cellCoordinate.get(k).getJ()).getIsOpen()) {
                findFreeCellsAroundIJCell(cellCoordinate.get(k));
            }
            getCell(cellCoordinate.get(k).getI(), cellCoordinate.get(k).getJ()).setIsOpen(true);
            openCellsAround(cellCoordinate.get(k));
            cellCoordinate.remove(k);
            k = 0;
        }
        cellCoordinate.clear();
    }

    public boolean isCellIsMine(int i, int j) {
        return !(i < 0 || j < 0 || i >= mineField.size() || j >= mineField.get(0).size()) && getCell(i, j).getIsMine();
    }

    public boolean gameIsWon() {
        if (gameIsLost) {
            return false;
        }
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (!getCell(i, j).getIsMine() && !getCell(i, j).getIsOpen())
                        /*|| getCell(i, j).getIsMine() && !getCell(i, j).getIsMineFound())*/{
                    return false;
                }
            }
        }
        return true;
    }

    public void openCell(int iTurn, int jTurn) {
        if (iTurn < 0 || iTurn >= mineField.size() ||
                jTurn < 0 || jTurn >= mineField.get(0).size()) {
            return;
        }
        if (!getCell(iTurn, jTurn).getIsMineFound()) {
            if (getCell(iTurn, jTurn).getIsMine()) {
                gameIsLost = true;
            }
            if (getCell(iTurn, jTurn).getMinesAround() == 0) {
                cellCoordinate.add(new CellCoordinate(iTurn, jTurn));
                findFreeCellsAroundIJCell(cellCoordinate.get(0));
                openFreeSpace();
            }

            getCell(iTurn, jTurn).setIsOpen(true);
        }
    }

    public void openAllCell() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                getCell(i, j).setIsOpen(true);
            }
        }
    }

    public boolean isAllCellsOpen() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (!getCell(i, j).getIsMine() && !getCell(i, j).getIsOpen()) {
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

    public Cell getCell(int i, int j) {
        return mineField.get(i).get(j);
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
