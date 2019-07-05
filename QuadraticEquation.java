import java.util.Scanner;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a, b, c, x, D;
        System.out.println("Введите, пожалуйста, число а:");

        while (!scanner.hasNextDouble()) {
            System.out.println("Неверный формат ввода. Введите, пожалуйста, число а:");
            scanner.next();
        }
        a = scanner.nextDouble();

        System.out.println("Введите, пожалуйста, число b:");
        while (!scanner.hasNextDouble()) {
            System.out.println("Неверный формат ввода. Введите, пожалуйста, число b:");
            scanner.next();
        }
        b = scanner.nextDouble();

        System.out.println("Введите, пожалуйста, число c:");

        while (!scanner.hasNextDouble()) {
            System.out.println("Неверный формат ввода. Введите, пожалуйста, число c:");
            scanner.next();
        }
        c = scanner.nextDouble();

        D = b * b - 4 * a * c;

        if (a == 0) {
            if (b != 0) {
                x = -b / c;
                System.out.println(x);
            } else if (c == 0) {
                System.out.println("x - любое число");
            } else {
                System.out.println("Нет решений");
            }

        } else if (D > 0) {
            x = (-b + Math.sqrt(D)) / (2 * a);
            System.out.println(x);
            x = (-b - Math.sqrt(D)) / (2 * a);
            System.out.println(x);

        } else if (D == 0) {
            x = -b / (2 * a);
            System.out.println(x);
        } else {
            System.out.println("Уравнение не имеет корней");
        }
    }
}
