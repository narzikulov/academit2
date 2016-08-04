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

    public void startGame() {
        printMineField();
        Scanner scn = new Scanner(System.in);
        do {
            System.out.println("Enter you turn please ('i' and 'j') and action ('e' - exit):");
            try {
                iTurn = scn.nextInt();
                jTurn = scn.nextInt();
                if (checkTurn(iTurn, jTurn)) {

                }

            } catch (InputMismatchException e) {
                break;
            }

            action = scn.nextLine();
            if (action.equals("e")) {
                break;
            }

            if (action.equals("o")) {
                minesweeper.openCell(iTurn, jTurn);
            }

            System.out.println("Action: " + action + "; turn: " + iTurn + ", " + jTurn);

        } while (!minesweeper.getGameIsOver() || !action.equals("e"));

/*        do {
            System.out.println("Enter you turn please ('i' and 'j') or ANY to stop the game: ");
            boolean correctTurn;
            do {
                correctTurn = true;
                try {
                    iTurn = scn.nextInt();
                    jTurn = scn.nextInt();
                    if (iTurn < 0 || iTurn >= mineField.size() ||
                            jTurn < 0 || jTurn >= mineField.get(0).size()) {
                        System.out.println("You are over the mine field, try again");
                        correctTurn = false;
                    }
                } catch (InputMismatchException e) {
                    //System.out.println(e);
                    return;
                }
            } while(!correctTurn);

            System.out.println("Opened minefield");
            printOpenedMineField();
            if (mineField.get(iTurn).get(jTurn).getIsMine()) {
                //System.out.println("You lose! It's mine.");
                minesweeper.setGameIsLost(true);
                minesweeper.openAllCell();
            }

            minesweeper.openCell(iTurn, jTurn);
            printMineField();

        } while (!minesweeper.isAllCellsOpen()); */
    }

    public void printMineField() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j).getIsOpen()) {
                    if (mineField.get(i).get(j).getIsMine()) {
                        System.out.printf("%3s", "*");
                    }
                    if (mineField.get(i).get(j).getIsMineFound()) {
                        System.out.printf("%3s", "M");
                    }
                    if (mineField.get(i).get(j).getUnderQuestion()) {
                        System.out.printf("%3s", "?");
                    } else {
                        System.out.printf("%3d", mineField.get(i).get(j).getMinesAround());
                    }
                } else System.out.printf("%3s", "x");
            }
            System.out.println();
        }
        System.out.println("_________________________________________");
    }

    public void printOpenedMineField() {
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j).getIsMine()) {
                    System.out.printf("%3s", "*");
                } else {
                    System.out.printf("%3d", mineField.get(i).get(j).getMinesAround());
                }
            }
            System.out.println();
        }
        System.out.println("_________________________________________");
    }
}
