package ru.academit.narzikulov;

/**
 * Created by tim on 02.12.2015.
 */
public class Gauss extends Matrix {
    public Gauss(int rowsCount, int columnsCount) {
        super(rowsCount, columnsCount);
    }

    public Gauss(Matrix matrixToCopy) {
        super(matrixToCopy);
    }

    public Gauss(double[][] matrixArray) {
        super(matrixArray);
    }

    public Gauss(Vector[] vectorsArray) {
        super(vectorsArray);
    }

    private void swapVectors(int i, int j) {
        if (i != j) {
            throw new ArrayIndexOutOfBoundsException("Размерность векторов не совпадает. Их перестановка невозможна!");
        }
        Vector savedVector = this.matrixRows[i];
        this.matrixRows[i] = this.matrixRows[j];
        this.matrixRows[j] = savedVector;
    }

    public Gauss gauss() {
        if (this.matrixRows.length + 1 != this.matrixRows[0].getSize()) {
            throw new ArrayIndexOutOfBoundsException("Некорректное задание СЛАУ!");
        }
        double epsilon = 0.00001;

        Gauss matrix = new Gauss(this.matrixRows);

        int matrixSize = matrix.matrixRows.length;
        double matrixElement0;
        double matrixElement1;
        for (int j = 0; j < matrixSize; ++j) {
            for (int i = j; i < matrixSize - 1; ++i) {
                matrixElement0 = matrix.matrixRows[j].getVectorElement(j);
                matrixElement1 = matrix.matrixRows[i + 1].getVectorElement(j);

                if (Math.abs(matrixElement1) < epsilon) {
                    continue;
                }
                if (Math.abs(matrixElement0) < epsilon && i == j) {
                    matrix.matrixRows[i].reverseVector();
                    matrix.swapVectors(i, j + 1);
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

    public double det() {
        Matrix newMatrix = this.gauss();
        int matrixSize = newMatrix.matrixRows.length;
        double det = 1;
        for (int i = 0; i < matrixSize; ++i) {
            det *= newMatrix.matrixRows[i].getVectorElement(i);
        }
        return det;
    }

    public Vector linearSystem() {
        Matrix gaussMatrix = this.gauss();
        int variablesNum = gaussMatrix.matrixRows.length;
        Vector solution = new Vector(variablesNum);

        double curSolution = gaussMatrix.matrixRows[variablesNum - 1].getVectorElement(variablesNum) /
                gaussMatrix.matrixRows[variablesNum - 1].getVectorElement(variablesNum - 1);
        solution.setVectorElement(variablesNum - 1, curSolution);

        for (int i = variablesNum - 2 ; i > 0; --i) {
            for (int j = i ; j < variablesNum; ++j) {
                curSolution += gaussMatrix.matrixRows[i].getVectorElement(j) * solution.getVectorElement(i);
                System.out.println("curSolution = " + curSolution);

            }
            solution.setVectorElement(i, curSolution);
        }

        return solution;
    }


}
