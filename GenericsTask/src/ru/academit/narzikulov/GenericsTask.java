package ru.academit.narzikulov;

import com.sun.javafx.binding.StringFormatter;

/**
 * Created by tim on 21.05.2016.
 */
public class GenericsTask<K, V> {
    private K kValue;
    private V vValue;

    public GenericsTask() {
    }

    public GenericsTask(K kValue, V vValue) {
        this.kValue = kValue;
        this.vValue = vValue;
    }

    public K getKValue() {
        return this.kValue;
    }

    public V getVValue() {
        return this.vValue;
    }

    public void setKValue(K kValue) {
        this.kValue = kValue;
    }

    public void setVValue(V vValue) {
        this.vValue = vValue;
    }

    public String toString() {
        return String.format("[ %s, %s ]", kValue, vValue);
    }

}
