package ru.academit.narzikulov.text;

import ru.academit.narzikulov.Minesweeper;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tim on 18.05.2016.
 */
public class MinesweeperText {
    private Minesweeper minesweeper = new Minesweeper();
    private int iTurnCoordinate;
    private int jTurnCoordinate;

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
        //startGame();
    }

    public void startGame() {
        printClosedMineField();
        Scanner scn = new Scanner(System.in);

        do {
            System.out.println("Enter you turn please (i, j): ");
            iTurnCoordinate = scn.nextInt();
            jTurnCoordinate = scn.nextInt();

        } while (!minesweeper.isChoosenCellIsMine(iTurnCoordinate, jTurnCoordinate));

        printMineField();
        System.out.println("Game over!");



    }

    public void printMineField() {
        ArrayList<ArrayList<Integer>> mineField = minesweeper.getMineField();
        for (ArrayList<Integer> aMineField : mineField) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                System.out.printf("%3d", aMineField.get(j));
            }
            System.out.println();
        }
    }

    public void printClosedMineField() {
        ArrayList<ArrayList<Integer>> mineField = minesweeper.getMineField();
        for (ArrayList<Integer> aMineField : mineField) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                System.out.printf("  x ");
            }
            System.out.println();
        }
    }

    //Временный метод для debug GUI класса
    public static void printMineField(Minesweeper ms) {
        ArrayList<ArrayList<Integer>> mineField = ms.getMineField();
        for (int i = 0; i < ms.getMineField().size(); ++i) {
            for (int j = 0; j < mineField.get(0).size(); ++j) {
                System.out.printf("%3d", mineField.get(i).get(j));
            }
            System.out.println();
        }
    }


}
