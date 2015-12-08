package ru.academit.narzikulov;

/**
 * Created by tim on 20.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        double[] vectorArray1 = {5, 4, 3, 2, 1};
        Vector vector1 = new Vector(vectorArray1);
        System.out.print("Vector1: ");
        System.out.println(vector1.toString());

        double[] vectorArray2 = {1, 2, 3, 4, 5, 6};
        Vector vector2 = new Vector(vectorArray2);
        System.out.print("Vector2: ");
        System.out.println(vector2.toString());

        System.out.print("Vector1 = Vector1 + Vector2: ");
        vector1.addVector(vector2);
        System.out.println(vector1.toString());

        double[] vectorArray11 = {7, 6, 5, 4, 3, 2, 1};
        Vector vector11 = new Vector(vectorArray11);
        System.out.print("Vector11: ");
        System.out.println(vector11.toString());

        vector2.subVector(vector11);
        System.out.print("Vector2 = Vector2 - Vector11: ");
        System.out.println(vector2.toString());

        double num = 2.0;
        vector2.multVectorToNum(num);
        System.out.printf("Vector2 * %.2f = ", num);
        System.out.println(vector2.toString());

        vector2.reverseVector();
        System.out.print("Vector2 * (-1) = ");
        System.out.println(vector2.toString());

        System.out.print("Vector2 length = ");
        System.out.println(vector2.getVectorLength());

        int index = 3;
        System.out.printf("get Vector2 %dd Number = ", index);
        System.out.println(vector2.getVectorElement(3));

        index = 2;
        num = 150;
        vector2.setVectorElement(index, num);
        System.out.printf("set Vector2 %dd Number = %.2f: ", index, num);
        System.out.println(vector2.toString());


        System.out.println();
        System.out.println("Vectors comparing");
        Vector vector3 = new Vector(vectorArray1);
        Vector vector4 = new Vector(vectorArray1);
        System.out.print("Vector3 = ");
        System.out.println(vector3.toString());
        System.out.print("Vector4 = ");
        System.out.println(vector4.toString());
        if (vector3.equals(vector4)) {
            System.out.println("Vector3 are equal Vector4");
        } else {
            System.out.println("Vectors are not equal");
        }
        System.out.println("Vector3 hash code = " + vector3.hashCode());
        System.out.println("Vector4 hash code = " + vector4.hashCode());

        System.out.println("Is Vector3 equal Vector3?");
        if (vector3.equals(vector3)) {
            System.out.println("Vectors are equal");
        } else {
            System.out.println("Vectors are not equal");
        }

        System.out.println();
        System.out.println(vector2.toString());
        System.out.println(vector4.toString());
        if (vector2.equals(vector4)) {
            System.out.println("Vectors are equal");
        } else {
            System.out.println("Vectors are not equal");
        }
        System.out.println("Vector2 hash code = " + vector2.hashCode());
        System.out.println("Vector4 hash code = " + vector4.hashCode());

        System.out.println();
        System.out.println("Vectors addition:");
        System.out.print("Vector2 = ");
        System.out.println(vector2.toString());

        System.out.print("Vector4 = ");
        System.out.println(vector4.toString());

        System.out.print("Vector5 = Vector2 + Vector4 = ");
        Vector vector5 = Vector.addTwoVectorsToNewOne(vector2, vector4);
        System.out.println(vector5.toString());

        Vector vector6 = Vector.subVectorsToNewOne(vector2, vector4);
        System.out.print("Vector6 = Vector2 - Vector4 = ");
        System.out.println(vector6.toString());

        System.out.print("Vector7 = Vector2 * Vector4 = ");
        System.out.println(Vector.multVectors(vector2, vector4));

        Vector vector7 = Vector.copyVector(vector6);
        System.out.print("Vector7 = copy of Vector6 = ");
        System.out.println(vector7.toString());

        Vector vector8 = new Vector(vector7);
        System.out.print("Vector8 = copy of Vector7 (constructor) = ");
        System.out.println(vector8.toString());

        //Matrix
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
        matrix15.printAsMatrix();
        System.out.println("Определитель = " + matrix15.determinant());

        System.out.println();
        System.out.println("Метода Гаусса:");
        //double[][] matrixArray16 = {{2, 1, -1, 8}, {-3, -1, 2, -11}, {-2, 1, 2, -3}}; //x = 2, y = 3, z = -1
        //double[][] matrixArray16 = {{2, -2, 1, -3}, {1, 3, -2, 1}, {3, -1, -1, 2}}; //x = -1.4, y = -2, z = -4.2
        //double[][] matrixArray16 = {{2, -1, 0, 0}, {-1, 1, 4, 13}, {1, 2, 3, 14}}; //x = 1, y = 2, z = 3
        double[][] matrixArray16 = {{1, -1, 3, 1, 5}, {4, -1, 5, 4, 4}, {2, -2, 4, 1, 6}, {1, -4, 5, -1, 3}}; //x = 9, y = 18, z = 10, p = -16
        Gauss matrix16 = new Gauss(matrixArray16);
        System.out.println("СЛАУ:");
        matrix16.printAsMatrix();
        System.out.println("Приведение расширенной матрицу к треугольному виду");
        matrix16.gauss().printAsMatrix();
        System.out.println("Решение СЛАУ:");
        System.out.println(matrix16.linearSystem().toString());
    }
}
