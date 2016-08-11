package ru.academit.narzikulov.minesweeper.text;

import ru.academit.narzikulov.minesweeper.Cell;
import ru.academit.narzikulov.minesweeper.Minesweeper;
import ru.academit.narzikulov.minesweeper.exceptions.CannotLoadHighScoresException;
import ru.academit.narzikulov.minesweeper.exceptions.UnableWriteHighScoresException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperText {
    private Minesweeper minesweeper = new Minesweeper();
    private int iTurn;
    private int jTurn;
    private String action;
    private final static String ACTION_EXIT = "e";
    private final static String ACTION_OPEN = "o";
    private final static String ACTION_FLAG = "f";
    private final static String ACTION_OPENAROUND = "a";

    public MinesweeperText() {
        Scanner scn = new Scanner(System.in);

        do {
            System.out.print("Input command:\n'e' Exit\n'a' About\n'n' New Game\n's' High Scores\n");
            String userCommand = scn.nextLine();
            switch (userCommand) {
                case "e":
                    System.exit(1);
                    break;
                case "a":
                    System.out.println(minesweeper.about());
                    break;
                case "n":
                    if (setMineFieldSize()) {
                        startGame();
                    }
                    break;
                case "s":
                    System.out.println(minesweeper.getHighScoresFile().makeString());
            }
        } while (true);
    }

    private boolean setMineFieldSize() {
        System.out.printf("Minimum number of rows: %d, columns: %d, mines: %d\n",
                Minesweeper.MIN_ROWS, Minesweeper.MIN_COLUMNS, Minesweeper.MIN_MINES);
        System.out.printf("Maximum number of rows: %d, columns: %d\n",
                Minesweeper.MAX_ROWS, Minesweeper.MAX_COLUMNS);
        System.out.println("Input num of rows, columns, mines or ANY for default size (9x9 with 10 mines): ");
        Scanner scn = new Scanner(System.in);

        try {
            int rows = scn.nextInt();
            int columns = scn.nextInt();
            int mines = scn.nextInt();
            if (minesweeper.checkMinefiledEnteredDim(rows, columns, mines)) {
                minesweeper = new Minesweeper(rows, columns, mines);
            } else {
                System.out.println("Incorrect number rows, columns or mines. Default size will be used!");
                return false;
            }
        } catch (InputMismatchException e) {
            minesweeper = new Minesweeper();
            System.out.println("Default minefield size selected.");
        }
        return true;
    }

    private boolean parseTurn(String unsplitedTurn) {
        String[] turn = unsplitedTurn.split(" ");

        if (turn[0].equals(ACTION_EXIT)) {
            action = ACTION_EXIT;
            return true;
        }

        if (turn.length < 3) {
            return false;
        }

        action = turn[0];
        if (!action.equals(ACTION_OPEN)) {
            if (!action.equals(ACTION_FLAG)) {
                if (!action.equals(ACTION_OPENAROUND)) {
                    return false;
                }
            }
        }

        try {
            iTurn = Integer.parseInt(turn[1]);
            jTurn = Integer.parseInt(turn[2]);
            if (!minesweeper.indexesAreNotOutOfBounds(iTurn, jTurn)) {
                System.out.println("The turn is out of the minefield");
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public void startGame() {
        printMineField();
        Scanner scn = new Scanner(System.in);
        do {
            if (minesweeper.getGameIsOver()) {
                minesweeper.openAllCell();
                break;
            }

            System.out.println("Enter you turn please ('action' 'i' and 'j', 'e' - exit, o - open cell, " +
                    "f - flag/question/clear, a - open cells around):");
            System.out.println("Example: 'o 1 1' - opens cell (1, 1). 'f 2 0' - flags cell (2, 0). 'e' - exit");
            String turn = scn.nextLine();
            if (!parseTurn(turn)) {
                System.out.println("Incorrect turn, try again.");
                continue;
            }

            if (action.equals(ACTION_EXIT)) {
                break;
            }

            Cell cell = minesweeper.getCell(iTurn, jTurn);

            if (action.equals(ACTION_OPEN) && !cell.getIsMineFound()) {
                minesweeper.openCell(iTurn, jTurn);
            }

            if (action.equals(ACTION_FLAG) && !cell.getIsOpen()) {
                if (!cell.getIsMineFound() && !cell.getUnderQuestion()) {
                    minesweeper.getCell(iTurn, jTurn).setIsMineFound(true);
                } else if (cell.getIsMineFound()) {
                    minesweeper.getCell(iTurn, jTurn).setIsMineFound(false);
                    minesweeper.getCell(iTurn, jTurn).setUnderQuestion(true);
                } else {
                    minesweeper.getCell(iTurn, jTurn).setUnderQuestion(false);
                }
            }

            if (action.equals(ACTION_OPENAROUND)) {
                minesweeper.openCellsAroundForOpenedCell(iTurn, jTurn);
                if (minesweeper.getGameIsLost()) {
                    minesweeper.openAllCell();
                    break;
                }
            }

            System.out.printf("Action: %s cell(%d, %d)\n", actionCodeToString(action), iTurn, jTurn);
            printMineField();
        } while (!minesweeper.gameIsWon() || !minesweeper.getGameIsLost());

        if (minesweeper.getGameIsLost()) {
            System.out.println("+--------------------+");
            System.out.println("| You lost the game! |");
            System.out.println("+--------------------+");
            printOpenedMineField();
        }
        if (minesweeper.getGameIsWon()) {
            System.out.println("+-------------------+");
            System.out.println("| You won the game! |");
            System.out.println("+-------------------+");
            System.out.println("Your scores: " + minesweeper.getScores());
            System.out.println("Input winner name please: ");
            try {
                minesweeper.setWinnerName(scn.nextLine());
            } catch (CannotLoadHighScoresException | UnableWriteHighScoresException e) {
                e.printStackTrace();
            }
        }
    }

    private String actionCodeToString(String actionCode) {
        if (actionCode.equals(ACTION_EXIT)) {
            return "Exit";
        }
        if (actionCode.equals(ACTION_OPEN)) {
            return "Open";
        }
        if (actionCode.equals(ACTION_FLAG)) {
            return "Set flag/question/clear";
        }
        if (actionCode.equals(ACTION_OPENAROUND)) {
            return "Open all cells around selected one";
        }
        return "";
    }

    private void printTopNumbers () {
        System.out.printf("   \\ ");
        for (int j = 0; j < minesweeper.getMinefieldHorisontalSize(); ++j) {
            System.out.printf("%3d", j);
        }
        System.out.println();
        System.out.print("     ");
        for (int j = 0; j < minesweeper.getMinefieldHorisontalSize(); ++j) {
            System.out.printf("---");
        }
        System.out.println();
    }

    private void printBottom () {
        System.out.print("     ");
        for (int j = 0; j < minesweeper.getMinefieldHorisontalSize(); ++j) {
            System.out.printf("---");
        }
        System.out.println();
    }

    public void printMineField() {
        printTopNumbers();
        for (int i = 0; i < minesweeper.getMineField().size(); ++i) {
            System.out.printf("%3d| ", i);
            for (int j = 0; j < minesweeper.getMinefieldHorisontalSize(); ++j) {
                if (minesweeper.getCell(i, j).getIsOpen()) {
                    if (minesweeper.getCell(i, j).getIsMine()) {
                        System.out.printf("%3s", "*");
                    } else {
                        System.out.printf("%3d", minesweeper.getCell(i, j).getMinesAround());
                    }
                } else {
                    if (minesweeper.getCell(i, j).getIsMineFound()) {
                        System.out.printf("%3s", "F");
                    } else if (minesweeper.getCell(i, j).getUnderQuestion()) {
                        System.out.printf("%3s", "?");
                    } else {
                        System.out.printf("%3s", "x");
                    }
                }
            }
            System.out.println();
        }

        printBottom();
    }

    private void printOpenedMineField() {
        printTopNumbers();
        for (int i = 0; i < minesweeper.getMineField().size(); ++i) {
            System.out.printf("%3d| ", i);
            for (int j = 0; j < minesweeper.getMinefieldHorisontalSize(); ++j) {
                if (minesweeper.getCell(i, j).getIsMine()) {
                    System.out.printf("%3s", "*");
                } else {
                    System.out.printf("%3d", minesweeper.getCell(i, j).getMinesAround());
                }
            }
            System.out.println();
        }
        printBottom();
    }
}
