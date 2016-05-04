package ru.academit.narzikulov;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Created by tim on 04.05.2016.
 */
public class Temperature extends JFrame {
    private JButton button = new JButton("Scale");
    private JTextField inputTemp = new JTextField("", 10);
    private JLabel label = new JLabel("Input temperature:");

    public Temperature(int x, int y) {
        super("Temperature");
        //this.setSize(x, y);
        this.setBounds(400, 100, x, y);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button = new JButton("Scale");
        button.setSize((int) x / 2, (int) y / 8);
        button.setLocation((int) (x * 0.4), (int) (y * 0.7));
        panel.add(button);

        JRadioButton celsiusRButton = new JRadioButton("Celsius");
        JRadioButton farengheitRButton = new JRadioButton("Farengheit");
        JRadioButton kelvinRButton = new JRadioButton("Kelvin");
        ButtonGroup selectedScaleRButton = new ButtonGroup();
        selectedScaleRButton.add(celsiusRButton);
        selectedScaleRButton.add(farengheitRButton);
        selectedScaleRButton.add(kelvinRButton);
        Box box = Box.createVerticalBox();
        box.add(celsiusRButton);
        box.add(farengheitRButton);
        box.add(kelvinRButton);
        box.setBorder(new TitledBorder("Select scale"));

        //panel.add(selectedScaleRButton);

        JTextField inputTemp = new JTextField();
        //inputTemp.setSize((int) x / 5, (int) y / 8);
        //inputTemp.setLocation((int) (x * 0.4), (int) (y * 0.4));
        //inputTemp.setHorizontalAlignment(0);
        panel.add(inputTemp);


        setContentPane(panel);
        //setContentPane(box);
        //setSize(x, y);
    }
}
