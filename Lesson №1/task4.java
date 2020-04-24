//4. Написать метод, принимающий на вход два числа, и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false;
public class task4 {
    public static void main(String[] args) {
        System.out.println(do_task(3, 4));
    }

    static boolean do_task(int a, int b) {
        int sum = a + b;
        if (sum > 10 && sum < 20) return true;
        else return false;
    }
}