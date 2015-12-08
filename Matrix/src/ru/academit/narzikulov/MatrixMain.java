package ru.academit.narzikulov;

/**
 * Created by tim on 08.12.2015.
 */
public class MatrixMain {
    public static void main() {
        System.out.println();
        System.out.println("--- Matrix class ---");
        int column = 2;
        int row = 5;
        System.out.printf("Matrix1 = new matrix %dx%d: ", column, row);
        Matrix matrix1 = new Matrix(column, row);
        System.out.println(matrix1.toString());

        Matrix matrix2 = new Matrix(matrix1);
        System.out.print("Matrix2 = copy of Matrix1 (constructor) = ");
        System.out.println(matrix2.toString());

        double[][] matrixArray1 = {{5, 4, 3, 2, 1}, {1, 2, 3, 4, 5, 6}};
        Matrix matrix3 = new Matrix(matrixArray1);
        System.out.print("Matrix3: ");
        System.out.println(matrix3.toString());

        double[] vectorArray1 = {5, 4, 3, 2, 1};
        Vector vector4 = new Vector(vectorArray1);
        System.out.print("Vector4: ");
        System.out.println(vector4.toString());
        double[] vectorArray9 = {3, 3};
        Vector vector9 = new Vector(vectorArray9);
        System.out.print("Vector9: ");
        System.out.println(vector9.toString());

        Vector[] vectorList1 = {vector4, vector9};
        Matrix matrix4 = new Matrix(vectorList1);
        System.out.print("Matrix4(vector4, vector9): ");
        System.out.println(matrix4.toString());

        System.out.println("Matrix4 size: " + matrix4.getRowsCount() + "x" + matrix4.getColumnsCount());

        int indexOfRow = 1;
        System.out.printf("Matrix4 row with index %d : ", indexOfRow);
        System.out.println(matrix4.getRow(indexOfRow).toString());

        vector9.subVector(vector4);

        indexOfRow = 0;
        System.out.printf("Set Matrix4[%d] as:", indexOfRow);
        System.out.println(vector9.toString());
        matrix4.setRow(indexOfRow, vector9);
        System.out.print("Matrix4: ");
        System.out.println(matrix4.toString());

        int indexOfColumn = 1;
        System.out.printf("Matrix4 column with index %d: ", indexOfColumn);
        System.out.println(matrix4.getColumn(indexOfColumn).toString());

        System.out.print("Matrix4 transpose: ");
        System.out.println(matrix4.transpose().toString());

        double num1 = -2;
        System.out.printf("Matrix4 mult to %.2f: ", num1);
        matrix4.multToNum(num1);
        System.out.println(matrix4.toString());

        indexOfRow = 0;
        indexOfColumn = 3;
        System.out.printf("Matrix4[%d, %d] element = ", indexOfRow, indexOfColumn);
        System.out.println(matrix4.getElement(indexOfRow, indexOfColumn));

        double[][] matrixArray7 = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix7 = new Matrix(matrixArray7);
        double[] vectorList12 = {7, 8, 9};
        Vector vector12 = new Vector(vectorList12);
        System.out.println("Matrix7:");
        matrix7.printAsMatrix();
        System.out.println("Vector12");
        System.out.print(vector12.toString());
        System.out.println();
        System.out.print("Matrix 7 x Vector12: ");
        System.out.println(matrix7.multToVector(vector12).toString());

        double[][] matrixArray8 = {{1, 2, 3, 4}, {2, 3, 4, 1}, {3, 4, 1, 2}, {4, 1, 2, 3}};
        Matrix matrix8 = new Matrix(matrixArray8);
        double[][] matrixArray9 = {{2, 1, 0, 0}, {0, 1, 3, 2}, {0, 0, 0, 5}, {-1, 2, 0, 0}};
        Matrix matrix9 = new Matrix(matrixArray9);

        System.out.println("Matrix8:");
        matrix8.printAsMatrix();
        System.out.println("Matrix9:");
        matrix9.printAsMatrix();

        System.out.println("Matrix10 = Matrix8 + Matrix9:");
        Matrix matrix10 = Matrix.addMatrixesToNeOne(matrix8, matrix9);
        matrix10.printAsMatrix();

        System.out.println("Matrix11 = Matrix9 - Matrix10:");
        Matrix matrix11 = Matrix.subMatrixesToNewOne(matrix9, matrix10);
        matrix11.printAsMatrix();

        System.out.println("Matrix8 = Matrix8 + Matrix9:");
        matrix8.addMatrix(matrix9);
        matrix8.printAsMatrix();

        System.out.println("Matrix8 = Matrix8 - Matrix9:");
        matrix8.subMatrix(matrix9);
        matrix8.printAsMatrix();

        // Matrixes multipication
        //double[][] matrixArray12 = {{1, 2}, {0, 1}};
        //double[][] matrixArray12 = {{1, 3, 2}, {0, 4, -1}};
        //double[][] matrixArray12 = {{-2, 1}, {5, 4}};
        double[][] matrixArray12 = {{3, -1}};
        Matrix matrix12 = new Matrix(matrixArray12);
        //double[][] matrixArray13 = {{2, 1}, {3, 2}};
        //double[][] matrixArray13 = {{2, 0, -1, 1}, {3, -2, 1, 2}, {0, 1, 2, 3}};
        //double[][] matrixArray13 = {{3}, {-1}};
        double[][] matrixArray13 = {{-2, 1}, {5, 4}};
        Matrix matrix13 = new Matrix(matrixArray13);

        System.out.println("Matrix12:");
        matrix12.printAsMatrix();
        System.out.println("Matrix13:");
        matrix13.printAsMatrix();

        System.out.println("Matrix14 = Matrix12 * Matrix13:");
        Matrix matrix14 = Matrix.multMatrixes(matrix12, matrix13);
        matrix14.printAsMatrix();

        //double[][] matrixArray15 = {{1, 2, 3, 4}, {2, 3, 4, 1}, {3, 4, 1, 2}, {4, 1, 2, 3}}; //det = 160
        //double[][] matrixArray15 = {{2, 1, 0, 0}, {0, 1, 3, 2}, {0, 0, 0, 5}, {-1, 2, 0, 0}}; // det = 75
        //double[][] matrixArray15 = {{2, 6, 5, 3, 2}, {-1, 3, 0, 1, -2}, {6, 5, 4, 0, 2}, {0, 1, 5, 3, -2}, {5, 3, 0, 1, 0}}; //det = -1500
        double[][] matrixArray15 = {{5, -1, 3}, {4, 9, 0}, {0, 4, 1}}; //det = 97
        //double[][] matrixArray15 = {{4, 2, -1}, {5, 3, -2}, {3, 2, -3}}; //det = -3
        //double[][] matrixArray15 = {{-2, 1, 3, 2}, {3, 0, -1, 2}, {-5, 2, 3, 0}, {4, -1, 2, -3}}; // det = -80
        //double[][] matrixArray15 = {{9, 8, 7, 6}, {5, 4, 3, 2}, {1, 0, 1, 2}, {3, 4, 5, 6}}; // det = 0
        //double[][] matrixArray15 = {{11, -2}, {7, 5}}; //det = 69
        Matrix matrix15 = new Matrix(matrixArray15);
        System.out.println("Matrix15:");
        matrix15.printAsMatrix();
        System.out.println("Определитель Matrix15 = " + matrix15.determinant());
    }
}
