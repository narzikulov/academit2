package ru.academit.narzikulov.serializator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by User on 03.07.2016.
 */
public class SymmetricMatrix implements Serializable {
    public static final long serialVersionUID = 1L;
    private int[] vector;
    private transient int[][] matrix;
    private transient static final int MAX_VALUE_FOR_MATRIX_ELEMENT = 1000;

    public SymmetricMatrix(int dim) {
        fill(dim);
    }

    private void fill(int dim) {
        matrix = new int[dim][dim];
        Random randomNumber = new Random();
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (j < i) {
                    matrix[i][j] = matrix[j][i];
                } else {
                    matrix[i][j] = randomNumber.nextInt(MAX_VALUE_FOR_MATRIX_ELEMENT);
                }
            }
        }
    }

    public void print() {
        int dim = matrix.length;
        for (int[] aMatrix : matrix) {
            for (int j = 0; j < dim; ++j) {
                System.out.printf("%5d", aMatrix[j]);
            }
            System.out.println();
        }
    }

    private void matrixToVector() {
        int dim = matrix.length;
        int vectorDim = dim;
        for (int i = 0; i < dim; ++i) {
            vectorDim += i;
        }
        ++vectorDim;

        vector = new int[vectorDim];
        vector[0] = dim;
        int vectorIterator = 1;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (j >= i) {
                    vector[vectorIterator] = matrix[i][j];
                    ++vectorIterator;
                }
            }
        }
    }

    public void printVector() {
        System.out.println("Vector");
        for (int aVector : vector) {
            System.out.printf("%5d", aVector);
        }
        System.out.println();
    }

    private void vectorToMatrix() {
        int dim = vector[0];
        int vectorIterator = 1;
        matrix = new int[dim][dim];
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (j >= i) {
                    matrix[i][j] = vector[vectorIterator];
                    ++vectorIterator;
                } else {
                    matrix[i][j] = matrix[j][i];
                }
            }
        }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        matrixToVector();
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        vectorToMatrix();
    }

}
