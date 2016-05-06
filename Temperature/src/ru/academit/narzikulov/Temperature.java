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
        panel.setLayout(new BorderLayout());

        //Box box = Box.createVerticalBox();

        Dimension textFieldDim = new Dimension(200, 50);
        inputTemp.setMaximumSize(textFieldDim);
        inputTemp.setLocation(50, 50);
        inputTemp.setHorizontalAlignment(0);
        inputTemp.setBorder(new TitledBorder("Input temperature"));
        panel.add(inputTemp, BorderLayout.PAGE_START);

        JRadioButton celsiusRButton = new JRadioButton("Celsius");
        JRadioButton farengheitRButton = new JRadioButton("Farengheit");
        JRadioButton kelvinRButton = new JRadioButton("Kelvin");
        ButtonGroup selectedScaleRButton = new ButtonGroup();
        selectedScaleRButton.add(celsiusRButton);
        selectedScaleRButton.add(farengheitRButton);
        selectedScaleRButton.add(kelvinRButton);
        Box radioBox = Box.createVerticalBox();
        radioBox.add(celsiusRButton);
        radioBox.add(farengheitRButton, BorderLayout.LINE_START);
        radioBox.add(kelvinRButton, BorderLayout.LINE_START);
        panel.add(radioBox, BorderLayout.LINE_START);
        radioBox.setBorder(new TitledBorder("Select scale"));

        JButton button = new JButton("Scale");
        button.setSize(x / 2, y / 8);
        button.setLocation((int) (x * 0.4), (int) (y * 0.7));
        //panel.add(button);
        panel.add(button, BorderLayout.PAGE_END);
        //box.setSize(50, 50);
        //box.setLocation(50, 50);

        //panel.add(selectedScaleRButton);

        //JTextArea tempOutText = new JTextArea("");
        //panel.add(tempOutText);

        //setContentPane(panel);
        setContentPane(panel);
        //setSize(x, y);
    }
}
