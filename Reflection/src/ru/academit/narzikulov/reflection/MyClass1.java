package ru.academit.narzikulov.reflection;

/**
 * Created by tim on 02.07.2016.
 */
public class MyClass1 {
    private long field1;
    private long field2;

    public MyClass1 (long field1, long field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public long sum() {
        return field1 + field2;
    }

    public long mult() {
        return field1 * field2;
    }
}
