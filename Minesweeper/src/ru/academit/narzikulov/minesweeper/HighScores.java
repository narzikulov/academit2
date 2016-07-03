package ru.academit.narzikulov.minesweeper;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tim on 27.06.2016.
 */
public class HighScores {
    //Ограниченное количество игроков, выводящихся в таблице победителей
    private final static int OUTPUT_NUM_OF_WINNERS = 20;
    private String fileName;
    private FileLogger fileLogger;

    public HighScores(String highScoresFileName, String logFileName) {
        this.fileName = highScoresFileName;
        fileLogger = new FileLogger(logFileName);
    }

    public String toString() {
        StringBuilder scoresTable = new StringBuilder();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(fileName))) {
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
            fileLogger.writeLog(e);
        }
        if (scoresTable.length() == 0) {
            return "There is no winners yet.";
        }
        return scoresTable.toString();
    }

    public void write(Winner winner) throws CannotLoadHighScoresException {
        ArrayList<Winner> highScoresTable = new ArrayList<>();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(fileName))) {
            while (highScoresFile.hasNextLine()) {
                String[] str = highScoresFile.nextLine().split(":");
                int record = Integer.valueOf(str[0]);
                String name = str[1];
                highScoresTable.add(new Winner(record, name));
            }
        } catch (IOException e) {
            fileLogger.writeLog(e);
            new CannotLoadHighScoresException(e);
        }

        add(highScoresTable, winner);

        try (PrintWriter highScoresFile = new PrintWriter(new FileWriter(fileName))) {
            for (Winner aHighScoresTable : highScoresTable) {
                highScoresFile.println(aHighScoresTable.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(ArrayList<Winner> highScoresTable, Winner winner) {
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