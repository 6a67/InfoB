package library;

/**
 * Subklasse von LibraryItem für die Implementierung von Bücher
 */
public class Book extends LibraryItem {
    /**
     * Titel des Buchs
     */
    private String title;
    /**
     * Author des Buchs
     */
    private String author;

    /**
     * Konstruktor für ein Buch
     * @param title Titel
     * @param author Author
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Getter Methode für den Titel
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter Methode für den Author
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Implementierung von getDescription() für Bücher
     * @return Beschreibung des Buchs
     */
    public String getDescription() {
        return "Der Name des Buches ist " + this.getTitle() +
                " und der Author des Buches ist " + this.getAuthor();
    }
}