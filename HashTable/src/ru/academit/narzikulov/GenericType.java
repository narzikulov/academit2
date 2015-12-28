package ru.academit.narzikulov;

/**
 * Created by tim on 28.12.2015.
 */
public class GenericType<T> {
    private T value;

    public GenericType(){
    }

    public GenericType(T value) {
        this.value = value;
    }

    public T get() {
        if (value == null) {
            throw new NullPointerException("Value is empty");
        }
        return this.value;
    }
}
