package ru.academit.narzikulov;

/**
 * Created by tim on 08.12.2015.
 */
public class GaussMain {
    public static void main() {
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
