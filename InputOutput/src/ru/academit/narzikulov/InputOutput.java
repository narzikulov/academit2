package ru.academit.narzikulov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tim on 04.06.2016.
 */
public class InputOutput {
    private Scanner file = null;
    private ArrayList<String> list = new ArrayList<>();

    public InputOutput(String str) throws FileNotFoundException {
        file = new Scanner(new FileInputStream(str));
        addFromFile();
    }

    public void addFromFile() {
        String str;
        while (file.hasNextLine()){
            str = file.nextLine();
            list.add(str);
        }
    }

    public void putToFile() {

    }

    public ArrayList<String> getList() {
        return list;
    }
}
