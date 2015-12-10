package ru.academit.narzikulov;

/**
 * Created by tim on 10.12.2015.
 */
public class DoubleNumsCompare {
    private static final double epsilon = 0.00001;

    public static boolean AreDoubleNumsEqual(double num1, double num2) {
        return Math.abs(num1 - num2) < epsilon;
    }

    public static boolean CompareNumWithZero(double num) {
        return Math.abs(num) < epsilon;
    }
}
