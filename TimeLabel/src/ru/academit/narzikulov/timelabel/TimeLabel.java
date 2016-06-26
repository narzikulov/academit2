package ru.academit.narzikulov.timelabel;
/**
 * Created by User on 27.06.2016.
 */
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;

public class TimeLabel {

    private JLabel label;
    private Timer timer;
    private DateFormat format;

    public TimeLabel() {
        label = new JLabel("00:00:00");
        label.setHorizontalAlignment(JLabel.CENTER);
        format = DateFormat.getTimeInstance();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                label.setText(format.format(new Date()));
            }
        });

        JFrame frame = new JFrame("Current time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.add(label);
        timer.start();
        frame.setVisible(true);
    }
}