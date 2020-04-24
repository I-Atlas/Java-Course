import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    /*
    блок настроек игры
    */
    public static int aiLevel = 0;
    private static int SIZE = 3;
    public static String[][] map = new String[SIZE][SIZE];

    public static final String USER_DOT = "X";
    public static final String USER_DOT_SECOND = "O";
    public static final String AI_DOT = "O";
    public static final String EMPTY_DOT = "-";


    public static void main(String[] args) {
        gameMenu();
    }

    /**
     * Метод игры 'Игрок против игрока'
     */
    public static void modeTwoPlayers() {
        int count = 0;
        initMap();
        while (true) {
            printMap();
            playerTurn(USER_DOT, 1);
            count++;
            if (checkWin(USER_DOT)) {
                System.out.println("Победил пользователь #1.");
                printMap();
                break;
            }
            playerTurn(USER_DOT_SECOND, 2);
            count++;
            if (checkWin(USER_DOT_SECOND)) {
                System.out.println("Победил пользователь #2.");
                printMap();
                break;
            }
            if (count == Math.pow(SIZE, 2)) {
                printMap();
                break;
            }
        }
    }

    /**
     * Метод игры 'Игрок против ии'
     */
    public static void modeAgainstAI() {
        int count = 0;
        initMap();
        while (true) {
            printMap();
            playerTurn(USER_DOT, 0);
            count++;
            if (checkWin(USER_DOT)) {
                System.out.println("Победил пользователь.");
                printMap();
                break;
            }

            aiTurn();
            count++;
            if (checkWin(AI_DOT)) {
                System.out.println("Победил ии.");
                printMap();
                break;
            }
            if (count == Math.pow(SIZE, 2)) {
                printMap();
                break;
            }
        }
    }

    /**
     * Метод выбора режима игры
     */
    public static void gameMenu() {
        System.out.println("Выберите режим игры: ");
        System.out.println("1. Игрок против ии.");
        System.out.println("2. Игрок против игрока.");
        System.out.println("3. Выход.");
        int i = 0;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        switch (i) {
            case 1: {
                aiLevel();
                break;
            }
            case 2: {
                modeTwoPlayers();
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Было введено неверное значение!");
            }
        }
    }

    /**
     * Метод выбора сложности ии
     */
    public static void aiLevel() {
        System.out.println("Выберите сложность ии: ");
        System.out.println("1. Глупый.");
        System.out.println("2. Умный.");
        System.out.println("3. IBM Deep Blue.");
        System.out.println("4. Назад.");
        System.out.println("5. Выход.");
        int i = 0;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        switch (i) {
            case 1: {
                aiLevel = 0;
                modeAgainstAI();
                break;
            }
            case 2: {
                aiLevel = 1;
                modeAgainstAI();
                break;
            }
            case 3: {
                aiLevel = 2;
                modeAgainstAI();
                break;
            }
            case 4: {
                gameMenu();
                break;
            }
            case 5: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Введите значение 1 - 5.");
            }
        }
    }

    /**
     * Метод подготовки игрового поля
     */
    public static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
    }

    /**
     * Метод вывода игрового поля на экран
     */
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Ход пользователя
     */
    public static void playerTurn(String sign, int i) {
        int x = -1;
        int y = -1;
        do {
            if (i == 0) {
                System.out.println("Введите координаты ячейки через пробел (горизонталь-вертикаль, x - y) (1 - " + SIZE + "): ");
            } else {
                System.out.println("Игрок " + i + ". Введите координаты ячейки через пробел (горизонталь-вертикаль, x - y) (1 - " + SIZE + "): ");
            }
            Scanner sc = new Scanner(System.in);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (isCellValid(x, y));
        map[x][y] = sign;
    }

    /**
     * Ход ии
     * Если выбран уровень 3 (aiLevel == 2), ии анализирует ход игрока и свой ход
     * Если выбран уровень 2 (aiLevel == 1), ии анализирует ход игрока. Если выигрышный ход существует, то ии ходит
     * Если выбран уровень 1 (aiLevel == 1), ии ходит случайным образом, рандомит ячейку
     */
    public static void aiTurn() {
        int x = -1;
        int y = -1;
        boolean ai_win = false;
        boolean user_win = false;
        if (aiLevel == 2) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (!isCellValid(i, j)) {
                        map[i][j] = AI_DOT;
                        if (checkWin(AI_DOT)) {
                            x = i;
                            y = j;
                            ai_win = true;
                        }
                        map[i][j] = EMPTY_DOT;
                    }
                }
            }
        }
        if (aiLevel > 0) {
            if (!ai_win) {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (!isCellValid(i, j)) {
                            map[i][j] = USER_DOT;
                            if (checkWin(USER_DOT)) {
                                x = i;
                                y = j;
                                user_win = true;
                            }
                            map[i][j] = EMPTY_DOT;
                        }
                    }
                }
            }
        }
        if (!ai_win && !user_win) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(SIZE);
                y = rnd.nextInt(SIZE);
            }
            while (isCellValid(x, y));
        }
        map[x][y] = AI_DOT;
        System.out.println("x = " + x + "| y = " + y + "| Выиграл ии = " + ai_win + "| Выиграл пользователь = " + user_win);
    }

    /**
     * Метод валидации заправшивает ячейки на корректность
     */
    public static boolean isCellValid(int x, int y) {
        //проверка вводимых координат
        if (x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1) {
            return false;
        }
        return map[x][y] != EMPTY_DOT;
    }

    /**
     * Метод проверки победы. Проверяет одну линию: горизонталь или вертикаль или диагональ
     */
    public static boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        for (int i = 0; i < SIZE; i++) {
            if (map[start_x + i * dx][start_y + i * dy] != sign)
                return false;
        }
        return true;
    }

    /**
     * Метод проверки победы. Проверяет все линии, если находит выигрышную, возвращает true или false
     */
    public static boolean checkWin(String sign) {
        for (int i = 0; i < SIZE; i++) {
            // проверка строк
            if (checkLine(i, 0, 0, 1, sign)) return true;
            // проверка столбцов
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        // проверка диагонали
        if (checkLine(0, 0, 1, 1, sign)) return true;
        if (checkLine(0, SIZE - 1, 1, -1, sign)) return true;
        return false;
    }
}
    