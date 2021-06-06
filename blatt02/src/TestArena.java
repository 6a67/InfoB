/**
 * Klasse zum Testen der Arena
 */
public class TestArena {

    public static void main(String[] args) {
        System.out.println("Starting test...");
        TestArena test = new TestArena();
        test.testAll();
        System.out.println("Done. Mit " + TestModules.getAnzahlFehler() + " Fehlern.");
    }

    /**
     * Überprüft, ob sich die Kooradianten außerhalb der Arena befinden
     */
    public void testOutOfArea() {
        Arena arena = new Arena();
        TestModules.assertBool(arena.getArea(1.5, 0.01) == -1, "Fehler in testOutOfArea");
    }

    /**
     * Überprüft die Spezialfälle
     */
    public void testSpecialCases() {
        Arena arena = new Arena();
        TestModules.assertBool(arena.getArea(0, 1.5) == 1, "Fehler in testSpecialCases");
        TestModules.assertBool(arena.getArea(1.5, 0) == 4, "Fehler in testSpecialCases");
        TestModules.assertBool(arena.getArea(0, -1.5) == 7, "Fehler in testSpecialCases");
        TestModules.assertBool(arena.getArea(-1.5, 0) == 10, "Fehler in testSpecialCases");
    }

    /**
     * Überprüft, ob jeder der 12 Bereiche funktioniert
     */
    public void testEachArea() {
        Arena arena = new Arena();
        TestModules.assertBool(arena.getArea(0.1, 1.3) == 1, "Fehler bei Area 1");
        TestModules.assertBool(arena.getArea(0.75, 0.75) == 2, "Fehler bei Area 2");
        TestModules.assertBool(arena.getArea(1.3, 0.1) == 3, "Fehler bei Area 3");
        TestModules.assertBool(arena.getArea(1.3, -0.1) == 4, "Fehler bei Area 4");
        TestModules.assertBool(arena.getArea(0.75, -0.75) == 5, "Fehler bei Area 5");
        TestModules.assertBool(arena.getArea(0.1, -1.3) == 6, "Fehler bei Area 6");
        TestModules.assertBool(arena.getArea(-0.1, -1.3) == 7, "Fehler bei Area 7");
        TestModules.assertBool(arena.getArea(-0.75, -0.75) == 8, "Fehler bei Area 8");
        TestModules.assertBool(arena.getArea(-1.3, -0.1) == 9, "Fehler bei Area 9");
        TestModules.assertBool(arena.getArea(-1.3, 0.1) == 10, "Fehler bei Area 10");
        TestModules.assertBool(arena.getArea(-0.75, 0.75) == 11, "Fehler bei Area 11");
        TestModules.assertBool(arena.getArea(-0.1, 1.3) == 12, "Fehler bei Area 12");

    }

    public void testAll() {
        this.testOutOfArea();
        this.testSpecialCases();
        this.testEachArea();
    }

}
