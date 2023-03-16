import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leibniz {
    public static double pi(int n){
        double pi = 0;

        for(int i = 0; i < n+1; i++){
            double zahler = Math.pow(-1, i);
            double nenner = 2*i+1;
            double summand = zahler/nenner;

            pi += summand;
        }

        return pi*4;
    }

    public static int correctDigits(double number){
        String pi1 = String.valueOf(number/10);
        String pi2 = String.valueOf(Math.PI/10);

        int digits = 0;

        for(int i = 0; i < pi1.length(); i++){
            if(pi1.charAt(i)==pi2.charAt(i)){
                digits++;
            }else{
                break;
            }
        }

        return digits-2;
    }

    public static int correctSEPDigits() {
        return 0;
    }
}



