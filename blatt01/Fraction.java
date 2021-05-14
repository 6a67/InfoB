public class Fraction {

	private int numerator; // Zähler
	private int denominator; // Nenner

	
	public Fraction(int ganzeZahl) {
		this(ganzeZahl, 1);
	}
	
	public Fraction(int zaehler, int nenner) {
		numerator = zaehler;
		denominator = nenner;
		
		repair();
	}
	
	/**
	 * Methode um den Bruch zu kürzen und ggf. Nenner auf 0 setzt, wenn Zähler 0 ist
	 */
	private void repair() {
		if(denominator == 0) {
			numerator = 0;
		} else {
			int gcd = findGCD(numerator, denominator);
			numerator /= gcd;
			denominator /= gcd;
		}
	}
	
	/**
	 * Euklidischer Algorithmus um den größten gemeinsamen Teiler zweier Zahlen zu finden
	 * 
	 * @param n1 Zahl 1
	 * @param n2 Zahl 2
	 * @return Größter gemeinsamer Teiler
	 */
	private int findGCD(int n1, int n2) {
		if(n2 == 0) {
			return n1;
		} else {
			return findGCD(n2, n1 % n2);
		}
	}
	
	/**
	 * Multipliziert den Bruch mit einer ganzen Zahl
	 * @param factor Ganzer Multiplikand
	 * @return Produkt der Multiplikation
	 */
    public Fraction multiply(int factor) {
    	return new Fraction(this.numerator * factor, this.denominator);
    }
	
	/**
	 * Multipliziert den Bruch mit einem anderen Bruch
	 * @param factor Multiplikand als Bruch
	 * @return Produkt der Multiplikation
	 */
    public Fraction multiply(Fraction factor) {
        return new Fraction(this.numerator * factor.getNumerator(), this.denominator * factor.getDenominator());
    }

    /**
     * Dividiert den Bruch durch einen anderen
     * @param divisor Dividend als Bruch
     * @return Quotient der Division
     */
    public Fraction divide(Fraction divisor) {
        return new Fraction(this.numerator * divisor.getDenominator(), this.denominator * divisor.getNumerator());
    }

	/**
	 * Multipliziert den Bruch mit beliebig vielen weitern Brüchen
	 * @param factors Multiplikanden als Bruch
	 * @return Produkt der Multiplikationen
	 */
	public Fraction multiply(Fraction... factors) {
		Fraction produkt = new Fraction(numerator, denominator);
		
		for(int i = 0; i < factors.length; i++) {
			produkt = produkt.multiply(factors[i]);
		}
		
		return produkt;
	}
	
	
	public int getNumerator() {
		return this.numerator;
	}

	public int getDenominator() {
		return this.denominator;
	}

	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
	
	public boolean equals(Fraction frac) {
		return numerator == frac.getNumerator() && denominator == frac.getDenominator();
	}

}
