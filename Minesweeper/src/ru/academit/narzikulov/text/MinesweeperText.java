package ru.academit.narzikulov.text;

import com.sun.corba.se.impl.io.TypeMismatchException;
import ru.academit.narzikulov.Cell;
import ru.academit.narzikulov.Minesweeper;

import java.text.NumberFormat;
import java.util.*;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperText {
    private Minesweeper minesweeper = new Minesweeper();
    private int iTurn;
    private int jTurn;

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
        } while (!minesweeper.getTheGameIsLost() || !minesweeper.isAllCellsOpen());

        if (minesweeper.getTheGameIsLost()) {
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
        int numOfMines;
        try {
            rows = scn.nextInt();
            columns = scn.nextInt();
            numOfMines = scn.nextInt();
            minesweeper = new Minesweeper(rows, columns, numOfMines);
        } catch (InputMismatchException e) {
            minesweeper = new Minesweeper();
        }
    }

    private void startGame() {
        printMineField();
        Scanner scn = new Scanner(System.in);

        do {
            System.out.println("Enter you turn please ('i' and 'j') or ANY to stop the game: ");
            try {
                iTurn = scn.nextInt();
                jTurn = scn.nextInt();
                if (iTurn < 0 || iTurn >= minesweeper.getMineField().size() ||
                        jTurn < 0 || jTurn >= minesweeper.getMineField().get(0).size()) {
                    System.out.println("You are over the mine field, try again");
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
                return;
            }

            if (minesweeper.isCellIsMine(iTurn, jTurn)) {
                //System.out.println("You lose! It's mine.");
                minesweeper.setTheGameIsLost(true);
                minesweeper.openAllCell();

            }

            minesweeper.openCell(iTurn, jTurn);
            printMineField();

        } while (!minesweeper.isAllCellsOpen());
    }

    public void printMineField() {
        ArrayList<ArrayList<Cell>> mineField = minesweeper.getMineField();
        for (int i = 0; i < mineField.size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                if (mineField.get(i).get(j).getIsOpen()) {
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

    //Временный метод для debug GUI класса
    public static void printMineField(Minesweeper ms) {
        ArrayList<ArrayList<Cell>> mineField = ms.getMineField();
        for (int i = 0; i < ms.getMineField().size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                System.out.printf("%5d", mineField.get(i).get(j));
            }
            System.out.println();
        }
    }


}
