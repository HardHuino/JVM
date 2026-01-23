package models;

import java.util.*;

public class Support {
    // Attributs
    private Game game;              // Référence vers le jeu
    private String platform;

    private int releaseYear;
    private String developer;
    private double globalSales;

    private int criticCount;
    private double criticScore;
    private int userCount;
    private double userScore;

    private Test test;
    private List<Review> reviews;

    private int tokensPlaced;

    // Constructeur
    public Support(Game game, String platform, int releaseYear,
                   String developer, double globalSales,
                   int criticCount, double criticScore,
                   int userCount, double userScore) {

        this.game = game;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.developer = developer;
        this.globalSales = globalSales;
        this.criticCount = criticCount;
        this.criticScore = criticScore;
        this.userCount = userCount;
        this.userScore = userScore;

        this.reviews = new ArrayList<>();
        this.tokensPlaced = 0;

        game.addSupport(this);
    }

    // Méthodes de gestion des reviews
    public void addReview(Review review) {
        reviews.add(review);
    }
    public void removeReview(Review review) {
        reviews.remove(review);
    }
    public List<Review> getReviews() {
        return reviews;
    }
    //public List<Review> getSortedReviews();
    public double getAverageReviewScore() {
        double total = 0;
        for (Review review : reviews) {
            total += review.getScore();
        }
        return total / reviews.size();
    }
    public int getReviewCount() {
        return reviews.size();
    }

    // Méthodes de gestion des jetons
    public int getTokensPlaced() {
        return tokensPlaced;
    }
    public void addTokens(int amount) {
        tokensPlaced += amount;
    }
    public void removeTokens(int amount) {
        tokensPlaced -= amount;
    }
    public void releaseTokens() {
        tokensPlaced = 0;
    }

    // Méthodes de gestion des tests
    public void setTest(Test test) {
        if (hasTest())
            throw new IllegalStateException("Un test existe déjà pour ce support");
        this.test = test;
    }
    public Test getTest() {
        return this.test;
    }
    public boolean hasTest() {
        return test!=null;
    }

    // Getters
    public Game getGame() {
        return game;
    }
    public String getPlatform() {
        return platform;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public String getDeveloper() {
        return developer;
    }
    public double getGlobalSales() {
        return globalSales;
    }
    public int getCriticCount() {
        return criticCount;
    }
    public double getCriticScore() {
        return criticScore;
    }
    public int getUserCount() {
        return userCount;
    }
    public double getUserScore() {
        return userScore;
    }
}
