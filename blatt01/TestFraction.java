public class TestFraction {

	private int anzahlFehler;
	
	public static void main(String[] args) {
		System.out.println("Start");
		TestFraction test = new TestFraction();
		test.testAll();
		System.out.println("Found " + test.getAnzahlFehler() + " Errors\nDone");
	}
	
	public int getAnzahlFehler() {
		return anzahlFehler;
	}
	
	public void assertBool(boolean toTest, String errMsg) {
		if(!toTest) {
			System.out.println(errMsg);
			this.anzahlFehler++;
		}
	}
	
	public void testGanzeZahl() {
		Fraction testFrac = new Fraction(3);
		assertBool(testFrac.equals(new Fraction(3, 1)), "Fehler in testGanzeZahl");
	}
	
	public void testSimplification() {
		Fraction testFrac = new Fraction(123, 12);
		assertBool(testFrac.equals(new Fraction(41, 4)), "Fehler in testSimplification");
	}
	
	public void testMultiplyInt() {
		Fraction testFrac = new Fraction(13, 3);
		assertBool(testFrac.multiply(5).equals(new Fraction(65, 3)), "Fehler in testMultiplyInt");
	}
	
	public void testMultiplyFrac() {
		Fraction testFrac = new Fraction(13, 6);
		assertBool(testFrac.multiply(9).equals(new Fraction(39, 2)), "Fehler in testMultiplyFrac");
	}
	
	public void testDivide() {
		Fraction testFrac = new Fraction(16);
		assertBool(testFrac.divide(new Fraction(5)).equals(new Fraction(16, 5)), "Fehler in testDivide");
	}
	
	public void testMultiplyMultFracs() {
		Fraction testFrac = new Fraction(1, 2);
		assertBool(testFrac.multiply(new Fraction(3), new Fraction(6, 5), new Fraction(7)).equals(new Fraction(63, 5)), "Fehler in testMultiplyMultFracs");
	}

	public void testAll() {
		testGanzeZahl();
		testSimplification();
		testMultiplyInt();
		testMultiplyFrac();
		testDivide();
		testMultiplyMultFracs();
	}

}
