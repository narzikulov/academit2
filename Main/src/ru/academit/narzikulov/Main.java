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

        Point p = new Point(0, 0);
        Point p1 = new Point(3, 4);
        p.printCoordinates();
        p1.printCoordinates();
        System.out.println();
        System.out.println("Dist:" + p.dist(p1));
    }
}
