//2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
public class task2 {
    public static void main(String[] args) {
        int[] arr = new int[8];
        for (int i = 1, j = 0; i < arr.length; i++) arr[i] = j += 3;
        for (int l : arr) System.out.print(l + " ");
    }
}