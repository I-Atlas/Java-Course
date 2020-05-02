import javax.swing.*;
import java.awt.*;

class Window extends JFrame {
    static final int countCat = 5;
    Plate plate;
    Cat[] cats = new Cat[countCat];
    public Window() {
        initWindow();
        setTitle("Lesson 5");
        setBounds(0, 0, 520, 740);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFont(new Font("Roboto",Font.BOLD,12));
        setLayout(new BorderLayout());
        JButton resetWindow = new JButton("Сбросить");
        JButton feedCats = new JButton("Покормить кошек");
        JButton addFood = new JButton("Добавить еды");

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(resetWindow);
        buttons.add(feedCats);
        buttons.add(addFood);
        add(buttons, BorderLayout.PAGE_END);
        addFood.addActionListener(e -> {
            plate.increaseFood(25);
            repaint();
        });
        feedCats.addActionListener(e -> {
            for(Cat cat : cats) {
                cat.eat(plate);
            }
            repaint();
        });
        resetWindow.addActionListener(e -> {
            initWindow();
            repaint();
        });
        setVisible(true);

    }

    public void initWindow() {
        plate = new Plate(rnd(50, 150));

        for (int i = 0; i < countCat; i++) {
            cats[i] = new Cat(rnd(10, 50));
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        plate.paint(g);

        g.drawString("Кошки: ", 30, 80);
        for (int i = 0; i < countCat; i++) {
            cats[i].paint(g, i + 1);
        }
    }

    private int rnd(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}

class Cat {
    private int Windowetite;
    private boolean isFull = false;

    Cat(int Windowetite) {
        this.Windowetite = Windowetite;
    }

    void eat(Plate plate) {
        if (!isFull) {
            isFull = plate.dereaseFood(Windowetite);
        }
    }

    public void paint(Graphics g, int vgap) {
        vgap = 80 * vgap + 60;
        g.setColor(new Color(0xAD, 0xDA, 0x80));
        g.drawRect(20,vgap, Windowetite,50);
        g.fillRect(20,vgap, isFull ? Windowetite : 0,50);
        g.setColor(Color.BLACK);
        g.drawString(isFull ? "" : String.valueOf(Windowetite), 30, vgap + 30);
    }
}

class Plate {
    private final int volume = 150;
    private int food;

    Plate(int food) {
        this.food = food;
    }

    boolean dereaseFood(int food) {
        if (this.food >= food) {
            this.food -= food;
            return true;
        }
        return false;
    }

    void increaseFood(int food) {
        this.food += (this.food + food <= volume) ? food : volume - this.food;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(0xF8, 0xDA, 0x8A));
        g.drawRect(20, 50, volume, 50);
        g.fillRect(20, 50, food, 50);
        g.setColor(Color.BLACK);
        g.drawString("Миска", 20, 45);
        g.drawString(getVolume(), 30, 80);
    }

    private String getVolume() {
        return food + " / " + volume;
    }

    public static class task1 {

        public static void main(String[] args) {
            Window window = new Window();
        }

    }
}