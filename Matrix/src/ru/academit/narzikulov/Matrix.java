package ru.academit.narzikulov;

/**
 * Created by tim on 13.11.2015.
 */
public class Matrix {
    private Vector[] matrix;

    public Matrix(int n, int m) throws NullPointerException {
        try {
            this.matrix = new Vector[m];
            for (int i = 0; i < m; ++i) {
                this.matrix[i] = new Vector(n);
            }
        } catch (NullPointerException e) {
            System.out.println("Обнаружено деление на ноль!");
        }
    }

    public Matrix(Matrix matrixToCopy) {
        Vector[] newMatrix = new Vector[matrixToCopy.matrix.length];
        int newMatrixSize = newMatrix.length;
        System.arraycopy(matrixToCopy.matrix, 0, newMatrix, 0, newMatrixSize);
        this.matrix = newMatrix;
    }

    public Matrix(double[][] matrixArray) {
        int numOfMatrixRows = matrixArray.length;
        this.matrix = new Vector[numOfMatrixRows];
        for (int i = 0; i < numOfMatrixRows; ++i) {
            matrix[i] = new Vector(matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        this.matrix = new Vector[vectorsArray.length];
        this.matrix = vectorsArray;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{ ");

        int matrixNumOfVectors = this.matrix.length;
        for (int i = 0; i < matrixNumOfVectors - 1; ++i) {
            s.append(matrix[i].toString());
            s.append(", ");
        }
        s.append(matrix[matrixNumOfVectors - 1]);
        s.append(" }");
        return s.toString();
    }

}
