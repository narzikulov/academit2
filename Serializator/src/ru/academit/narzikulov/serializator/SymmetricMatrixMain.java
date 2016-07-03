package ru.academit.narzikulov.serializator;

import java.io.*;

/**
 * Created by User on 03.07.2016.
 */
public class SymmetricMatrixMain {
    public static void main() throws IOException, ClassNotFoundException {
        SymmetricMatrix matrix = new SymmetricMatrix(20);
        SymmetricMatrix matrixLoaded;

        System.out.println("Original matrix");
        matrix.print();

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("Serializator//src//ru//academit//narzikulov//serializator//out.bin"))) {
            out.writeObject(matrix);
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Serializator//src//ru//academit//narzikulov//serializator//out.bin"))) {
            matrixLoaded = (SymmetricMatrix) in.readObject();
        }

        System.out.println();
        System.out.println("Loaded matrix");
        matrixLoaded.print();
    }
}
