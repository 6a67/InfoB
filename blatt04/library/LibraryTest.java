package library;

import util.List;

/**
 * Testklasse zum Testen von Bibliotheken-Operationalität
 */
public class LibraryTest {

    public static void main(String[] args) {
        System.out.println("Starte Test...");
        LibraryTest test = new LibraryTest();
        test.testAddDeleteItem();
        test.searchSingleElement();
        test.searchMultipleElements();
        test.testBorrowed();
        test.testSideEffect();
        System.out.println("Test mit " + TestModules.getAnzahlFehler() + " Fehlern abgeschlossen");
    }

    public void testAddDeleteItem() {
        Library library = new Library();
        LibraryItem avatar = new BluRay("Avatar", "James Cameron");
        LibraryItem taow = new Book("The Art of War", "Sun Tzu");

        library.addItem(avatar);
        TestModules.assertBool(library.getLibraryItems().elem() == avatar, "Hinzufügen eines BluRays fehlgeschlagen");
        library.addItem(taow);
        TestModules.assertBool(library.getLibraryItems().elem() == taow, "Hinzufügen eines Buches fehlgeschlagen");

        library.deleteItem(taow);
        TestModules.assertBool(library.getLibraryItems().elem() != taow, "Löschen eines BluRays fehlgeschlagen");
        library.deleteItem(avatar);
        TestModules.assertBool(library.getLibraryItems().empty(), "Löschen eines BluRays fehlgeschlagen");
    }

    public void searchSingleElement() {
        Library library = new Library();
        LibraryItem avatar = new BluRay("Avatar", "James Cameron");
        LibraryItem taow = new Book("The Art of War", "Sun Tzu");

        library.addItem(avatar);
        library.addItem(taow);

        List resultBluRay = library.search("Avatar");
        int resultCountBluRay = 0;
        while (!resultBluRay.endpos()) {
            if (((LibraryItem)resultBluRay.elem()).getDescription().contains("Avatar")) {
                resultCountBluRay++;
            }
            resultBluRay.advance();
        }
        TestModules.assertBool(resultCountBluRay == 1, "Single Element BluRay-Suche fehlgeschlagen");

        List resultBook = library.search("The Art of War");
        int resultCountBook = 0;
        while (!resultBook.endpos()) {
            if (((LibraryItem)resultBook.elem()).getDescription().contains("The Art of War")) {
                resultCountBook++;
            }
            resultBook.advance();
        }
        TestModules.assertBool(resultCountBook == 1, "Single Element Buch-Suche fehlgeschlagen");
    }

    public void searchMultipleElements() {
        Library library = new Library();
        LibraryItem avatar = new BluRay("Avatar", "James Cameron");
        LibraryItem taow = new Book("The Art of War", "Sun Tzu");
        LibraryItem tlw = new Book("The last Wish", "Andrzej Sapkowski");
        LibraryItem affc = new Book("A Feast for Crows", "George RR Martin");
        LibraryItem inter = new BluRay("Interstellar", "Christopher Nolan");

        library.addItem(avatar);
        library.addItem(taow);
        library.addItem(tlw);
        library.addItem(affc);
        library.addItem(inter);

        List result = library.search("Avatar", "Interstellar", "George RR Martin");
        int resultCount = 0;
        while (!result.endpos()) {
            if (((LibraryItem)result.elem()).getDescription().matches(".*(Avatar|Interstellar|George RR Martin).*")) {
                resultCount++;
            }
            result.advance();
        }
        TestModules.assertBool(resultCount == 3, "Multiple Element Suche fehlgeschlagen");
    }

    public void testBorrowed() {
        LibraryItem avatar = new BluRay("Avatar", "James Cameron");
        LibraryItem taow = new Book("The Art of War", "Sun Tzu");

        avatar.setBorrowed(true);
        TestModules.assertBool(avatar.isBorrowed(), "Ausleihsetzung auf true fehlgeschlagen");
        taow.setBorrowed(false);
        TestModules.assertBool(!taow.isBorrowed(), "Ausleihsetzung auf false fehlgeschlagen");
    }

    public void testSideEffect() {
        Library library = new Library();
        LibraryItem avatar = new BluRay("Avatar", "James Cameron");

        library.addItem(avatar);

        System.out.println("Seiteneffekt Test: ");
        System.out.println("Avatar isBorrowed() Test in Library auf Standardwert false: " +
                ((LibraryItem)library.getLibraryItems().elem()).isBorrowed());
        avatar.setBorrowed(true);
        System.out.println("Avatar durch avatar.setBorrowed() auf true gesetzt. Ergebnis: " + avatar.isBorrowed());


        System.out.println("Avatar isBorrowed() Test auf true in Library durch elem().isBorrowed(): " +
                ((LibraryItem)library.getLibraryItems().elem()).isBorrowed());
        ((LibraryItem)library.getLibraryItems().elem()).setBorrowed(false);
        System.out.println("LibraryItem wurde auf false gesetzt durch elem().setBorrowed()");
        System.out.println("Avatar Test auf true oder false durch avatar.isBorrowed(): " + avatar.isBorrowed());
        System.out.println("Avatar Test auf true oder false durch elem().isBorrowed(): " +
                ((LibraryItem)library.getLibraryItems().elem()).isBorrowed());
    }
}