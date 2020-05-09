package Lesson7;

import javax.swing.*;
import java.awt.*;

public class GameOutcome extends JDialog {
    private JLabel jLabel = new JLabel();

    public GameOutcome() {
        add(jLabel, BorderLayout.CENTER);
        JButton ok = new JButton("OK");
        ok.setBackground(Color.decode("#ffffff"));
        ok.setBorder(BorderFactory.createLineBorder(Color.decode("#d1d1d1")));
        ok.addActionListener(event -> setVisible(false));
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(300, 200);
        setLocationRelativeTo ( null );
    }

    public void setMessage(String message, GameMap gameMap) {
        gameMap.gameOver = true;
        this.setVisible(true);
        jLabel.setText(message);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
    }
}
