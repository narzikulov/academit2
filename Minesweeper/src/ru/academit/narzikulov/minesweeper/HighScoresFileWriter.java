package ru.academit.narzikulov.minesweeper;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by tim on 27.06.2016.
 */
public class HighScoresFileWriter {
    //Ограниченное количество игроков, выводящихся в таблице победителей
    private final static int OUTPUT_NUM_OF_WINNERS = 20;
    private String highScoresFileName;
    private String logFileName;

    public HighScoresFileWriter(String highScoresFileName, String logFileName) {
        this.highScoresFileName = highScoresFileName;
        this.logFileName = logFileName;
    }

    public String highScoresTableToString() {
        StringBuilder scoresTable = new StringBuilder();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(highScoresFileName))) {
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
            try (PrintWriter logFile = new PrintWriter(new FileWriter(logFileName))) {
                logFile.println(new Date(System.currentTimeMillis()));
                logFile.println(new Time(System.currentTimeMillis()));
                logFile.println(e);
                logFile.println("High scores file not found!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return scoresTable.toString();
    }

    public void addWinnerToHighScoresFile(Winner winner) throws IOException {
        ArrayList<Winner> highScoresTable = new ArrayList<>();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(highScoresFileName))) {
            while (highScoresFile.hasNextLine()) {
                String[] str = highScoresFile.nextLine().split(":");
                int record = Integer.valueOf(str[0]);
                String name = str[1];
                highScoresTable.add(new Winner(record, name));
            }
        } catch (IOException e) {
            try (PrintWriter logFile = new PrintWriter(new FileWriter(logFileName))) {
                logFile.println(e);
                logFile.println("Error reading High Scores file");
            }
        }

        if (winner.getName() == null) {
            return;
        }

        addWinnerToHighScoresTable(highScoresTable, winner);

        try (PrintWriter highScoresFile = new PrintWriter(new FileWriter(highScoresFileName))) {
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
