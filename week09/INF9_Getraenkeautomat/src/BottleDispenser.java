public class BottleDispenser {
	protected CoinBox  coinBox;
	protected boolean locked = true;
	
	public void setCoinBox(CoinBox coinBox) {
		this.coinBox = coinBox;
	}
	
	public void lock(boolean b) {
		locked = b;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void dispense() {
        //dispense
		coinBox.change(-coinBox.getBottlePrice());
	}
}
