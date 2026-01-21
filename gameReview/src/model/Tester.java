package model;

import java.util.List;

public class Tester extends Player {
    // Attributs
    private List<Test> writtenTests;
    private static final int MIN_PLAYTIME_FOR_TEST = 10; // heures
    private static final int TOKENS_PER_TEST = 5;

    // Constructeur
    public Tester(String pseudo);

    // Méthodes de test
    @Override
    public boolean canWriteTest(Game game, Support support);
    public Test writeTest(Game game, Support support, String text, Map<String, Double> categoryScores, String version);
    public List<Test> getWrittenTests();
    public int getTestCount();

    // Méthodes de signalement
    public void reportReview(Review review, String reason);
    public List<Review> getReportedReviews();

    // Recherche de jeux à tester
    public List<Game> findGamesToTest(Support gameService);
}