public class Lotto {
    
 	static final int ZIEHUNGEN = 6;
	static final int MAXLOTTOZAHLEN = 42;
		   
    public static int[] ziehung() {
        /* TO BE DONE */
    }
	
	public static void main(String[] args) {
        int[] zahlen = ziehung();
		for (int i = 0; i < ZIEHUNGEN; i++) {
			System.out.printf("%d ", zahlen[i]);
		}
		System.out.printf("\nLottozahlen wie immer ohne Gewahr\n");
	}
}