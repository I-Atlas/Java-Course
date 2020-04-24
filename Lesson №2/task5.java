//5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
public class task5 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round((Math.random() * 20) - 10);
            System.out.print(arr[i] + " ");
        }
    int min = arr[0], max = arr[0], Max = 0, Min = 0;
        for (int i = 0; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
            Max = i;
        }
        if (arr[i] < min) {
            min = arr[i];
            Min = i;
        }
    }
        System.out.println("Значение максимального элемента: " + max + ", индекс максимального элемента: " + Max);
        System.out.println("Значение минимального элемента: " + min + ", индекс минимального элемента: " + Min);
    }
}