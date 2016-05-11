package ru.academit.narzikulov;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Created by tim on 04.05.2016.
 */
public class TemperatureFrame {
    private JFrame tempFrame = new JFrame("Temperature");
    private JButton calcButton = new JButton("Calculate");
    private JTextField inputTemp = new JTextField("", 10);
    private JLabel outputTemp = new JLabel(inputTemp.getText());
    private JRadioButton celsiusChoiceButton = new JRadioButton("Celsius");
    private JRadioButton fahrenheitChoiceButton = new JRadioButton("Fahrenheit");
    private JRadioButton kelvinChoiceButton = new JRadioButton("Kelvin");
    private ButtonGroup selectedScaleRButton = new ButtonGroup();
    private TemperatureCalc convertedTemp = new TemperatureCalc();

    public TemperatureFrame(int x, int y) {
        tempFrame.setBounds(400, 100, x, y);
        tempFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Dimension textFieldDim = new Dimension(200, 50);
        inputTemp.setMaximumSize(textFieldDim);
        inputTemp.setLocation(50, 50);
        inputTemp.setHorizontalAlignment(0);
        inputTemp.setBorder(new TitledBorder("Input celsius temperature"));
        panel.add(inputTemp, BorderLayout.NORTH);
        inputTemp.addFocusListener(new FocusListenerForChoiceButtons());

        selectedScaleRButton.add(celsiusChoiceButton);
        selectedScaleRButton.add(fahrenheitChoiceButton);
        selectedScaleRButton.add(kelvinChoiceButton);
        celsiusChoiceButton.setSelected(true);

        Box radioBox = Box.createVerticalBox();
        radioBox.add(celsiusChoiceButton);
        radioBox.add(fahrenheitChoiceButton);
        radioBox.add(kelvinChoiceButton);
        panel.add(radioBox, BorderLayout.WEST);
        radioBox.setBorder(new TitledBorder("Select scale"));

        celsiusChoiceButton.addFocusListener(new FocusListenerForChoiceButtons());
        fahrenheitChoiceButton.addFocusListener(new FocusListenerForChoiceButtons());
        kelvinChoiceButton.addFocusListener(new FocusListenerForChoiceButtons());

        celsiusChoiceButton.addActionListener(new ActionListenerForButtons());
        fahrenheitChoiceButton.addActionListener(new ActionListenerForButtons());
        kelvinChoiceButton.addActionListener(new ActionListenerForButtons());

        calcButton.setSize(x / 2, y / 8);
        calcButton.setLocation((int) (x * 0.4), (int) (y * 0.7));
        panel.add(calcButton, BorderLayout.SOUTH);
        calcButton.addActionListener(new ActionListenerForButtons());

        Font font = new Font("arial", Font.BOLD, 18);
        outputTemp.setFont(font);
        outputTemp.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelForOutputTemp = new JPanel();
        panelForOutputTemp.setBorder(new TitledBorder("Converted temperature:"));
        panel.add(panelForOutputTemp);
        panelForOutputTemp.add(outputTemp);

        tempFrame.add(panel);
    }

    public void setTempFrame() {
        tempFrame.setVisible(true);
    }

    private void resultPrinting() {
        try {
            Double temp = Double.valueOf(inputTemp.getText());
            if (celsiusChoiceButton.isSelected()) {
                outputTemp.setText(temp.toString());
            }
            if (fahrenheitChoiceButton.isSelected()) {
                outputTemp.setText(String.valueOf(convertedTemp.celsiusToFahrenheit(temp)));
            }
            if (kelvinChoiceButton.isSelected()) {
                outputTemp.setText(String.valueOf(convertedTemp.celsiusToKelvin(temp)));
            }
            outputTemp.setVisible(true);

        } catch (NumberFormatException e1) {
            outputTemp.setText("Not a temperature!");
            inputTemp.requestFocus();
        }
    }

    private class ActionListenerForButtons implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultPrinting();
        }
    }

    private class FocusListenerForChoiceButtons implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            resultPrinting();
        }

        @Override
        public void focusLost(FocusEvent e) {
            resultPrinting();
        }
    }
}


