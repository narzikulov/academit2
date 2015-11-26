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

    public double maxVectorLength() {
        double maxLength = 0;
        for (Vector matrixRow : this.matrixRows) {
            double curVectorLength = matrixRow.getVectorLength();
            if (curVectorLength > maxLength) maxLength = curVectorLength;
        }
        return maxLength;
    }

    public void changeVectors(int i, int j) {
        Vector savedVector = new Vector(this.matrixRows.length);
        savedVector = this.matrixRows[i];
        this.matrixRows[i] = this.matrixRows[j];
        this.matrixRows[j] = savedVector;
/*      int vectorLength = this.matrixRows[0].getSize();
        for (int k = 0; k < vectorLength; ++k) {
            double curVectorElement = this.matrixRows[i].getVectorElement(k);
            System.out.println("curVectorElement" + curVectorElement);
            this.matrixRows[i].setVectorElement(k, this.matrixRows[j].getVectorElement(k));
            System.out.println("matrixRows[i] element = " + this.matrixRows[j].getVectorElement(k));
            this.matrixRows[j].setVectorElement(k, curVectorElement);
            System.out.println("matrix[j] element = " + this.matrixRows[j].getVectorElement(k));
        }
*/
    }

    public Matrix gauss() {
        if (this.matrixRows.length != this.matrixRows[0].getSize()) {
            throw new ArrayIndexOutOfBoundsException("Определитель можно вычислить только у квадратных матриц!");
        }
        double epsilon = 0.00001;

        Matrix matrix = new Matrix(this.matrixRows);

        int matrixSize = matrix.matrixRows.length;
        double matrixElement0;
        double matrixElement1;
        for (int j = 0; j < matrixSize - 1; ++j) {
            for (int i = j; i < matrixSize - 1; ++i) {
                matrixElement0 = matrix.matrixRows[j].getVectorElement(j);
                matrixElement1 = matrix.matrixRows[i + 1].getVectorElement(j);

                if (Math.abs(matrixElement1) < epsilon) {
                    continue;
                }
                if (Math.abs(matrixElement0) < epsilon && i == j) {
                    matrix.matrixRows[i].reverseVector();
                    matrix.changeVectors(i, j + 1);
                    continue;
                }

                if (Math.abs(matrixElement0 - matrixElement1) > epsilon) {
                    matrix.matrixRows[i + 1].multVectorToNum(matrixElement0);
                    matrix.matrixRows[j].multVectorToNum(matrixElement1);
                }

                matrix.matrixRows[i + 1].subVector(matrix.matrixRows[j]);

                if (Math.abs(matrixElement0 - matrixElement1) > epsilon) {
                    matrix.matrixRows[i + 1].multVectorToNum(1 / matrixElement0);
                    matrix.matrixRows[j].multVectorToNum(1 / matrixElement1);
                }
            }
        }
        return matrix;
    }

    public static double det(Matrix matrix) {
        Matrix newMatrix = matrix.gauss();
        int matrixSize = newMatrix.matrixRows.length;
        double det = 1;
        for (int i = 0; i < matrixSize; ++i) {
            det *= newMatrix.matrixRows[i].getVectorElement(i);
        }
        return det;
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
        this.multToNum(-1);
        this.addMatrix(addMatrix);
        this.multToNum(-1);
    }

}
