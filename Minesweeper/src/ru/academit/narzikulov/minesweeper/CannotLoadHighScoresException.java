package ru.academit.narzikulov.minesweeper;

import java.io.FileNotFoundException;

/**
 * Created by User on 03.07.2016.
 */
public class CannotLoadHighScoresException extends Exception {
    public CannotLoadHighScoresException(Exception e) {
        e.printStackTrace();
    }
}
