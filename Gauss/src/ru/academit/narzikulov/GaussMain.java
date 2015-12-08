package ru.academit.narzikulov;

/**
 * Created by tim on 08.12.2015.
 */
public class GaussMain {
    public static void main() {
        System.out.println();
        System.out.println("Решение СЛАУ методом Гаусса:");
        //x = 2, y = 3, z = -1
        //double[][] matrixArray16 = {{2, 1, -1}, {-3, -1, 2}, {-2, 1, 2}};
        //double[] vectorArray16 = {8, -11, -3};

        //x = -1.4, y = -2, z = -4.2
        //double[][] matrixArray16 = {{2, -2, 1}, {1, 3, -2}, {3, -1, -1}};
        //double[] vectorArray16 = {-3, 1, 2};

        //x = 1, y = 2, z = 3
        //double[][] matrixArray16 = {{2, -1, 0}, {-1, 1, 4}, {1, 2, 3}};
        //double[] vectorArray16 = {0, 13, 14};

        //x = 9, y = 18, z = 10, p = -16
        //double[][] matrixArray16 = {{1, -1, 3, 1}, {4, -1, 5, 4}, {2, -2, 4, 1}, {1, -4, 5, -1}};
        //double[] vectorArray16 = {5, 4, 6, 3};

        //система не имеет решений
        //double[][] matrixArray16 = {{4, -3, 2, -1}, {3, -2, 1, -3}, {5, -3, 1, -8}, {0, 0, 0, 5}};
        //double[] vectorArray16 = {8, 7, 1, 3};

        //система имеет
        double[][] matrixArray16 = {{2, 3, -1, 1}, {8, 12, -9, 8}, {4, 6, 3, -2}, {2, 3, 9, -7}};
        double[] vectorArray16 = {1, 3, 3, 3};


        Matrix matrix16 = new Matrix (matrixArray16);
        Vector vector16 = new Vector (vectorArray16);
        System.out.println("Коэффициенты уравнений СЛАУ:");
        matrix16.printAsMatrix();
        System.out.println("Вектор: ");
        System.out.println(vector16.toString());
        System.out.println("Приведение СЛАУ к треугольному виду:");
        Gauss.gauss(matrix16, vector16).printAsMatrix();
        System.out.println("Решение СЛАУ:");
        System.out.println(Gauss.linearSystem(matrix16, vector16).toString());
    }


}
