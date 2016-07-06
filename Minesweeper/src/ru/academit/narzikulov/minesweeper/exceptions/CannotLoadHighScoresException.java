package ru.academit.narzikulov.minesweeper.exceptions;

/**
 * Created by User on 03.07.2016.
 */
public class CannotLoadHighScoresException extends Exception {
    public CannotLoadHighScoresException(Exception e) {
        super(e);
    }
}
