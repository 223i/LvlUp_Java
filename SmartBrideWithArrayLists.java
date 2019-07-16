import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SmartBrideWithArrayLists {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        int amountTop = inputTopAmountAndCandidateAmount("Введите количество топ позиций: ");
        int amountOfMen = inputTopAmountAndCandidateAmount("Введите количество претендентов: ");
        ArrayList<Fiance> listOfCandidates = createListOfCandidates(amountOfMen);

        while (amountOfMen != -1) {
            for (int i = 1; i < amountOfMen; i++) {
                Fiance current = listOfCandidates.get(i);
                if (current.IQ < listOfCandidates.get(i-1).IQ) {
                    listOfCandidates.set(i,listOfCandidates.get(i-1));
                    listOfCandidates.set(i-1, current);
                }
            }
            amountOfMen-- ;
        }

        ArrayList<Fiance> TopCandidates = topNCandidates(amountTop, listOfCandidates);
        for (int i = 1; i<= TopCandidates.size(); i++) {
            System.out.println(i + ". " + TopCandidates.get(i-1).Name + " " + TopCandidates.get(i - 1).IQ);
        }
    }


    public static ArrayList<Fiance> createListOfCandidates (int amountOfCandidates) {
        ArrayList<Fiance> candidates = new ArrayList<>();
        Random random = new Random();

        for (int counter = 0; counter < amountOfCandidates; counter++) {
            System.out.println("Введите имя кандидата: ");
            Fiance candidate = new Fiance(random.nextInt(170),input.next());
            candidates.add(candidate);
        }

        return candidates;
    }

    public static ArrayList<Fiance> topNCandidates(int amountTop, ArrayList<Fiance> listOfCandidates){
        ArrayList<Fiance> topNCandidates = new ArrayList<>();
        for (int n = 0;  n < amountTop; n++){
            Fiance OneOfTopCandidates = listOfCandidates.get(listOfCandidates.size()-1 - n);
            topNCandidates.add(n,OneOfTopCandidates);
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

    public Fiance(int IQ, String name) {
        this.IQ = IQ;
        Name = name;
    }

    @Override
    public String toString() {
        return "Candidate{Name = " + Name + ", IQ = " + IQ + "}";
    }
}