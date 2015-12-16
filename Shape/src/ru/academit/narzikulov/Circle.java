package ru.academit.narzikulov;

/**
 * Created by tim on 21.10.2015.
 */
public class Circle implements Shape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return this.radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}
