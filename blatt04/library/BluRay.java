package library;

/**
 * Subklasse von LibraryItem für die Implementierung von BluRays
 */
public class BluRay extends LibraryItem {
    /**
     * Titel des BluRays
     */
    private String title;
    /**
     * Regisseur des BluRays
     */
    private String director;

    /**
     * Konstruktor für ein BluRay
     * @param title Titel
     * @param director Regisseur
     */
    public BluRay(String title, String director) {
        this.title = title;
        this.director = director;
    }

    /**
     * Getter Methode für den Titel
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter Methode für den Regisseur
     * @return director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Implementierung von getDescription() für BluRays
     * @return Beschreibung des BluRay Films
     */
    public String getDescription() {
        return "Der Name des Films ist " + this.getTitle() +
                " und der Regisseur des Films ist " + this.getDirector();
    }
}