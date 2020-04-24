//4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
public class task4 {
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0, l = arr[i].length; j < arr[i].length; j++, l--) {
                if (i == j || i == (l - 1)) arr[i][j] = 1;
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\r\n");
        }
    }
}