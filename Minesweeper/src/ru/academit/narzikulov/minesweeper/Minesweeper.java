package ru.academit.narzikulov.minesweeper;

import ru.academit.narzikulov.minesweeper.exceptions.CannotLoadHighScoresException;
import ru.academit.narzikulov.minesweeper.exceptions.UnableWriteHighScoresException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tim on 18.05.2016.
 */
public class Minesweeper {
    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    public static final int MINES = 10;

    public static final int MIN_ROWS = 5;
    public static final int MIN_COLUMNS = 5;
    public static final int MIN_MINES = 5;
    public static final int MAX_ROWS = 25;
    public static final int MAX_COLUMNS = 50;

    private int rows;
    private int columns;
    private int mines;
    private ArrayList<ArrayList<Cell>> mineField = new ArrayList<>();
    private ArrayList<CellCoordinate> cellCoordinate = new ArrayList<>();
    private boolean gameIsLost = false;
    private boolean gameIsWon = false;
    private boolean gameIsOver = false;

    private boolean isGameStarted;
    private int scores;
    private long playingTime;

    private Winner winner = new Winner();

    private String pathToFiles = "Minesweeper\\src\\ru\\academit\\narzikulov\\minesweeper\\";
    private String highScoresFileName = pathToFiles + "hs.txt";
    private String logFileName = pathToFiles + "log.txt";
    private HighScores highScoresFile = new HighScores(highScoresFileName, logFileName);

    public Minesweeper(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        //Если количество мин указано больше, чем количество игровых ячеек, тогда
        //пусть количество мин = количеству игровых ячеек поля - 1
        if (mines >= rows * columns) {
            this.mines = (rows - 1) * (columns - 1);
        }

        for (int i = 0; i < rows; ++i) {
            mineField.add(new ArrayList<Cell>());
            for (int j = 0; j < columns; ++j) {
                mineField.get(i).add(new Cell(0));
            }
        }
        setMines();
    }

    public Minesweeper() {
        this(ROWS, COLUMNS, MINES);
    }

    //Проверка указанных размерностей минного поля
    public boolean checkMinefiledEnteredDim (int rows, int columns, int mines) {
        return (MIN_ROWS <= rows && rows <= MAX_ROWS &&
                MIN_COLUMNS <= columns && columns <= MAX_COLUMNS &&
                MIN_MINES <= mines);
    }

    //Заполнение ячеек игрового поля нулями
    private void makeEmptyMineField() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                mineField.get(i).get(j).setMinesAround(0);
                mineField.get(i).get(j).setIsMine(false);
            }
        }
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

    public boolean indexesAreNotOutOfBounds(int i, int j) {
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
            if (!getCell(i, j).getIsMine() && getCell(i, j).getMinesAround() != 0 && !getCell(i, j).getIsMineFound()) {
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

    private int numberFoundedMinesAroundCell(int i, int j) {
        int numberFoundedMines = 0;
        for (int x = i - 1; x <= i + 1; ++x) {
            for (int y = j - 1; y <= j + 1; ++y) {
                if (!indexesAreNotOutOfBounds(x, y)) {
                    continue;
                }
                if (getCell(x, y).getIsMineFound()) {
                    ++numberFoundedMines;
                }
            }
        }
        return numberFoundedMines;
    }

    private boolean isAllFoundedMinesAreRealyMines(int i, int j) {
        for (int x = i - 1; x <= i + 1; ++x) {
            for (int y = j - 1; y <= j + 1; ++y) {
                if (!indexesAreNotOutOfBounds(x, y)) {
                    continue;
                }
                if (getCell(x, y).getIsMine() && !getCell(x, y).getIsMineFound()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void openCellsAroundForOpenedCell(int i, int j) {
        if (!indexesAreNotOutOfBounds(i, j)) {
            return;
        }

        if (getCell(i, j).getIsOpen() && numberFoundedMinesAroundCell(i, j) == getCell(i, j).getMinesAround()) {
            if (!isAllFoundedMinesAreRealyMines(i, j)) {
                gameIsLost = true;
                gameIsOver = true;
                return;
            }
            openCell(i - 1, j);
            openCell(i - 1, j - 1);
            openCell(i - 1, j + 1);
            openCell(i, j - 1);
            openCell(i, j + 1);
            openCell(i + 1, j - 1);
            openCell(i + 1, j);
            openCell(i + 1, j + 1);
        }
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
        return !(indexesAreNotOutOfBounds(i, j) && getCell(i, j).getIsMine());
    }

    public boolean gameIsWon() {
        if (gameIsLost) {
            gameIsWon = false;
            return false;
        }
        if (!isAllCellsOpen()) {
            return false;
        }
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (getCell(i, j).getIsMine() && getCell(i, j).getIsOpen()) {
                    return false;
                }
            }
        }
        gameIsWon = true;
        gameIsOver = true;
        //TODO додумать метод расчета очков за игру для разных размерностей полей
        scores = (int) (System.currentTimeMillis() - playingTime) / 1000;
        winner.setRecord(scores);
        return true;
    }

    public void setWinnerName(String playerName) throws CannotLoadHighScoresException, UnableWriteHighScoresException {
        if ("".equals(playerName)) {
            winner.setName("Anonymous");
        } else {
            winner.setName(playerName);
        }
        highScoresFile.write(winner);
    }

    public HighScores getHighScoresFile() {
        return highScoresFile;
    }

    public Winner getWinner() {
        return winner;
    }

    public int getScores() {
        return scores;
    }

    public void openCell(int iTurn, int jTurn) {
        //Если первый ход попадает на мину, то переделать минное поле
        if (!isGameStarted) {
            while (getCell(iTurn, jTurn).getIsMine()) {
                makeEmptyMineField();
                setMines();
            }
            //Начало отсчета времени после первого хода
            playingTime = System.currentTimeMillis();
        }

        //Первый ход
        isGameStarted = true;

        if (iTurn < 0 || iTurn >= mineField.size() ||
                jTurn < 0 || jTurn >= mineField.get(0).size()) {
            return;
        }

        if (!getCell(iTurn, jTurn).getIsMineFound()) {
            if (getCell(iTurn, jTurn).getIsMine()) {
                gameIsLost = true;
                gameIsOver = true;
                return;
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

    public int getMines() {
        return mines;
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

    public boolean getGameIsWon() {
        return gameIsWon;
    }

    public boolean getIsGameStarted() {
        return isGameStarted;
    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }

    public long getPlayingTime() {
        return playingTime;
    }

    public String about() {
        String about1 = "Winesweeper v. 1.0";
        String about2 = "https://github.com/narzikulov/academit2/tree/master/Minesweeper";
        return String.format("[ %s%n%s ]", about1, about2);
    }
}