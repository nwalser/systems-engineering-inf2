import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        int k = 10;
        System.out.println("Enter Number of Cycles: ");

        try{
            k = Integer.parseInt(reader.readLine());
        } catch (Exception ignored){}

        double calculatedPi = Leibniz.pi(k);
        double correctDigits = Leibniz.correctDigits(calculatedPi);

        System.out.println("PI: " + calculatedPi);
        System.out.println("PI: " + Math.PI);
        System.out.println("Correct Digits: " + correctDigits);
    }
}
