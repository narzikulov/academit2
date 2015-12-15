package ru.academit.narzikulov;

/**
 * Created by tim on 10.12.2015.
 */
public enum LinearSystemSolveResultCode {
    NO_SOLVES,
    ONE_SOLVE,
    MANY_SOLVES;

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
