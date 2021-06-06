/**
 * Testet den Constructor und Destructor von Company
 */
public class TestCompany {

	
	public static void main(String[] args) {
		System.out.println("Starting test...");
		TestCompany test = new TestCompany();
		test.testConstructorAndDestructor();
		System.out.println("\nDone. Mit " + TestModules.getAnzahlFehler() + " Fehlern.");
	}

	/**
	 * Testet zuerst den Constructor und dann den Destructor
	 */
	public void testConstructorAndDestructor() {
		Company comp1 = new Company("Name der ersten Firma");
		Company comp2 = new Company("Name der zweiten Firma");
		Company comp3 = new Company("Name der dritten Firma");
		TestModules.assertBool(Company.getAnzahlAnObjekten() == 3, "Fehler in beim Constructor");

		comp1 = null;
		comp2 = null;
		comp3 = null;
		System.gc();


		for(int i = 0; i < 5 && Company.getAnzahlAnObjekten() != 0; i++) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		TestModules.assertBool(Company.getAnzahlAnObjekten() == 0, "Fehler in beim Destructor");

	}


}
