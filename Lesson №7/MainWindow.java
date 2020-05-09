package Lesson7;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final GameWindow gameWindow;
    private static final int WIN_HEIGHT = 230;
    private static final int WIN_WIDTH = 350;
    private static final int MIN_WIN_LEN = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_WIN_LEN = 10;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String STR_WIN_LEN = "Количество клеток для победы: ";
    private static final String STR_FILED_SIZE = "Размер поля: ";
    private JRadioButton jrbHumVsAi = new JRadioButton("Игрок против ИИ", true);
    private JRadioButton jrbHumVsHum = new JRadioButton("Игрок против игрока");
    private ButtonGroup gameMode = new ButtonGroup();
    private JSlider slFieldSize;
    private JSlider slWinLeght;

    public MainWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setTitle("Выберите параметры игры: ");
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo (null);

        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) (gameWindowBounds.getCenterX() - WIN_WIDTH/2);
        int posY = (int) (gameWindowBounds.getCenterY() - WIN_HEIGHT/2);

        setLocation(posX, posY);
        setLayout(new GridLayout(10,1));

        gameControlsMode();
        gameControlsWinLen();

        JButton startGame = new JButton("Начать игру !");
        startGame.setBackground(Color.decode("#ffffff"));
        startGame.setBorder(BorderFactory.createLineBorder(Color.decode("#d1d1d1")));
        startGame.addActionListener(e -> startGame());
        add(startGame);
    }

    void startGame() {
        int gameMode;
        if(jrbHumVsAi.isSelected()) {
            gameMode = GameMap.MODE_H_V_A;
        } else {
            gameMode = GameMap.MODE_H_V_H;
        }
        int fieldSize = slFieldSize.getValue();
        int winLen = slWinLeght.getValue();
        gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLen);
        setVisible(false);
    }

    private void gameControlsMode() {
        add(new JLabel("Выберите режим игры: "));
        gameMode.add(jrbHumVsAi);
        gameMode.add(jrbHumVsHum);
        add(jrbHumVsAi);
        add(jrbHumVsHum);
    }

    void gameControlsWinLen() {
        add(new JLabel("Выберите размер поля: "));
        final JLabel lblFieldSize = new JLabel(STR_FILED_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);

        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_WIN_LEN);
        slFieldSize.addChangeListener(e -> {
            int currentFieldSize = slFieldSize.getValue();
            lblFieldSize.setText(STR_FILED_SIZE + currentFieldSize);
            slWinLeght.setMaximum(currentFieldSize);
        });
        add(slFieldSize);

        add(new JLabel("Выберите количество клеток для победы: "));
        final JLabel lblWinLen = new JLabel(STR_WIN_LEN + MIN_WIN_LEN);
        add(lblWinLen);

        slWinLeght = new JSlider(MIN_WIN_LEN, MAX_WIN_LEN, MIN_WIN_LEN);
        slWinLeght.addChangeListener(e -> lblWinLen.setText(STR_WIN_LEN + slWinLeght.getValue()));
        add(slWinLeght);
    }
}