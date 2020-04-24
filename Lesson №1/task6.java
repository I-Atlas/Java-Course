//6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
public class task6 {
    public static void main(String[] args) {
        System.out.println(do_task(-1));
    }

    static boolean do_task(int a) {
        if (a < 0) return true;
        return false;
    }
}