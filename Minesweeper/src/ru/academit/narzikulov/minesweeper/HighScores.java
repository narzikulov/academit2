package ru.academit.narzikulov.minesweeper;

import ru.academit.narzikulov.minesweeper.exceptions.CannotLoadHighScoresException;
import ru.academit.narzikulov.minesweeper.exceptions.UnableWriteHighScoresException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private ArrayList<Winner> highScoresTable;

    public HighScores(String highScoresFileName, String logFileName){
        this.fileName = highScoresFileName;
        fileLogger = new FileLogger(logFileName);
    }

    public String makeString() {
        try {
            read();
        } catch (CannotLoadHighScoresException e) {
            fileLogger.writeLog(e);
            try {
                throw new UnableWriteHighScoresException(e);
            } catch (UnableWriteHighScoresException e1) {
                e1.printStackTrace();
            }
        }

        StringBuilder scoresTable = new StringBuilder();
        for (int i = 0; i < highScoresTable.size(); ++i) {
            if (i == OUTPUT_NUM_OF_WINNERS) {
                break;
            }
            scoresTable.append(String.format("%6d %s %n",
                    highScoresTable.get(i).getRecord(), highScoresTable.get(i).getName()));
        }

        return scoresTable.toString();
    }

    private void read() throws CannotLoadHighScoresException {
        if (highScoresTable != null) {
            return;
        }
        highScoresTable = new ArrayList<>();
        try (Scanner highScoresFile = new Scanner(new FileInputStream(fileName))) {
            while (highScoresFile.hasNextLine()) {
                String[] str = highScoresFile.nextLine().split(":");
                int record = Integer.valueOf(str[0]);
                String name = str[1];
                highScoresTable.add(new Winner(record, name));
            }
        } catch (IOException e) {
            fileLogger.writeLog(e);
            throw new CannotLoadHighScoresException(e);
        }
    }

    public void write(Winner winner) throws CannotLoadHighScoresException, UnableWriteHighScoresException {
        //highScoresTable = new ArrayList<>();
        add(highScoresTable, winner);

        try (PrintWriter highScoresFile = new PrintWriter(new FileWriter(fileName))) {
            for (Winner aHighScoresTable : highScoresTable) {
                highScoresFile.println(aHighScoresTable.toString());
            }
        } catch (IOException e) {
            throw new UnableWriteHighScoresException(e);
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