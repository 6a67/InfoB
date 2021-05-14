public class TestModules {

    private static int anzahlFehler;

    public static void assertBool(boolean toTest, String errMsg) {
        if(!toTest) {
            System.out.println(errMsg);
            anzahlFehler++;
        }
    }

    public static int getAnzahlFehler() {
        return anzahlFehler;
    }

}
