//6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят;
public class task6 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round((Math.random() * 20) - 10);
            System.out.print(arr[i] + " ");
        }
        System.out.println("\r\nОтвет: " + do_task(arr));
    }
    private static boolean do_task(int[] array) {
        int Left_summ, Right_summ;
        for (int i = 0; i < array.length + 1; i++) {
            Left_summ = 0;
            Right_summ = 0;
            for (int j = 0; j < i; j++) {
                Left_summ += array[j];
            }
            for (int j = i; j < array.length; j++) {
                Right_summ += array[j];
            }
            if (Left_summ == Right_summ) return true;
        }
        return false;
    }
}