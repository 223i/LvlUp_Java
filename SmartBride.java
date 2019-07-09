import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SmartBride {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        int amountTop = inputTopAmountAndCandidateAmount("Введите количество топ позиций: ");
        int amountOfMen = inputTopAmountAndCandidateAmount("Введите количество претендентов: ");
        Fiance[] listOfCandidates = createListOfCandidates(amountOfMen);

        while (amountOfMen != -1) {
            for (int i = 1; i < amountOfMen; i++) {
                Fiance current = listOfCandidates[i];
                if (current.IQ < listOfCandidates[i-1].IQ) {
                    listOfCandidates[i] = listOfCandidates[i-1];
                    listOfCandidates[i-1] = current;
                }
            }
            amountOfMen-- ;
        }

        Fiance[] TopCandidates = topNCandidates(amountTop, listOfCandidates);
        for (int i = 1; i<= TopCandidates.length; i++) {
            System.out.println(i + ". " + TopCandidates[i - 1].Name + " " + TopCandidates[i - 1].IQ);
        }
    }


    public static Fiance[] createListOfCandidates (int amountOfCandidates) {
        Fiance[] candidates = new Fiance[amountOfCandidates];
        Random random = new Random();

        for (int counter = 0; counter < candidates.length; counter++) {
            candidates[counter] = new Fiance();
            System.out.println("Введите имя кандидата: ");
            candidates[counter].Name = input.next();
            candidates[counter].IQ = random.nextInt(170);
        }

        return candidates;
    }

    public static Fiance[] topNCandidates(int amountTop, Fiance[] listOfCandidates){
        Fiance[] topNCandidates = new Fiance[amountTop];
        for (int n = 0;  n < amountTop; n++){
                topNCandidates[n] = listOfCandidates[listOfCandidates.length-1 - n];
        }
        return topNCandidates;
    }

    public static int inputTopAmountAndCandidateAmount(String string) {
        System.out.println(string);
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

}

class Fiance {
    int IQ;
    String Name;

    @Override
    public String toString() {
        return "Candidate{Name = " + Name + ", IQ = " + IQ + "}";
    }
}