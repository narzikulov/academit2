package ru.academit.narzikulov.minesweeper.text;

import ru.academit.narzikulov.minesweeper.Cell;
import ru.academit.narzikulov.minesweeper.Minesweeper;

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
                    setTheMineFieldSize();
                    startGame();
                    break;
            }
        } while (!minesweeper.getGameIsLost() || !minesweeper.isAllCellsOpen());

        if (minesweeper.getGameIsLost()) {
            System.out.println("You lost the game!");
        } else {
            System.out.println("You won the game!");
        }
    }

    private void setTheMineFieldSize() {
        System.out.print("Input num of rows, columns, mines or ANY for default size (9x9 with 10 mines): ");
        Scanner scn = new Scanner(System.in);
        int rows;
        int columns;
        int mines;
        try {
            rows = scn.nextInt();
            columns = scn.nextInt();
            mines = scn.nextInt();
            minesweeper = new Minesweeper(rows, columns, mines);
        } catch (InputMismatchException e) {
            System.out.println("Default size selected.");
        }
        mineField = minesweeper.getMineField();
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
        printOpenedMineField();
        printMineField();
        Scanner scn = new Scanner(System.in);
        do {
            if (minesweeper.getGameIsOver()) {
                minesweeper.openAllCell();
                break;
            }

            System.out.println("Enter you turn please ('action' 'i' and 'j', 'e' - exit, o - open cell, " +
                    "f - flag/question/clear, a - open cells around):");
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

            if (action.equals("f") && cell.getIsMineFound()) {
                minesweeper.getCell(iTurn, jTurn).setIsMineFound(false);
                minesweeper.getCell(iTurn, jTurn).setUnderQuestion(true);
            }

            if (action.equals("f") && !cell.getIsOpen() && !cell.getUnderQuestion()) {
                minesweeper.getCell(iTurn, jTurn).setIsMineFound(true);
                System.out.println(minesweeper.getCell(iTurn, jTurn).getIsMineFound());
            }



            if (action.equals("f") && cell.getUnderQuestion()) {
                minesweeper.getCell(iTurn, jTurn).setUnderQuestion(false);
            }

            System.out.println("Action: " + action + "; turn: " + iTurn + ", " + jTurn);
            printOpenedMineField();
            printMineField();

        } while (!minesweeper.gameIsWon() || !minesweeper.getGameIsLost());

        if (minesweeper.getGameIsLost()) {
            System.out.println("You lost the game");
        }

        if (minesweeper.getGameIsWon()) {
            System.out.println("You won the game");
            System.out.println("Your scores: " + minesweeper.getScores());
        }
    }

    public void printMineField() {
        for (int i = 0; i < minesweeper.getMineField().size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
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

    public void printOpenedMineField() {
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
