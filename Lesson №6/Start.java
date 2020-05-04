package Lesson6;

import javax.swing.*;

public class Start {
    public Start() {
        JFrame calculator = new JFrame("Calculator");
        calculator.setSize(465, 660);
        calculator.setResizable(false);
        calculator.add(new Calculator());
        calculator.setLocationRelativeTo(null);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }

}
