package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Review {
    // Générateur d'ID unique
    private static int nextId = 1;

    // Attributs
    private int id;
    private Player author;
    private Game game;
    private Support support;
    private LocalDate date;
    private String text;
    private double score;
    private String version;
    private Map<Player, ReviewRating> ratings;
    private boolean isDeleted;
    private boolean isReported;
    private String reportReason;

    // Constructeur
    public Review(Player author, Game game, Support support, String text, double score, String version) {
        this.id = nextId++;  // ID auto-généré
        this.author = author;
        this.game = game;
        this.support = support;
        this.text = text;
        this.score = score;
        this.version = version;
        this.date = LocalDate.now();  // Date auto-générée
        this.ratings = new HashMap<>();
        this.isDeleted = false;
        this.isReported = false;
        this.reportReason = "";
    }

    // Méthodes de gestion des évaluations
    public void addRating(Player player, ReviewRating rating) {
        ratings.put(player, rating);
    }
    public ReviewRating getRating(Player player) {
        return ratings.get(player);
    }
    public int getPositiveCount() {
        int count = 0;
        for (ReviewRating rating : ratings.values()) {
            if (rating==ReviewRating.POSITIVE) {
                count++;
            }
        }
        return count;
    }
    public int getNeutralCount() {
        int count = 0;
        for (ReviewRating rating : ratings.values()) {
            if (rating==ReviewRating.NEUTRAL) {
                count++;
            }
        }
        return count;
    }
    public int getNegativeCount() {
        int count = 0;
        for (ReviewRating rating : ratings.values()) {
            if (rating==ReviewRating.NEGATIVE) {
                count++;
            }
        }
        return count;
    }
    public double getUtilityScore() {
        return getPositiveCount() - getNegativeCount();
    }

    // Méthodes de modération
    public void report(String reason) {
        this.reportReason = reason;
        this.isReported = true;
    }
    public void delete() {
        this.isDeleted = true;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public boolean isReported() {
        return isReported;
    }
    public String getReportReason() {
        return reportReason;
    }

    // Getters
    public int getId() {
        return id;
    }
    public Player getAuthor() {
        return author;
    }
    public Game getGame() {
        return game;
    }
    public Support getSupport() {
        return support;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public double getScore() {
        return score;
    }
    public String getVersion() {
        return version;
    }
    public Map<Player, ReviewRating> getRatings() {
        return new HashMap<>(ratings);  // Copie défensive
    }
}