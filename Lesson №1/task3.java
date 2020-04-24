//3. Написать метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – входные параметры этого метода;
public class task3 {
    public static void main(String[] args) {
        System.out.println(do_task(5, 2, 123, 677));
    }

    static int do_task(int a, int b, int c, int d){
        return (a * (b + (c / d)));
    }
}