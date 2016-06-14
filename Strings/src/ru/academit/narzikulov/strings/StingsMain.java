package ru.academit.narzikulov.strings;

/**
 * Created by tim on 11.06.2016.
 */
public class StingsMain {
    public static void main() {
        Strings str = new Strings(1, 100);
        System.out.println(str.getString());

        Strings str1 = new Strings("1, 2, 3, 4, 5");
        System.out.println(str1.getString());
    }
}
