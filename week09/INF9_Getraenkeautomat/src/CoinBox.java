public class CoinBox {
	protected BottleDispenser bottleDispenser;
	protected int saldo=0;
	private final int BOTTLEPRICE = 2;
	
	public int getBottlePrice() {
	    return BOTTLEPRICE;
	}
	
	public CoinBox(BottleDispenser bottleDispenser) {
	   this.bottleDispenser = bottleDispenser;
	}
	
	public void change(int sum) {
       saldo+=sum;
	   if(saldo>=BOTTLEPRICE){
		   bottleDispenser.lock(false);
	   }
	   else{
		   bottleDispenser.lock(true);
	   }
	}
	
	public int getSaldo() {
		return saldo;
	}
	


}
