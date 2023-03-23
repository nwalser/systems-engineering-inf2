import java.util.ArrayList;
import java.util.Random;

public class Lotto {
 	static final int ZIEHUNGEN = 6;
	static final int MAXLOTTOZAHLEN = 42;


	// returns array with items from lower to upper limit
	private static ArrayList<Integer> InitializeLottoArray(int lower, int upper){
		int numberOfItems = (upper+1)-lower;

		ArrayList<Integer> list = new ArrayList<Integer>();

		for(int i = 0; i < numberOfItems; i++){
			list.add(i + lower);
		}

		return list;
	}

    public static int[] ziehung() {
        ArrayList<Integer> numbersToPull = InitializeLottoArray(1, MAXLOTTOZAHLEN);

		ArrayList<Integer> pulledNumbers = new ArrayList<Integer>();

		for(int i = 0; i < ZIEHUNGEN; i++){
			Random r = new Random();
			int numberIndex = r.nextInt(numbersToPull.size());
			int nextNumber = numbersToPull.get(numberIndex);
			numbersToPull.remove(numberIndex);
			pulledNumbers.add(nextNumber);
		}

		// convert list to array
		int[] array = pulledNumbers.stream().mapToInt(Integer::intValue).toArray();

		return array;
    }
	
	public static void main(String[] args) {
        int[] zahlen = ziehung();
		for (int i = 0; i < ZIEHUNGEN; i++) {
			System.out.printf("%d ", zahlen[i]);
		}
		System.out.printf("\nLottozahlen wie immer ohne Gewahr\n");
	}
}