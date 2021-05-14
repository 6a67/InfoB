public class ParameterX {
	/**
	 * Überprüft die Eingabe des Nutzers auf Gültigkeit
	 * @param input Eingabeargumente
	 * @return Gültigkeit der Eingabeargumente
	 */
	private static boolean checkInput(String[] input) {
		return input.length == 1 && Integer.parseInt(input[0]) > 0;
	}
		
	
	public static void main(String[] args) {
		if(checkInput(args)) {
			for(int i = 1; i <= Integer.parseInt(args[0]); i++) {
				System.out.println(i);
			}
		} else {
			System.out.println("Ungültige Eingabe");
			return;
		}
	}

}