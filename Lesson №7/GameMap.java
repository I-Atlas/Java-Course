package Lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMap extends JPanel {
    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;
    private static final char USER_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '.';
    private static final int DELTA_DRAW = 10;
    private static Random random = new Random();
    private GameOutcome gameOutcome;
    char[][] field;
    int fieldSizeX;
    int fieldSizeY;
    int winLenght;
    boolean gameOver;
    boolean isWait;
    int mode;
    int cellHeight;
    int cellWidth;
    boolean isInitialized = false;
    boolean stepPlayer2;

    GameMap() {
        setBackground(Color.decode("#F5F5F5"));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Update(e);
            }
        });
        gameOutcome = new GameOutcome();
    }

    void playerStep(int y,int x, char sumbol) {
        setSum(y, x, sumbol);
    }

    void aiStep() {
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellValid(i, j)) {
                    setSum(i, j, AI_DOT);
                    if (checkWin(AI_DOT)) return;
                    setSum(i, j, EMPTY_DOT);
                }
            }
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellValid(i, j)) {
                    setSum(i, j, USER_DOT);
                    if (checkWin(USER_DOT)) {
                        setSum(i, j, AI_DOT);
                        return;
                    }
                    setSum(i, j, EMPTY_DOT);
                }
            }
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellValid(y, x));
        setSum(y, x, AI_DOT);
    }

    void Update(MouseEvent e) {
        if (!gameOver && !isWait) {
            int cellX = e.getX()/cellWidth;
            int cellY = e.getY()/cellHeight;
            if (mode == 0) playerVsAI(cellY, cellX);
            else playerVsPlayer(cellY, cellX);
        }
    }

    void playerVsPlayer(int y, int x) {
        char newsumbol =  stepPlayer2 ? AI_DOT : USER_DOT;
        playerStep(y,x,newsumbol);
        repaint();
        if (checkWin(newsumbol)) {
            gameOutcome.setMessage(stepPlayer2 ? "Победил игрок №2 !" : "Победил игрок №1 !",this);
            return;
        }
        if (isMapFull()) {
            gameOutcome.setMessage("Ничья !",this);
            return;
        }
        stepPlayer2 = !stepPlayer2;
    }

    void playerVsAI(int y, int x) {
        playerStep(y,x,USER_DOT);
        repaint();
        if (checkWin(USER_DOT)) {
            gameOutcome.setMessage("Победил игрок !",this);
            return;
        }
        if (isMapFull()) {
            gameOutcome.setMessage("Ничья !",this);
            return;
        }
        isWait = true;
        aiStep();
        isWait = false;
        repaint();
        if (checkWin(AI_DOT)) {
            gameOutcome.setMessage("Победил ИИ !",this);
            return;
        }
        if (isMapFull()) {
            gameOutcome.setMessage("Ничья !",this);
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Render(g);
    }

    void startNewGame(int mode, int filedSizeX, int filedSizeY, int winLen) {
        System.out.println(mode + " " + filedSizeX + " " + winLen);
        gameOver = false;
        stepPlayer2 = false;
        isWait = false;
        this.fieldSizeX = filedSizeX;
        this.fieldSizeY = filedSizeY;
        this.winLenght = winLen;
        this.mode = mode;
        field = new char[filedSizeY][filedSizeX];
        initFields();
        isInitialized = true;
        repaint();
    }

    void Render(Graphics g) {
        if(!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        cellWidth = panelWidth/fieldSizeY;
        cellHeight = panelHeight/fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x,0,x, panelHeight);
        }

        g.setColor(Color.decode("#757575"));
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] != EMPTY_DOT) {
                    if (field[j][i] == USER_DOT) {
                        // Крестик
                        g.drawLine((i * cellWidth) + DELTA_DRAW, (j * cellHeight)+ DELTA_DRAW, (i + 1) * cellWidth - DELTA_DRAW, (j + 1) * cellHeight - DELTA_DRAW);
                        g.drawLine((i + 1) * cellWidth - DELTA_DRAW, (j * cellHeight) + DELTA_DRAW , (i * cellWidth) + DELTA_DRAW, (j + 1) * cellHeight - DELTA_DRAW);
                    }
                    if (field[j][i] == AI_DOT) {
                        // Нолик
                        g.drawOval((i * cellWidth) + DELTA_DRAW, (j * cellHeight) + DELTA_DRAW, cellWidth - DELTA_DRAW * 2, cellHeight - DELTA_DRAW * 2);
                    }
                }
            }
        }
    }

    private void initFields() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private void setSum(int y, int x, char sum) {
        field[y][x] = sum;
    }
    
    private boolean checkLine(int y, int x, int vy, int vx, char sum) {
        int wayX = x + (winLenght - 1) * vx;
        int wayY = y + (winLenght - 1) * vy;
        if (wayX < 0 || wayY < 0 || wayX > fieldSizeX - 1 || wayY > fieldSizeY - 1) return false;
        for (int i = 0; i < winLenght; i++) {
            int itemY = y + i * vy;
            int itemX = x + i * vx;
            if (field[itemY][itemX] != sum) return false;
        }
        return true;
    }

    private boolean checkWin(char sum) {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (checkLine(i, j, 0, 1,  sum)) return true;
                if (checkLine(i, j, 1, 1,  sum)) return true;
                if (checkLine(i, j, 1, 0,  sum)) return true;
                if (checkLine(i, j, -1, 1, sum)) return true;
            }
        }
        return false;
    }

    boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > fieldSizeX - 1 || y > fieldSizeY - 1) {
            return false;
        }
        return field[y][x] == EMPTY_DOT;
    }
}