package ru.academit.narzikulov.minesweeper;

/**
 * Created by tim on 21.06.2016.
 */
public class Winner {
    private int record;
    private String name;

    public Winner(int record, String name) {
        this.record = record;
        this.name = name;
    }

    public Winner() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getName() {
        return this.name;
    }

    public int getRecord() {
        return record;
    }

    public String toString() {
        return String.format("%d:%s", record, name);
    }
}

