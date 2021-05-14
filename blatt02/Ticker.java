public class Ticker {

	private static Ticker ticker;
	
	private Ticker() {
		
	}

	/**
	 * Sorgt dafür, dass nur ein Ticker existiert
	 * @return Der einzige Ticker
	 */
	public static Ticker getInstance() {
		if(ticker == null) {
			ticker = new Ticker();
		}
		
		return ticker;
	}

	/**
	 * Sorgt für eine Ausgabe es Tickers
	 * @param text Der Text, welcher ausgegeben wird
	 */
	public void print(String text) {
		text = text.replace("\n", " ");
		System.out.print("+++" + text);
	}
	
}
