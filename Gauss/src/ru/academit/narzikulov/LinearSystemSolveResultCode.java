package ru.academit.narzikulov;

/**
 * Created by tim on 10.12.2015.
 */
public enum LinearSystemSolveResultCode {
    NO_SOLVES ("Система не имеет решений"),
    ONE_SOLVE (""),
    MANY_SOLVES("Система имеет множество решений");

    LinearSystemSolveResultCode(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return this.message;
    }
}
