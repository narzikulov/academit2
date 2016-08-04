package ru.academit.narzikulov;
/*
Задача 2. Вектор
Реализовать класс Vector для векторов вещественных чисел размерности n. Компоненты вектора хранить массивом. Номера компонент отсчитываются от нуля
Конструктор вектора должен принимать число n – размерность вектора. Если n <= 0, то нужно бросать исключение IllegalArgumentException
Для этого класса нужно реализовать:
1.	Конструкторы
a.	Vector(n) – размерность n, все компоненты равны 0
b.	Vector(Vector) – конструктор копирования
c.	Vector(double[]) – заполнение вектора значениями из массива
d.	Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
2.	Метод getSize() для получения размерности вектора
3.	Реализовать метод toString(), чтобы печатал вектор в  формате { значения компонент через запятую }
Например, { 1, 2, 3 }
4.	Реализовать  нестатические методы:
a.	Прибавление к вектору другого вектора
b.	Вычитание из вектора другого вектора
c.	Умножение вектора на скаляр
d.	Разворот вектора (умножение всех компонент на -1)
e.	Получение длины вектора
f.	Получение и установка компоненты вектора по индексу
g.	Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны с точностью до 0.0001. Соответственно, переопределить hashCode
5.	Реализовать статические методы – должны создаваться новые векторы:
a.	Сложение двух векторов
b.	Вычитание векторов
c.	Скалярное произведение векторов
Если операции выполняются над векторами разной размерности, то считать что у меньшего вектора в недостающих компонентах нули
В main написать небольшую тестовую программу
 */

/**
 * Created by tim on 08.12.2015.
 */
public class VectorMain {
    public static void main() {
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
    }
}
