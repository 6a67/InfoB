/**
 * Stellt eine Firma mit einem Namen dar. Diese kann an den Ticker weitergeben, wenn sich ihr Stock Price ändert.
 */
public class Company {

	private String name;
	private static int anzahlAnObjekten = 0;

	public Company(String name) {
		this.name = name;
		anzahlAnObjekten++;
	}

	/**
	 * Gibt über den Ticker den neuen Stock Price aus
	 * @param d Neuer Stock Price
	 */
	public void changeStockPrice(double d) {
		Ticker.getInstance().print("Der Aktienkurs von " + name + " ändert sich auf " + d);
	}
	
	protected void finalize() {
		Ticker.getInstance().print("Die Firma " + name + " ist insolvent");
		anzahlAnObjekten--;
	}

	public static int getAnzahlAnObjekten() {
		return anzahlAnObjekten;
	}
	
}
