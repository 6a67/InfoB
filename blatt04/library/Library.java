package library;

import util.List;

/**
 * Darstellung einer Bibliothek, welche mit Bücher oder BluRays gefüllt werden kann
 */
public class Library {
    /**
     * Liste der gesamten in der Bibliothek vorhandenen LibraryItems
     */
    private List libraryItems;

    public Library() {
        libraryItems = new List();
    }

    /**
     * Fügt ein Item der Klasse LibraryItem der Bibliothek hinzu
     * @param item Ein LibraryItem, kann ein Buch oder eine BluRay sein
     */
    public void addItem(LibraryItem item) {
        if (item.getLibrary() != null && item.getLibrary() != this) {
            throw new RuntimeException("Item ist schon Teil einer anderen Library");
        }

        item.setLibrary(this);
        this.libraryItems.add(item);
    }

    /**
     * Löscht ein LibraryItem aus der Bibliothek
     * @param item Ein LibraryItem, kann ein Buch oder eine BluRay sein
     */
    public void deleteItem(LibraryItem item) {
        libraryItems.reset();
        while(libraryItems.elem() != item && !libraryItems.endpos()) {
            libraryItems.advance();
        }

        item.setLibrary(null);
        libraryItems.delete();
    }

    /**
     * Überprüft ob das übergebene String array in der LibraryItems Liste vorhanden ist
     * @param text String array nach dem auf übereinstimmung in der Liste der LibraryItems gesucht werden soll
     * @return Objekte als Liste, in dem es eine übereinstimmung mit dem String array gab
     */
    public List search(String...text) {
        List matchingLibraryItems = new List();
        libraryItems.reset();

        while (!libraryItems.endpos()) {
            for (int i = 0; i < text.length; i++){
                if (((LibraryItem)libraryItems.elem()).getDescription().contains(text[i])) {
                    matchingLibraryItems.add(libraryItems.elem());
                }
            }
            libraryItems.advance();
        }

        return matchingLibraryItems;
    }

    /**
     * Getter Methode für die libraryItems Liste
     * @return libraryItems
     */
    public List getLibraryItems() {
        return libraryItems;
    }
}