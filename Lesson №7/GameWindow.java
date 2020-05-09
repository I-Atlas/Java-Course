package Lesson7;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 510;
    private static GameMap field;
    private static MainWindow mainWindow;

    public GameWindow() {

        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo ( null );
        setResizable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton newGame = new JButton("Новая игра");
        JButton goExit = new JButton("Выход");

        mainWindow = new MainWindow(this);

        bottomPanel.add(newGame);
        bottomPanel.add(goExit);

        newGame.addActionListener(e -> mainWindow.setVisible(true));
        newGame.setBackground(Color.decode("#ffffff"));
        newGame.setBorder(BorderFactory.createLineBorder(Color.decode("#d1d1d1")));
        goExit.addActionListener(e -> System.exit(0));
        goExit.setBackground(Color.decode("#ffffff"));
        goExit.setBorder(BorderFactory.createLineBorder(Color.decode("#d1d1d1")));

        field = new GameMap();
        add(field, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    void startNewGame(int mode, int filedSizeX, int filedSizeY, int winLen) {
        field.startNewGame(mode, filedSizeX, filedSizeY, winLen);
    }
}