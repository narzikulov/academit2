package ru.academit.narzikulov;

/**
 * Created by tim on 21.10.2015.
 */
public class Square implements Shape {
    private double width;

    public Square(double width)
    {
        this.width = width;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.width;
    }

    public double getArea() {
        return this.width * this.width;
    }
}
