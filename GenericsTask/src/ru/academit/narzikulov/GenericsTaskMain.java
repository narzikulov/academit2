package ru.academit.narzikulov;

/**
 * Created by tim on 21.05.2016.
 */
public class GenericsTaskMain {
    public static void main() {
        String str = "Hello world!";
        Integer num = 5;
        GenericsTask pair = new GenericsTask<String, Integer>(str, num);
        System.out.println(pair.toString());
    }
}
