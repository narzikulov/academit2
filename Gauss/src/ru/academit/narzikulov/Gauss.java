package ru.academit.narzikulov;

/**
 * Created by tim on 02.12.2015.
 */
public class Gauss {
    private Matrix matrix;
    private Vector vector;

    public Gauss(Matrix matrix, Vector vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    public Gauss(double[][] matrixArray, double[] vectorArray) {
        this.matrix = new Matrix(matrixArray);
        this.vector = new Vector(vectorArray);
    }



    private Matrix extendMatrix() {
        if (this.matrix.matrixRows.length != this.vector.getSize() ||
                this.matrix.matrixRows[0].getSize() != this.vector.getSize()) {
            throw new ArrayIndexOutOfBoundsException("Невозможно расширить матрицу из несовпадения размеров матрицы и вектора!");
        }

        int extendMatrixRowsNum = this.matrix.matrixRows.length;
        int extendMatrixColumnsNum = this.matrix.matrixRows.length + 1;
        Matrix extendedMatrix = new Matrix(extendMatrixRowsNum, extendMatrixColumnsNum);
        for (int i = 0; i < extendMatrixRowsNum; ++i) {
            for (int j = 0; j < extendMatrixColumnsNum - 1; ++j) {
                extendedMatrix.matrixRows[i].setVectorElement(j, this.matrix.matrixRows[i].getVectorElement(j));
            }
            extendedMatrix.matrixRows[i].setVectorElement(extendMatrixColumnsNum - 1, vector.getVectorElement(i));
        }
        return extendedMatrix;
    }

    private double getVectorLengthWithoutLastElement(Vector vector) {
        double vectorLength = 0;
        for (int i = 0; i < vector.getSize() - 1; i++) {
            double aVectorArray = vector.getVectorElement(i);
            vectorLength += aVectorArray * aVectorArray;
        }
        return Math.sqrt(vectorLength);
    }

    private Matrix gauss() {
        Matrix extMatrix = extendMatrix();

        int matrixSize = extMatrix.matrixRows.length;
        for (int j = 0; j < matrixSize; ++j) {
            for (int i = j; i < matrixSize - 1; ++i) {
                double matrixElement0 = extMatrix.matrixRows[j].getVectorElement(j);
                double matrixElement1 = extMatrix.matrixRows[i + 1].getVectorElement(j);

                if (DoubleNumsCompare.CompareNumWithZero(matrixElement1)) {
                    continue;
                }
                if (DoubleNumsCompare.CompareNumWithZero(matrixElement0) && i == j) {
                    extMatrix.matrixRows[i].reverseVector();
                    extMatrix.swapVectors(i, j + 1);
                    continue;
                }

                if (!DoubleNumsCompare.AreDoubleNumsEqual(matrixElement0, matrixElement1)) {
                    extMatrix.matrixRows[i + 1].multVectorToNum(matrixElement0);
                    extMatrix.matrixRows[j].multVectorToNum(matrixElement1);
                }

                extMatrix.matrixRows[i + 1].subVector(extMatrix.matrixRows[j]);

                if (!DoubleNumsCompare.AreDoubleNumsEqual(matrixElement0, matrixElement1)) {
                    extMatrix.matrixRows[i + 1].multVectorToNum(1 / matrixElement0);
                    extMatrix.matrixRows[j].multVectorToNum(1 / matrixElement1);
                }
            }
            if (DoubleNumsCompare.CompareNumWithZero(getVectorLengthWithoutLastElement(extMatrix.matrixRows[j])) &&
                    !DoubleNumsCompare.CompareNumWithZero(extMatrix.matrixRows[j].getVectorElement(matrixSize))) {
                return null;
            }
        }
        return extMatrix;
    }

    private double detGauss(Matrix extMatrix) {
        int matrixSize = extMatrix.matrixRows.length;
        double det = 1;
        for (int i = 0; i < matrixSize; ++i) {
            det *= extMatrix.matrixRows[i].getVectorElement(i);
        }
        return det;
    }

    public GaussResult linearSystemSolve() {
        Matrix gaussMatrix = gauss();

        if (gaussMatrix == null) {
            return new GaussResult(GaussLinearSystemSolveId.NOSOLVES);
        }

        if (DoubleNumsCompare.CompareNumWithZero(detGauss(gaussMatrix))) {
            return new GaussResult(GaussLinearSystemSolveId.MANYSOLVES);
        }

        int variablesNum = gaussMatrix.matrixRows.length;
        Vector solution = new Vector(variablesNum);

        double curSolution = gaussMatrix.matrixRows[variablesNum - 1].getVectorElement(variablesNum) /
                gaussMatrix.matrixRows[variablesNum - 1].getVectorElement(variablesNum - 1);
        solution.setVectorElement(variablesNum - 1, curSolution);

        for (int i = variablesNum - 2; i >= 0; --i) {
            curSolution = gaussMatrix.matrixRows[i].getVectorElement(variablesNum);
            for (int j = variablesNum - 1; j >= 0; --j) {
                curSolution -= gaussMatrix.matrixRows[i].getVectorElement(j) * solution.getVectorElement(j);
            }
            solution.setVectorElement(i, curSolution / gaussMatrix.matrixRows[i].getVectorElement(i));
        }

        return new GaussResult(solution, GaussLinearSystemSolveId.ONESOLVE);
    }
}
