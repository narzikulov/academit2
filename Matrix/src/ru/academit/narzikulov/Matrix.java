package ru.academit.narzikulov;

/**
 * Created by tim on 13.11.2015.
 */
public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int rowsCount, int columnsCount) {
        if (columnsCount <= 0 || rowsCount <= 0) {
            throw new IllegalArgumentException("Некорректное задание рамеров матрицы!");
        }
        this.matrixRows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; ++i) {
            this.matrixRows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrixToCopy) {
        Vector[] newMatrix = new Vector[matrixToCopy.matrixRows.length];
        int newMatrixSize = newMatrix.length;
        for (int i = 0; i < newMatrixSize; ++i) {
            newMatrix[i] = new Vector(matrixToCopy.matrixRows[i]);
        }
        this.matrixRows = newMatrix;
    }

    public Matrix(double[][] matrixArray) {
        int numOfMatrixRows = matrixArray.length;
        this.matrixRows = new Vector[numOfMatrixRows];
        for (int i = 0; i < numOfMatrixRows; ++i) {
            matrixRows[i] = new Vector(matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int matrixRowsLength = 0;
        int indexOfVectorWithMaxSize = 0;
        for (int i = 0; i < vectorsArray.length; ++i) {
            if (vectorsArray[i].getSize() > matrixRowsLength) {
                matrixRowsLength = vectorsArray[i].getSize();
                indexOfVectorWithMaxSize = i;
            }
        }

        this.matrixRows = new Vector[vectorsArray.length];
        for (int i = 0; i < vectorsArray.length; ++i) {
            this.matrixRows[i] = new Vector(vectorsArray[indexOfVectorWithMaxSize].getSize());
            this.matrixRows[i].addVector(vectorsArray[i]);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{ ");

        int matrixNumOfVectors = this.matrixRows.length;
        for (int i = 0; i < matrixNumOfVectors - 1; ++i) {
            s.append(matrixRows[i].toString());
            s.append(", ");
        }
        s.append(matrixRows[matrixNumOfVectors - 1]);
        s.append(" }");
        return s.toString();
    }

    public int getRowsCount() {
        return this.matrixRows.length;
    }

    public int getColumnsCount() {
        return this.matrixRows[0].getSize();
    }

    public Vector getRow(int indexOfRow) {
        if (indexOfRow < 0 || indexOfRow >= this.matrixRows.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы диапазона матрицы");
        }
        return new Vector(this.matrixRows[indexOfRow]);
    }

    public void setRowWithIndex(int indexOfRow, Vector insertingVector) {
        if (indexOfRow < 0 || indexOfRow >= this.matrixRows.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы диапазона матрицы");
        }
        if (insertingVector.getSize() > this.matrixRows[indexOfRow].getVectorLength()) {
            throw new IllegalArgumentException("Размер вектора больше размерности векторов матрицы");
        }

        if (insertingVector.getSize() < this.matrixRows[indexOfRow].getSize()) {
            insertingVector.extVectorsToEqualSize(this.matrixRows[indexOfRow]);
        }

        for (int i = 0; i < this.matrixRows[indexOfRow].getSize(); ++i) {
            this.matrixRows[indexOfRow].setVectorElement(i, insertingVector.getVectorElement(i));
        }
    }

    public Vector getColumn(int indexOfColumn) {
        if (indexOfColumn < 0 || indexOfColumn >= this.matrixRows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы диапазона матрицы");
        }

        int matrixColumnVectorLength = this.matrixRows.length;
        Vector matrixColumnVector = new Vector(matrixColumnVectorLength);
        for (int i = 0; i < matrixColumnVectorLength; ++i) {
            matrixColumnVector.setVectorElement(i, matrixRows[i].getVectorElement(indexOfColumn));
        }
        return matrixColumnVector;
    }

    public double getElement(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex > this.matrixRows.length) {
            throw new IndexOutOfBoundsException("Индекс строки выходит за границы диапазона матрицы");
        }
        if (columnIndex < 0 || columnIndex > this.matrixRows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс строки выходит за границы диапазона матрицы");
        }

        return this.matrixRows[rowIndex].getVectorElement(columnIndex);
    }

    public Matrix transpose() {
        Vector[] tMatrixVector = new Vector[this.matrixRows[0].getSize()];
        for (int i = 0; i < this.matrixRows[0].getSize(); ++i) {
            tMatrixVector[i] = new Vector(this.getColumn(i));
        }
        return new Matrix(tMatrixVector);
    }

    public Matrix multToNum(double num) {
        Vector[] newMatrixVector = new Vector[this.matrixRows.length];
        for (int i = 0; i < this.matrixRows.length; ++i) {
            newMatrixVector[i] = this.matrixRows[i];
            newMatrixVector[i].multVectorToNum(num);
        }
        return new Matrix(newMatrixVector);
    }

}
