package ru.academit.narzikulov;

import javax.swing.*;

/**
 * Created by tim on 04.05.2016.
 */
public class TemperatureMain {
    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TemperatureFrame tempFrame = new TemperatureFrame(300, 200);
                tempFrame.setTempFrame(300, 200);
            }
        });
    }
}
