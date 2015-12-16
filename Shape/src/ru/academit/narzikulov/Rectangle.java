package ru.academit.narzikulov;

/**
 * Created by tim on 21.10.2015.
 */
public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getArea() {
        return width * height;
    }
}
