/**
 * @(#)Boerse.java
 *
 *
 * @author 
 * @version 1.00 2016/3/15
 */

public class Boerse {
	static double[] stock = {
        13.75,14.07,13.96,13.97,13.8,13.63,13.77,13.75,13.73,13.56,13.49,
        13.16,13.6,13.05,13.21,13.4,13.5,13.72,13.63,13.78,13.84,13.83,
        13.81,13.91,14.43,14.17,13.76,14.08,14.3,14.3,14.66,14.54,14.45,
        13,12.9,13.13,13.06,13.08
    };
    
    public static double maxDayProfit() {
        /* TO BE DONE */
    }
    
    public static double maxWeekProfit() {
        /* TO BE DONE */
    }
    
    public static double maxTotalProfit() {
         /* TO BE DONE */        
    }
					
	public static void main(String[] args) {
	    System.out.printf("%1.3f %1.3f %1.3f",maxDayProfit(),maxWeekProfit(),maxTotalProfit());
	}

}