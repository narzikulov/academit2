package ru.academit.narzikulov;

/**
 * Created by tim on 21.10.2015.
 */
public class ShapeMain {
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
    }
}
