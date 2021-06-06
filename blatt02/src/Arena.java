/**
 * Stellt eine künstliche kreisförmige Arena dar, in dem es 12 Abschnitte mit jeweils 30 Grad Abstand gibt.
 * Der Durchmesser beträgt 3 Meilen.
 */
public class Arena {

	public Arena() {
		
	}

	/**
	 * Ermittelt in welchem Teil der Arena sich die Koordinaten befinden und gibt diesen Teil zurück.
	 * -1 bei Koordinaten außerhalb der Arena
	 * @param x x Koordinate
	 * @param y y Koordinate
	 * @return Teil der Arena
	 */
	public int getArea(double x, double y) {

		double laenge = Math.sqrt(x*x + y*y);
		
		if(laenge > 1.5) {
			return -1;
		}

		double winkel = Math.acos((1.5*y)/(1.5*laenge)) * (180/Math.PI);
		if(x < 0) winkel = 360 - winkel;
		return ((int) winkel / 30) + 1;
	}

}
