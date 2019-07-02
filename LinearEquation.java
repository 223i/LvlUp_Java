import java.util.Scanner;

public class LinearEquation {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double a, b, x;
        System.out.println("Введите, пожалуйста, число а:");

        while (!scanner.hasNextDouble()){
            System.out.println("Вы ввели не число. Введите, пожалуйста, число а:");
            scanner.next();
        }
        a = scanner.nextDouble();

        System.out.println("Введите, пожалуйста, число b:");

        while (!scanner.hasNextDouble()){
            System.out.println("Вы ввели не число. Введите, пожалуйста, число b:");
            scanner.next();
        }
        b = scanner.nextDouble();

        if (a == 0 & b != 0){
            System.out.println("Нет решений");
        } else if (a == 0 & b == 0){
            System.out.println("x - любое число");
        } else {
            x = -1 * (b/a);
            System.out.println(x);
        }
    }
}
