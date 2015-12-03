package ru.academit.narzikulov;

/**
 * Created by tim on 13.11.2015.
 */
public class Matrix {
    public Vector[] matrixRows;

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
            this.matrixRows[i] = new Vector(matrixArray[i]);
        }
    }

    private int vectorsMaxSize(Vector[] vectorsArray) {
        int maxSize = 0;
        for (Vector aVectorsArray : vectorsArray) {
            if (aVectorsArray.getSize() > maxSize) {
                maxSize = aVectorsArray.getSize();
            }
        }
        return maxSize;
    }

    public Matrix(Vector[] vectorsArray) {
        this.matrixRows = new Vector[vectorsArray.length];
        int vectorsMaxSize = vectorsMaxSize(vectorsArray);
        for (int i = 0; i < vectorsArray.length; ++i) {
            this.matrixRows[i] = new Vector(vectorsMaxSize);
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

    public void printAsMatrix() {
        for (Vector matrixRow : this.matrixRows) {
            System.out.println(matrixRow.toString());
        }
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

    public void setRow(int index, Vector insertingVector) {
        if (index < 0 || index >= this.matrixRows.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы диапазона матрицы");
        }
        if (insertingVector.getSize() > this.matrixRows[index].getVectorLength()) {
            throw new IllegalArgumentException("Размер вектора больше размерности векторов матрицы");
        }

        if (insertingVector.getSize() < this.matrixRows[index].getSize()) {
            double[] newVectorArray = insertingVector.extVectorsToEqualSize(this.matrixRows[index]);
            int newVectorArrayLength = newVectorArray.length;
            for (int i = 0; i < newVectorArrayLength; ++i) {
                newVectorArray[i] = insertingVector.getVectorElement(i);
            }
            this.matrixRows[index] = new Vector(newVectorArray);
            return;
        }

        for (int i = 0; i < this.matrixRows[index].getSize(); ++i) {
            this.matrixRows[index].setVectorElement(i, insertingVector.getVectorElement(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.matrixRows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы диапазона матрицы");
        }

        int matrixColumnVectorLength = this.matrixRows.length;
        Vector matrixColumnVector = new Vector(matrixColumnVectorLength);
        for (int i = 0; i < matrixColumnVectorLength; ++i) {
            matrixColumnVector.setVectorElement(i, matrixRows[i].getVectorElement(index));
        }
        return matrixColumnVector;
    }

    public double getElement(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex > this.matrixRows.length) {
            throw new IndexOutOfBoundsException("Индекс строки выходит за границы диапазона матрицы");
        }
        if (columnIndex < 0 || columnIndex > this.matrixRows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс столбца выходит за границы диапазона матрицы");
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

    public void multToNum(double num) {
        for (Vector matrixRow : this.matrixRows) {
            matrixRow.multVectorToNum(num);
        }
    }

    public double maxVectorLength() {
        double maxLength = 0;
        for (Vector matrixRow : this.matrixRows) {
            double curVectorLength = matrixRow.getVectorLength();
            if (curVectorLength > maxLength) maxLength = curVectorLength;
        }
        return maxLength;
    }

    public void changeVectors(int i, int j) {
        Vector savedVector = this.matrixRows[i];
        this.matrixRows[i] = this.matrixRows[j];
        this.matrixRows[j] = savedVector;
    }

    public Vector multToVector(Vector vector) {
        int newVectorSize = this.matrixRows.length;
        Vector newVector = new Vector(newVectorSize);
        for (int j = 0; j < newVectorSize; ++j) {

            int matrixColumns = this.matrixRows[j].getSize();
            if (matrixColumns != vector.getSize()) {
                throw new ArrayIndexOutOfBoundsException("Размерность матрицы и вектора не подходят для перемножения");
            }

            double newVectorElement = 0;
            for (int i = 0; i < matrixColumns; ++i) {
                newVectorElement += this.matrixRows[j].getVectorElement(i) * vector.getVectorElement(i);
            }
            newVector.setVectorElement(j, newVectorElement);
        }
        return newVector;
    }

    public void addMatrix(Matrix addMatrix) {
        if (this.matrixRows.length != addMatrix.matrixRows.length) {
            throw new ArrayIndexOutOfBoundsException("Размерности матриц не идентичные. Сложение невозможно!");
        }

        for (int i = 0; i < this.matrixRows.length; ++i) {
            if (this.matrixRows[i].getSize() != addMatrix.matrixRows[i].getSize()) {
                throw new ArrayIndexOutOfBoundsException("Размерности матриц не идентичные. Сложение невозможно!");
            }

            this.matrixRows[i].addVector(addMatrix.matrixRows[i]);
        }
    }

    public void subMatrix(Matrix addMatrix) {
        Matrix copyOfAddMatrix = new Matrix(addMatrix);
        copyOfAddMatrix.multToNum(-1);
        this.addMatrix(copyOfAddMatrix);
    }

    public static Matrix addMatrixToNeOne(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix1 = new Matrix(matrix1);
        newMatrix1.addMatrix(matrix2);
        return newMatrix1;
    }

    public static Matrix subMatrixToNeOne(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix1 = new Matrix(matrix1);
        newMatrix1.subMatrix(matrix2);
        return newMatrix1;
    }

    public static Matrix multMatrixes(Matrix matrix1, Matrix matrix2) {
        int newMatrixRowsNum = matrix1.matrixRows.length;
        int newMatrixColumnsNum = matrix2.vectorsMaxSize(matrix2.matrixRows);
        Matrix newMatrix = new Matrix(newMatrixRowsNum, newMatrixColumnsNum);

        for (int i = 0; i < newMatrixRowsNum; ++i) {
            double matrixElement = 0;
            if (matrix1.matrixRows[i].getSize() != matrix2.matrixRows.length) {
                throw new ArrayIndexOutOfBoundsException("Размерности матриц не идентичные. Перемножение невозможно!");
            }

            for (int j = 0; j < newMatrixColumnsNum; ++j) {
                matrixElement = Vector.multVectors(matrix1.matrixRows[i], matrix2.getColumn(j));
                newMatrix.matrixRows[i].setVectorElement(j, matrixElement);
            }
        }

        return newMatrix;
    }

}
