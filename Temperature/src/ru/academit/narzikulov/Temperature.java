package ru.academit.narzikulov;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Created by tim on 04.05.2016.
 */
public class Temperature extends JFrame {
    private JButton button = new JButton("Calculate");
    private JTextField inputTemp = new JTextField("", 10);
    private JLabel outputTemp = new JLabel(inputTemp.getText());
    private JRadioButton celsiusRButton = new JRadioButton("Celsius");
    private JRadioButton farengheitRButton = new JRadioButton("Farengheit");
    private JRadioButton kelvinRButton = new JRadioButton("Kelvin");
    private ButtonGroup selectedScaleRButton = new ButtonGroup();


    public Temperature(int x, int y) {
        super("Temperature");
        this.setBounds(400, 100, x, y);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Dimension textFieldDim = new Dimension(200, 50);
        inputTemp.setMaximumSize(textFieldDim);
        inputTemp.setLocation(50, 50);
        inputTemp.setHorizontalAlignment(0);
        inputTemp.setBorder(new TitledBorder("Input celsius temperature"));
        panel.add(inputTemp, BorderLayout.PAGE_START);
        inputTemp.addFocusListener(new FocusL());

        selectedScaleRButton.add(celsiusRButton);
        selectedScaleRButton.add(farengheitRButton);
        selectedScaleRButton.add(kelvinRButton);
        celsiusRButton.setSelected(true);

        Box radioBox = Box.createVerticalBox();
        radioBox.add(celsiusRButton);
        radioBox.add(farengheitRButton);
        radioBox.add(kelvinRButton);
        panel.add(radioBox, BorderLayout.LINE_START);
        radioBox.setBorder(new TitledBorder("Select scale"));

        celsiusRButton.addMouseListener(new MouseL());
        farengheitRButton.addMouseListener(new MouseL());
        kelvinRButton.addMouseListener(new MouseL());

        button.setSize(x / 2, y / 8);
        button.setLocation((int) (x * 0.4), (int) (y * 0.7));
        panel.add(button, BorderLayout.PAGE_END);
        button.addMouseListener(new MouseL());

        Font font = new Font("arial", Font.BOLD, 15);
        outputTemp.setFont(font);
        panel.add(outputTemp, BorderLayout.LINE_END);

        setContentPane(panel);
    }

    class MouseL implements MouseListener {
        public void mouseClicked(MouseEvent event) {
            try {
                //Double.parseDouble(inputTemp.getText());
                Double temp = Double.valueOf(inputTemp.getText());
                if (celsiusRButton.isSelected()) {
                    outputTemp.setText(temp.toString());
                }
                if (farengheitRButton.isSelected()) {
                    temp = temp * 1.8 + 32;
                    outputTemp.setText(temp.toString());
                }
                if (kelvinRButton.isSelected()) {
                    temp += 273.15;
                    outputTemp.setText(temp.toString());
                }
                outputTemp.setVisible(true);

            } catch (NumberFormatException e1) {
                outputTemp.setText("Not a temperature!");
                inputTemp.requestFocus();
            }
        }

        public void mouseEntered(MouseEvent event) {
        }

        public void mouseExited(MouseEvent event) {
        }

        public void mousePressed(MouseEvent event) {
        }

        public void mouseReleased(MouseEvent event) {
        }
    }

    class FocusL implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                Double.parseDouble(inputTemp.getText());
            } catch (NumberFormatException e1) {
                outputTemp.setText("Not a temperature!");
                inputTemp.requestFocus();
            }
        }
    }
}


