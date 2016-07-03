package ru.academit.narzikulov.minesweeper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;

/**
 * Created by tim on 29.06.2016.
 */
public class FileLogger {
    private String logFileName;
    public FileLogger(String logFileName) {
        this.logFileName = logFileName;
    }

    public void writeLog(Exception e) {
        try (PrintWriter logFile = new PrintWriter(new FileWriter(logFileName))) {
            logFile.println(new Date(System.currentTimeMillis()));
            logFile.println(new Time(System.currentTimeMillis()));
            logFile.println(e);
            logFile.println("Error reading High Scores file!");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
