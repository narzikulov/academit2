package ru.academit.narzikulov;

/**
 * Created by tim on 10.12.2015.
 */
public class DoubleNumsCompare {
    private static final double epsilon = 0.00001;

    private DoubleNumsCompare() {
    }

    public static boolean areDoubleNumsEqual(double num1, double num2) {
        return Math.abs(num1 - num2) < epsilon;
    }
}
