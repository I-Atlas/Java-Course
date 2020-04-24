//7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
public class task7 {
    public static void main(String[] args) {
        do_task("Ilya");
    }

    static void do_task(String name) {
        System.out.println("Привет, " + name + "!");
    }
}