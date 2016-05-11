package ru.academit.narzikulov;

/**
 * Created by tim on 11.05.2016.
 */
public class TemperatureCalc {
    public TemperatureCalc() {
    }

    public double celsiusToFahrenheit(double temp) {
        return temp * 1.8 + 32;
    }

    public double celsiusToKelvin(double temp) {
        return temp + 273.15;
    }
}
