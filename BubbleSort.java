import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int n = inputLengthOfList();
        int[] numbers = getlistOfNumbers(n);
        int lengthOfArray = numbers.length;

        while (lengthOfArray != -1) {
            for (int i = 1; i < lengthOfArray; i++) {
                int current = numbers[i];
                if (current < numbers[i-1]) {
                    numbers[i] = numbers[i-1];
                    numbers[i-1] = current;
                }
            }
            lengthOfArray-- ;
        }
        System.out.println(Arrays.toString(numbers));
    }

    public static int inputLengthOfList() {
        System.out.println("Введите, пожалуйста, длину массива: ");
        int result;
        while (true) {
            while (!input.hasNextInt()) {
                System.out.println("Неверный формат ввода. Введите, пожалуйста, целое положительное число");
                input.next();
            }
            result = input.nextInt();
            if (result < 0) {
                System.out.println("Вы ввели отрицательное число. Введите положительное целое число");
            } else {
                break;
            }
        }
        return result;
    }

    public static int[] getlistOfNumbers(int LenghtOfList) {
        Random random = new Random();
        int[] numbers = new int[LenghtOfList];
        for (int i = 0; i < LenghtOfList; i++) {
            numbers[i] = random.nextInt(1000);
        }
        return numbers;
    }
}
