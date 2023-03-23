/**
 * @(#)Boerse.java
 *
 *
 * @author 
 * @version 1.00 2016/3/15
 */
import java.util.*;

public class Boerse {
	static Double[] stock = {
        13.75,14.07,13.96,13.97,13.8,13.63,13.77,13.75,13.73,13.56,13.49,
        13.16,13.6,13.05,13.21,13.4,13.5,13.72,13.63,13.78,13.84,13.83,
        13.81,13.91,14.43,14.17,13.76,14.08,14.3,14.3,14.66,14.54,14.45,
        13.0,12.9,13.13,13.06,13.08
    };

    public static List<Double> CalculateChanges(List<Double> changed, int dayDifference){
        List<Double> changes = new ArrayList<Double>();

        for(int i = 0; i < changed.size()-dayDifference; i++){
            Double priceNow = changed.get(i);
            Double priceFuture = changed.get(i + dayDifference);

            changes.add(priceFuture-priceNow);
        }

        return changes;
    }

    public static double GetMaxOfList(List<Double> items){
        return items.stream()
                .mapToDouble(v -> v)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    public static double GetMinOfList(List<Double> items){
        return items.stream()
                .mapToDouble(v -> v)
                .min()
                .orElseThrow(NoSuchElementException::new);
    }


    public static double maxDayProfit() {
        List<Double> prices = Arrays.asList(stock);

        List<Double> changes = CalculateChanges(prices, 1);

        return GetMaxOfList(changes);
    }
    
    public static double maxWeekProfit() {
        List<Double> prices = Arrays.asList(stock);

        // take 5 as day difference because weekends are not listed in array
        List<Double> changes = CalculateChanges(prices, 5);

        return GetMaxOfList(changes);
    }

    // brute force algorithm to get possible max profit
    public static double maxTotalProfit() {
        List<Double> prices = Arrays.asList(stock);
        List<Double> profits = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++){
            for (int j = i; j < prices.size(); j++){
                Double item1 = prices.get(i);
                Double item2 = prices.get(j);

                profits.add(item2-item1);
            }
        }

        return GetMaxOfList(profits);
    }
					
	public static void main(String[] args) {
	    System.out.printf("%1.3f %1.3f %1.3f",maxDayProfit(),maxWeekProfit(),maxTotalProfit());
	}

}













