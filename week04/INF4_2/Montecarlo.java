import java.io.*;
import java.util.*;

public class Montecarlo {
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

    public static double pi(int k) {
        int inside = 0;
        int outside = 0;

        for (int i = 0; i < k; i++){
            if(Math.pow(Math.random(), 2)+Math.pow(Math.random(), 2) < 1){
                inside++;
            }else{
                outside++;
            }
        }

        return (double)inside / (inside+outside) * 4;
    }

    public static void main(String[] args) {
        /* TO BE DONE */
    }
}