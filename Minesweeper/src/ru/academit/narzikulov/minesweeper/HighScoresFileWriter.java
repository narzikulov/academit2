package ru.academit.narzikulov.minesweeper;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static ru.academit.narzikulov.minesweeper.Minesweeper.HIGH_SCORES_FILE_NAME;

/**
 * Created by tim on 27.06.2016.
 */
public class HighScoresFileWriter {


    //Ограниченное количество игроков, выводящихся в таблице победителей
    private final static int OUTPUT_NUM_OF_WINNERS = 20;

    public HighScoresFileWriter() {
    }

    public String highScoresTableToString() {
        StringBuilder scoresTable = new StringBuilder();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(HIGH_SCORES_FILE_NAME))) {
            int i = 0;
            while (highScoresFile.hasNextLine()) {
                ++i;
                String[] str = highScoresFile.nextLine().split(":");
                int record = Integer.valueOf(str[0]);
                String name = str[1];
                scoresTable.append(String.format("%6d %s %n", record, name));
                if (i == OUTPUT_NUM_OF_WINNERS) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("High scores fle not found!");
        }
        return scoresTable.toString();
    }

    public void highScoresFileWriter(Minesweeper minesweeper) throws IOException {
        ArrayList<Winner> highScoresTable = new ArrayList<>();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(HIGH_SCORES_FILE_NAME))) {
            while (highScoresFile.hasNextLine()) {
                String[] str = highScoresFile.nextLine().split(":");
                int record = Integer.valueOf(str[0]);
                String name = str[1];
                highScoresTable.add(new Winner(record, name));
            }
        } catch (IOException ignored) {
        }

        if (minesweeper.getPlayerName() == null) {
            return;
        }

        Winner winner = new Winner(minesweeper.getScores(), minesweeper.getPlayerName());
        addWinnerToHighScoresTable(highScoresTable, winner);

        try (PrintWriter highScoresFile = new PrintWriter(new FileWriter(HIGH_SCORES_FILE_NAME))) {
            for (Winner aHighScoresTable : highScoresTable) {
                highScoresFile.println(aHighScoresTable.toString());
            }
        }
    }

    private void addWinnerToHighScoresTable(ArrayList<Winner> highScoresTable, Winner winner) {
        int index = -1;
        for (int i = 0; i < highScoresTable.size(); ++i) {
            if (winner.getRecord() <= highScoresTable.get(i).getRecord()) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            highScoresTable.add(winner);
            return;
        }

        highScoresTable.add(index, winner);
    }
}
