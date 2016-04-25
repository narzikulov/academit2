package ru.academit.narzikulov;

import java.awt.*;

/**
 * Created by tim on 20.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        //VectorMain.main();
        //MatrixMain.main();
        //GaussMain.main();
        //ShapeMain.main();
        //HashTableMain.main();

        Range range1 = new Range(10, 15);
        System.out.println("Range length: " + range1.length());

        double num = 5;
        System.out.printf("Is the num %.2f is inside range (%.2f; %.2f)? : %b", num, range1.getFrom(), range1.getTo(),
                range1.isInside(num));

        Range range2 = new Range(3, 8);

        System.out.println();
        System.out.println("Range1: " + range1.toString() + ", Range2: " + range2.toString());

        Range range3 = range1.intersection(range2);
        if (range3 == null) {
            System.out.println("There is no intersection.");
        } else {
            System.out.println("Intersection: " + range3.toString());
        }

        System.out.println("Union: " + range1.union(range2).toString());

        System.out.println("Subtraction:");
        Range[] range4 = range1.subtraction(range2);
        for (int i = 0; i < range4.length; ++i) {
            if (range4[i] != null) {
                System.out.println(range4[i].toString());
            }
        }
    }
}
