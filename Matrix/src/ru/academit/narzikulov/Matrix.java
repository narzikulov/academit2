package ru.academit.narzikulov;

/**
 * Created by tim on 13.11.2015.
 */
public class Matrix {
    private Vector[] matrixElements;

    public Matrix(int rowNumber, int columnNumber) {
        if (rowNumber <= 0 || columnNumber <= 0) {
            throw new IllegalArgumentException("Некорректное задание рамеров матрицы!");
        }
        this.matrixElements = new Vector[columnNumber];
        for (int i = 0; i < columnNumber; ++i) {
            this.matrixElements[i] = new Vector(rowNumber);
        }
    }

    public Matrix(Matrix matrixToCopy) {
        Vector[] newMatrix = new Vector[matrixToCopy.matrixElements.length];
        int newMatrixSize = newMatrix.length;
        for (int i = 0; i < newMatrixSize; ++i) {
            newMatrix[i] = new Vector(matrixToCopy.matrixElements[i]);
        }
        this.matrixElements = newMatrix;
    }

    public Matrix(double[][] matrixArray) {
        int numOfMatrixRows = matrixArray.length;
        this.matrixElements = new Vector[numOfMatrixRows];
        for (int i = 0; i < numOfMatrixRows; ++i) {
            matrixElements[i] = new Vector(matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        this.matrixElements = new Vector[vectorsArray.length];
        for (int i = 0; i < vectorsArray.length; ++i) {
            this.matrixElements[i] = new Vector(vectorsArray[i]);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{ ");

        int matrixNumOfVectors = this.matrixElements.length;
        for (int i = 0; i < matrixNumOfVectors - 1; ++i) {
            s.append(matrixElements[i].toString());
            s.append(", ");
        }
        s.append(matrixElements[matrixNumOfVectors - 1]);
        s.append(" }");
        return s.toString();
    }

}
