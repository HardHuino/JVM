package models;

import exceptions.*;
import java.util.*;

public class Game {
    // Attributs
    private String name;
    private String genre;
    private String publisher;
    private String rating;

    private List<Support> supports;

    // Constructeur
    public Game(String name, String genre, String publisher, String rating) {
        this.name = name;
        this.genre = genre;
        this.publisher = publisher;
        this.rating = rating;
        this.supports = new ArrayList<>();
    }

    // Méthodes de gestion des plateformes
    public void addSupport(Support support) {
        supports.add(support);
    }
    public Collection<Support> getAvailableSupports() {
        return supports;
    }
    public boolean isAvailableOn(Support support) {
        return supports.contains(support);
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getRating() {
        return rating;
    }

    public Support getSupport(String platform) throws SupportNotFoundException {
        for (Support support : supports) {
            if (support.getPlatform().equals(platform)) {
                return support;
            }
        }
        throw new SupportNotFoundException(getName(), platform);
    }
}
