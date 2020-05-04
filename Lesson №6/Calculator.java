package Lesson6;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JPanel {
    private JButton [] numbers = new JButton [10];
    private JButton equ = new JButton("="), backspace = new JButton ("⌫"), point = new JButton(".");
    private JButton plus = new JButton("+"), minus = new JButton("-"),
            multi = new JButton ("✕"), div = new JButton ("/"),
            del = new JButton ("C"), expo = new JButton ("^");
    private JTextField output = new JTextField();
    private Font font = new Font ("SansSerif", Font.PLAIN, 20);
    private Font font_field = new Font ("SansSerif", Font.BOLD, 35);
    private double FirstValue = 0;
    public String operation = "0";

    public Calculator() {
        setLayout (null);
        setFocusable(true);
        grabFocus();

        output = new JTextField();
        output.setBounds (10,10,430,200);
        output.setFont(font_field);
        output.setEditable(false);
        add(output);

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(x * (100 + 10) + 10, y * (70 + 10) + 300, 100, 70);
                numbers[x * 3 + y + 1].setFont(font);
                numbers[x * 3 + y + 1].setBackground(Color.decode("#F5F5F5"));
                numbers[x * 3 + y + 1].setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
                add(numbers[x * 3 + y +1]);
            }
        }

        numbers[0] = new JButton ("0");
        numbers[0].setBounds(120, 540, 100, 70);
        numbers[0].setFont(font);
        numbers[0].setBackground(Color.decode("#F5F5F5"));
        numbers[0].setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
        add(numbers[0]);

        point.setBounds(10, 540, 100, 70);
        point.setFont(font);
        point.setBackground(Color.decode("#F5F5F5"));
        point.setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
        add(point);

        expo.setBounds(120, 220, 100, 70);
        expo.setFont(font);
        expo.setBackground(Color.decode("#e0e0e0"));
        expo.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(expo);

        equ.setBounds(230, 540, 100, 70);
        equ.setFont(font);
        equ.setBackground(Color.decode("#ffc48a"));
        equ.setBorder(BorderFactory.createLineBorder(Color.decode("#ffc48a")));
        add(equ);

        backspace.setBounds(340, 220, 100, 70);
        backspace.setFont(font);
        backspace.setBackground(Color.decode("#e0e0e0"));
        backspace.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(backspace);

        del.setBounds(230, 220, 100, 70);
        del.setFont (font);
        del.setBackground(Color.decode("#e0e0e0"));
        del.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(del);

        plus.setBounds(340, 540, 100, 70);
        plus.setFont(font);
        plus.setBackground(Color.decode("#e0e0e0"));
        plus.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(plus);

        minus.setBounds(340, 460, 100, 70);
        minus.setFont(font);
        minus.setBackground(Color.decode("#e0e0e0"));
        minus.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(minus);

        multi.setBounds(340, 380, 100, 70);
        multi.setFont(font);
        multi.setBackground(Color.decode("#e0e0e0"));
        multi.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(multi);

        div.setBounds(340, 300, 100, 70);
        div.setFont(font);
        div.setBackground(Color.decode("#e0e0e0"));
        div.setBorder(BorderFactory.createLineBorder(Color.decode("#e0e0e0")));
        add(div);

        ActionListener l = (ActionEvent event) -> {
            JButton b = (JButton)event.getSource();
            output.setText(output.getText() + b.getText());
        };
        for (JButton b: numbers){
            b.addActionListener(l);
        }

        del.addActionListener (event -> {
            String temp = output.getText ();
            output.setText (null);
        });

        backspace.addActionListener(event -> {
            try {
                String temp = output.getText();
                output.setText(temp.substring(0, temp.length() - 1));
            }catch (Exception t) {
                output.setText("Отсутствует значение");
            }
        });

        plus.addActionListener(event -> {
            FirstValue = Double.valueOf(output.getText());
            output.setText("");
            operation = "+";
        });

        minus.addActionListener(event ->  {
            FirstValue = Double.valueOf(output.getText());
            output.setText("");
            operation = "-";
        });

        multi.addActionListener(event ->  {
            FirstValue = Double.valueOf(output.getText());
            output.setText("");
            operation = "*";
        });

        div.addActionListener(event ->  {
            FirstValue = Double.valueOf(output.getText());
            output.setText("");
            operation = "/";
        });

        expo.addActionListener(event ->  {
            FirstValue = Double.valueOf(output.getText());
            output.setText("");
            operation = "^";
        });

        equ.addActionListener(event ->  {
            double SecondValue = Double.valueOf(output.getText());
            if ("+".equals(operation)) {
                output.setText((FirstValue + SecondValue) + "");
            }
            if ("-".equals(operation)) {
                output.setText((FirstValue - SecondValue) + "");
            }
            if ("*".equals(operation)) {
                output.setText((FirstValue * SecondValue) + "");
            }
            if ("/".equals(operation)) {
                output.setText((FirstValue / SecondValue) + "");
            }

            if ("^".equals(operation)) {
                output.setText(Math.pow(FirstValue, SecondValue) + "");
            }

            FirstValue = 0;
            operation = "+";
        });
    }
}
