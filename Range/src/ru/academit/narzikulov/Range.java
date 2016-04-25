package ru.academit.narzikulov;

/**
 * Created by tim on 25.04.2016.
 */
public class Range {
    private double from;
    private double to;
    private double epsilon = 0.000001;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range() {
        this.from = 0;
        this.to = 0;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double length() {
        return Math.abs(Math.abs(from) - Math.abs(to));
    }

    public boolean isInside(double num) {
        return (num >= from && num <= to);
    }

    public void print() {
        System.out.printf("(%.2f; %.2f)", from, to);
    }

    public String toString() {
        return new String ("(" + from + "; " + to + ")");
    }

    public Range intersection(Range range) {
        if (this.to < range.from || this.from > range.to) {
            return null;
        }
        return (new Range(Math.max(this.from, range.from), Math.min(this.to, range.to)));
    }

    public Range union(Range range) {
        return (new Range(Math.min(this.from, range.from), Math.max(this.to, range.to)));
    }

    public Range[] subtraction (Range range) {
        Range[] subtractions = new Range[2];

        if (this.intersection(range) != null) {
            if (this.from <= range.from && this.to >= range.to) {
                subtractions[0] = new Range(this.from, range.from);
                subtractions[1] = new Range(range.to, this.to);
                return subtractions;
            }
            if (this.from <= range.from && this.to <= range.to) {
                subtractions[0] = new Range(this.from, range.from);
                return subtractions;
            }
            if (this.from >= range.from && this.to >= range.to) {
                subtractions[0] = new Range(range.to, this.to);
                return subtractions;
            }
        }
        subtractions[0] = this;
        return subtractions;
    }

}
