package antRace;


public class AntRace implements AntFields {

	public static void main(String[] args) throws InterruptedException {

		AntField field = new AntField(FIELD4);

		Ant ant = new Ant(field, 2, 4, 1);

		new Thread(ant).start();

		// Wait until there are no working ants anymore
		// The removeAnt methode will notify if there are no working ants anymore
		synchronized (field) {
			field.wait();
		}

		System.out.println(field.toString());

	}
}
