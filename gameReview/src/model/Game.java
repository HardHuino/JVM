package model;

import java.util.List;
import java.util.Map;

public class Game {
    // Attributs
    private String name;
    private Genre genre;
    private String publisher;
    private Rating rating;
    private List<Support> supports;


    // Constructeur
    public Game(String name, Genre genre, String publisher, Rating rating) {
        this.name = name;
        this.genre = genre;
        this.publisher = publisher;
        this.rating = rating;
    }

    // Méthodes de gestion des plateformes
    public void addSupport(Support support) {
        supports.add(support);
    }
    public Set<Support> getAvailableSupports();
    public boolean isAvailableOn(Support support);

    // Getters
    public String getName() {
        return name;
    }
    public Genre getGenre() {
        return genre;
    }
    public String getPublisher() {
        return publisher;
    }
    public Rating getRating() {
        return rating;
    }
}
