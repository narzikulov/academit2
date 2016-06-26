package ru.academit.narzikulov.timelabel;

import javax.swing.*;

/**
 * Created by User on 27.06.2016.
 */
public class TimeLabelMain {
    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TimeLabel();
            }
        });
    }
}
