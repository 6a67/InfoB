package library;

/**
 * Abstrakte Klasse für die Inhalte einer Bibliothek
 */
public abstract class LibraryItem {
    /**
     * Variable zur Feststellung ob ein Item ausgeliehen ist
     */
    private boolean isBorrowed = false;
    /**
     * Variable zur Speicherung in welcher Bibliothek die Items sind
     */
    private Library library;

    public LibraryItem() {

    }

    /**
     * Setter Methode für die Library
     */
    public void setLibrary(Library library){
        this.library = library;
    }

    /**
     * Check ob ein Item ausgeliehen ist
     * @return boolean isBorrowed
     */
    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    /**
     * Setter Methode für isBorrowed
     * @param isBorrowed true when ausgeliehen
     */
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    /**
     * Abstrakte Methode um die Beschreibung der jeweiligen Items auszugeben
     * @return Beschreibung des Items
     */
    public abstract String getDescription();

    /**
     * Getter Methode für die Library
     * @return library
     */
    public Library getLibrary() {
        return library;
    }
}