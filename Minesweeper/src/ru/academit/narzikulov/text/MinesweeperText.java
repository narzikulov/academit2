package ru.academit.narzikulov.text;

import ru.academit.narzikulov.Cell;
import ru.academit.narzikulov.Minesweeper;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperText {
    private Minesweeper minesweeper = new Minesweeper();
    private int iTurn;
    private int jTurn;

    public MinesweeperText() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Default game field is 9x9 with 10 mines. Would you like to input new dimension and num of mines? y/n");
        if (scn.nextLine().equals("y")) {
            System.out.print("Input num of rows, columns, mines: ");
            int rows = scn.nextInt();
            int columns = scn.nextInt();
            int numOfMines = scn.nextInt();
            minesweeper = new Minesweeper(rows, columns, numOfMines);
        }
        startGame();
    }

    public void startGame() {
        printMineField();
        Scanner scn = new Scanner(System.in);

        do {
            System.out.println("Enter you turn please (i, j): ");
            iTurn = scn.nextInt();
            jTurn = scn.nextInt();

            if (minesweeper.isChoosenCellIsMine(iTurn, jTurn)) {
                System.out.println("You lose! It's mine.");
                break;
            }

            minesweeper.openCell(iTurn, jTurn);
            printMineField();

        } while (!minesweeper.isChoosenCellIsMine(iTurn, jTurn));

        minesweeper.openAllCell();
        System.out.println("-------------------------------");
        printMineField();
        System.out.println("Game over!");



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
