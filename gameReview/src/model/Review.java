package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Review {
    // Attributs
    private String id;
    private User author;
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
        this.author = author;
        this.game = game;
        this.support = support;
        this.text = text;
        this.score = score;
        this.version = version;
        this.ratings = new HashMap<>();
        this.isDeleted = false;
        this.isReported = false;
        this.reportReason = "";

    }

    // Méthodes de gestion des évaluations
    public void addRating(Player player, ReviewRating rating);
    public ReviewRating getRating(Player player);
    public int getPositiveCount();
    public int getNeutralCount();
    public int getNegativeCount();
    public double getUtilityScore();

    // Méthodes de modération
    public void report(String reason);
    public void delete();
    public boolean isDeleted();
    public boolean isReported();
    public String getReportReason();

    // Getters
    public String getId();
    public Player getAuthor();
    public VideoGame getGame();
    public Support getSupport();
    public LocalDate getDate();
    public String getText();
    public double getScore();
    public String getVersion();
}