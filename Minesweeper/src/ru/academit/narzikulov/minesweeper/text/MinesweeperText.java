package ru.academit.narzikulov.minesweeper.text;

import ru.academit.narzikulov.minesweeper.Cell;
import ru.academit.narzikulov.minesweeper.Minesweeper;
import ru.academit.narzikulov.minesweeper.exceptions.CannotLoadHighScoresException;
import ru.academit.narzikulov.minesweeper.exceptions.UnableWriteHighScoresException;

import java.util.ArrayList;
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
    private ArrayList<ArrayList<Cell>> mineField;

    public MinesweeperText() {
        Scanner scn = new Scanner(System.in);

        /*System.out.println("Default game field size is 9x9 with 10 mines.");
        System.out.println("Press 'y + ENTER' to input new dimension and num of mines or just ENTER to use default size?");
        System.out.println("Press 'e' to STOP the game.");
        if (scn.nextLine().equalsIgnoreCase("e")) {
            System.exit(1);
        }
        if (scn.nextLine().equalsIgnoreCase("y")) {
        }
        */


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
                    if (setTheMineFieldSize()) {
                        startGame();
                    }
                    break;
                case "s":
                    System.out.println(minesweeper.getHighScoresFile().makeString());
            }
        } while (true);
    }

    private boolean setTheMineFieldSize() {
        System.out.printf("Minimum number of rows: %d, columns: %d, mines: %d\n",
                Minesweeper.MIN_ROWS, Minesweeper.MIN_COLUMNS, Minesweeper.MIN_MINES);
        System.out.printf("Maximum number of rows: %d, columns: %d\n",
                Minesweeper.MAX_ROWS, Minesweeper.MAX_COLUMNS);
        System.out.println("Input num of rows, columns, mines or ANY for default size (9x9 with 10 mines): ");
        Scanner scn = new Scanner(System.in);
        int rows;
        int columns;
        int mines;
        try {
            rows = scn.nextInt();
            columns = scn.nextInt();
            mines = scn.nextInt();
            if (minesweeper.checkMinefiledEnteredDim(rows, columns, mines)) {
                minesweeper = new Minesweeper(rows, columns, mines);
            } else {
                System.out.println("Incorrect number rows, columns or mines. Default size will be used!");
                return false;
            }
        } catch (InputMismatchException e) {
            System.out.println("Default or previous game size selected.");
        }
        return true;
        //mineField = minesweeper.getMineField();
    }

    private boolean checkTurn(int i, int j) {
        return !(iTurn < 0 || iTurn >= mineField.size() || jTurn < 0 || jTurn >= mineField.get(0).size());
    }

    private boolean parseTurn(String[] turn) {
        if (turn[0].equals("e")) {
            action = "e";
            return true;
        }

        if (turn.length < 3) {
            return false;
        }

        action = turn[0];
        if (!action.equals("o")) {
            if (!action.equals("f")) {
                if (!action.equals("a")) {
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
        //printOpenedMineField();
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
            String[] turn = scn.nextLine().split(" ");
            if (!parseTurn(turn)) {
                System.out.println("Incorrect turn, try again.");
                continue;
            }

            if (action.equals("e")) {
                break;
            }

            Cell cell = minesweeper.getCell(iTurn, jTurn);

            if (action.equals("o") && !cell.getIsMineFound()) {
                minesweeper.openCell(iTurn, jTurn);
            }

            if (action.equals("f") && !cell.getIsOpen()) {
                if (!cell.getIsMineFound() && !cell.getUnderQuestion()) {
                    minesweeper.getCell(iTurn, jTurn).setIsMineFound(true);
                } else if (cell.getIsMineFound()) {
                    minesweeper.getCell(iTurn, jTurn).setIsMineFound(false);
                    minesweeper.getCell(iTurn, jTurn).setUnderQuestion(true);
                } else {
                    minesweeper.getCell(iTurn, jTurn).setUnderQuestion(false);
                }
            }

            if (action.equals("a")) {
                minesweeper.openCellsAroundForOpenedCell(iTurn, jTurn);
                if (minesweeper.getGameIsLost()) {
                    minesweeper.openAllCell();
                    break;
                }
            }


            System.out.println("Action: " + actionCodeToString(action) + " cell(" + iTurn + ", " + jTurn + ")");

            //Debug. Every turn printed opened minefield above.
            //printOpenedMineField();

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
            } catch (CannotLoadHighScoresException e) {
                e.printStackTrace();
            } catch (UnableWriteHighScoresException e) {
                e.printStackTrace();
            }
        }
    }

    private String actionCodeToString(String actionCode) {
        if (actionCode.equals("e")) {
            return "Exit";
        }
        if (actionCode.equals("o")) {
            return "Open";
        }
        if (actionCode.equals("f")) {
            return "Set flag/question/clear";
        }
        if (actionCode.equals("a")) {
            return "Open all cells around selected one";
        }
        return "";
    }

    public void printMineField() {
        for (int i = 0; i < minesweeper.getMineField().size(); ++i) {
            for (int j = 0; j < minesweeper.getMineField().get(0).size(); ++j) {
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
        System.out.println("_________________________________________");
    }

    private void printOpenedMineField() {
        for (int i = 0; i < minesweeper.getMineField().size(); ++i) {
            for (int j = 0; j < minesweeper.getMineField().get(0).size(); ++j) {
                if (minesweeper.getCell(i, j).getIsMine()) {
                    System.out.printf("%3s", "*");
                } else {
                    System.out.printf("%3d", minesweeper.getCell(i, j).getMinesAround());
                }
            }
            System.out.println();
        }
        System.out.println("_________________________________________");
    }
}
