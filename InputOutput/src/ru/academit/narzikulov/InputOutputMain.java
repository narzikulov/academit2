package ru.academit.narzikulov;

import java.io.FileNotFoundException;

/**
 * Created by tim on 04.06.2016.
 */
public class InputOutputMain {
    public static void main() throws FileNotFoundException {
        InputOutput file = new InputOutput("text.txt");
        System.out.println(file.getList().toString());
    }
}
