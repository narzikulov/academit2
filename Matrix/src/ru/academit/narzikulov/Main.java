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

        vector1.addVector(vector2);
        System.out.print("Vector1 = Vector1 + Vector2: ");
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
        if (vector3.isEqualTo(vector4)) {
            System.out.println("Vectors are equal");
        } else {
            System.out.println("Vectors are not equal");
        }

        System.out.println("Is Vector3 equal Vector3?");
        if (vector3.isEqualTo(vector3)) {
            System.out.println("Vectors are equal");
        } else {
            System.out.println("Vectors are not equal");
        }

        System.out.println();
        System.out.println(vector2.toString());
        System.out.println(vector4.toString());
        if (vector2.isEqualTo(vector4)) {
            System.out.println("Vectors are equal");
        } else {
            System.out.println("Vectors are not equal");
        }

        System.out.println();
        System.out.println("Vectors addition:");
        System.out.print("Vector2 = ");
        System.out.println(vector2.toString());

        System.out.print("Vector4 = ");
        System.out.println(vector4.toString());

        Vector vector5 = Vector.addTwoVectorsToNewOne(vector2, vector4);
        System.out.print("Vector5 = Vector2 + Vector4 = ");
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
        System.out.printf("Matrix4 vector with index %d : ", indexOfRow);
        System.out.println(matrix4.getRowWithIndex(indexOfRow).toString());

        indexOfRow = 0;
        matrix4.setRowWithIndex(indexOfRow, vector9);
        System.out.println(matrix4.toString());
    }
}
