public class Calculator {

    /**
     * Calculates the result of two fractions or numbers
     * @param args Fraction1 Operator Fraction2
     */
    public static void main(String[] args) {

        // Checks if there are enough parameters
        if(args.length != 3) {
            System.err.println("Es müssen genau drei Argumente übergeben werden.\nBeispiel: 1/2 - 1/3");
            return;
        }

        // Parses the first fraction and checks if it valid
        Fraction frac1 = Fraction.parseFraction(args[0]);
        if(frac1 == null) {
            System.err.println("Der erste Bruch ist ungültig");
            return;
        }

        // Parses the second fraction and checks if it valid
        Fraction frac2 = Fraction.parseFraction(args[2]);
        if(frac1 == null) {
            System.err.println("Der zweite Bruch ist ungültig");
            return;
        }

        // Calls different functions for different operators
        Fraction result;
        switch(args[1]) {
            case "+":
                result = frac1.add(frac2);
                break;
            case "-":
                result = frac1.substract(frac2);
                break;
            case "*":
                result = frac1.multiply(frac2);
                break;
            case "/":
                result = frac1.divide(frac2);
                break;
            default:
                System.err.println("Ungültiger Operator");
                return;
        }

        System.out.println(result);
    }

}
