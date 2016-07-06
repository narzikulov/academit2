package ru.academit.narzikulov.minesweeper.exceptions;

/**
 * Created by tim on 06.07.2016.
 */
public class UnableWriteHighScoresException extends Exception {
    public UnableWriteHighScoresException(Exception e) {
        super(e);
    }
}
