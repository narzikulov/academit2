/*
Задача 1. Фигуры
Создать иерархию классов для геометрических фигур.
Для них нужно создать интерфейс Shape, в котором определены методы:
double getWidth()
double getHeight()
double getArea()
Все фигуры должны реализовывать этот интерфейс и правильным образом определять данные методы
1.	Square – квадрат
Должен иметь конструктор, принимающий длину стороны
2.	Triangle – треугольник
Должен иметь конструктор, принимающий x1, y1, x2, y2, x3, y3 – шесть координат.
В качестве ширины возвращать max(x1, x2, x3) – min(x1, x2, x3)
В качестве высоты возвращать max(y1, y2, y3) – min(y1, y2, y3)
3.	Rectangle – прямоугольник
Должен иметь конструктор, принимающий длины двух сторон
4.	Circle – окружность
Должна иметь конструктор, принимающий радиус. В качестве ширины и высоты должен выдаваться диаметр
В main написать небольшую тестовую программу.
 */
package ru.academit.narzikulov;
import java.util.ArrayList;

/**
 * Created by tim on 21.10.2015.
 */
public class ShapeMain {

    public static int findIndexWithMaxWidth(ArrayList<Shape> shapesList) {
        double maxWidth = 0;
        int indexWithMaxWidth = -1;
        for (int i = 0; i < shapesList.size(); ++i) {
            if (shapesList.get(i).getWidth() > maxWidth) {
                maxWidth = shapesList.get(i).getWidth();
                indexWithMaxWidth = i;
            }
        }
        return indexWithMaxWidth;
    }

    public static double countTotalArea(ArrayList<Shape> shapesList) {
        double area = 0;
        for (Shape aShapesList : shapesList) {
            area += aShapesList.getArea();
        }
        return area;
    }

    public static void main() {
        Square square = new Square(5);
        System.out.printf("Square area = %.2f%n", square.getArea());
        System.out.printf("Squar width = %.2f%n", square.getWidth());
        System.out.printf("Squar height = %.2f%n", square.getHeight());
        System.out.println();

        Circle circle = new Circle(6);
        System.out.printf("Circle area = %.2f%n", circle.getArea());
        System.out.printf("Circle radius = %.2f%n", circle.getWidth());
        System.out.printf("Circle radius = %.2f%n", circle.getHeight());
        System.out.println();

        Rectangle rectangle = new Rectangle(3, 4);
        System.out.printf("Rectangle area = %.2f%n", rectangle.getArea());
        System.out.printf("Rectangle width = %.2f%n", rectangle.getWidth());
        System.out.printf("Rectangle height = %.2f%n", rectangle.getHeight());
        System.out.println();

        Triangle triangle = new Triangle(0, 0, 4, 3, 5, 6);
        System.out.printf("Triangle area = %.2f%n", triangle.getArea());
        System.out.printf("Triangle width = %.2f%n", triangle.getWidth());
        System.out.printf("Triangle height = %.2f%n", triangle.getHeight());
        System.out.println();

        ArrayList<Shape> listOfShapes = new ArrayList<Shape>();
        listOfShapes.add(square);
        listOfShapes.add(circle);
        listOfShapes.add(rectangle);
        listOfShapes.add(triangle);
        int indexWithMaxWidth = findIndexWithMaxWidth(listOfShapes);
        if (indexWithMaxWidth != -1) {
            System.out.print("Shape with maximum width is: ");
            System.out.print(listOfShapes.get(indexWithMaxWidth).getClass().getSimpleName());
            System.out.print(" with width = ");
            System.out.println(listOfShapes.get(indexWithMaxWidth).getWidth());

            System.out.println("Total area of all shapes in list = " + countTotalArea(listOfShapes));
        }
    }
}
