package ru.academit.narzikulov;

/**
 * Created by tim on 02.12.2015.
 */
public class Gauss {
    public static boolean solve = true;

    public static Matrix extendMatrix(Matrix matrix, Vector vector) {
        if (matrix.matrixRows.length != vector.getSize() || matrix.matrixRows[0].getSize() != vector.getSize()) {
            throw new ArrayIndexOutOfBoundsException("Невозможно расширить матрицу из несовпадения размеров матрицы и вектора!");
        }

        int extendMatrixRowsNum = matrix.matrixRows.length;
        int extendMatrixColumnsNum = matrix.matrixRows.length + 1;
        Matrix extendedMatrix = new Matrix(extendMatrixRowsNum, extendMatrixColumnsNum);
        for (int i = 0; i < extendMatrixRowsNum; ++i) {
            for (int j = 0; j < extendMatrixColumnsNum - 1; ++j) {
                extendedMatrix.matrixRows[i].setVectorElement(j, matrix.matrixRows[i].getVectorElement(j));
            }
            extendedMatrix.matrixRows[i].setVectorElement(extendMatrixColumnsNum - 1, vector.getVectorElement(i));
        }
        return extendedMatrix;
    }

    public static Matrix gauss(Matrix matrix, Vector vector) {
        double epsilon = 0.00001;
        Matrix extMatrix = extendMatrix(matrix, vector);

        int matrixSize = extMatrix.matrixRows.length;
        double matrixElement0;
        double matrixElement1;
        for (int j = 0; j < matrixSize; ++j) {
            for (int i = j; i < matrixSize - 1; ++i) {
                matrixElement0 = extMatrix.matrixRows[j].getVectorElement(j);
                matrixElement1 = extMatrix.matrixRows[i + 1].getVectorElement(j);

                if (Math.abs(matrixElement1) < epsilon) {
                    continue;
                }
                if (Math.abs(matrixElement0) < epsilon && i == j) {
                    extMatrix.matrixRows[i].reverseVector();
                    extMatrix.swapVectors(i, j + 1);
                    continue;
                }

                if (Math.abs(matrixElement0 - matrixElement1) > epsilon) {
                    extMatrix.matrixRows[i + 1].multVectorToNum(matrixElement0);
                    extMatrix.matrixRows[j].multVectorToNum(matrixElement1);
                }

                extMatrix.matrixRows[i + 1].subVector(extMatrix.matrixRows[j]);

                if (Math.abs(matrixElement0 - matrixElement1) > epsilon) {
                    extMatrix.matrixRows[i + 1].multVectorToNum(1 / matrixElement0);
                    extMatrix.matrixRows[j].multVectorToNum(1 / matrixElement1);
                }
            }
            if (extMatrix.matrixRows[j].getVectorLengthWithoutLastElement() < epsilon &&
                    Math.abs(extMatrix.matrixRows[j].getVectorElement(matrixSize)) > epsilon) {
                solve = false;
            }
        }
        return extMatrix;
    }

    public static Vector linearSystem(Matrix matrix, Vector vector) {
        if (!solve) {
            System.out.println("Система не имеет решений");
            return new Vector(vector.getSize());
        }
        Matrix gaussMatrix = gauss(matrix, vector);
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

        return solution;
    }


}
