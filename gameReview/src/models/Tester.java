package models;

import java.util.*;

public class Tester extends Player {
    // Attributs
    private List<Test> writtenTests;
    private List<Review> reportedReviews;
    private static final int MIN_PLAYTIME_FOR_TEST = 10; // heures
    private static final int TOKENS_PER_TEST = 5;

    // Constructeur
    public Tester(String pseudo) {
        super(pseudo);
        this.type = UserType.TESTER;  // Mise à jour du type
        this.writtenTests = new ArrayList<>();
        this.reportedReviews = new ArrayList<>();
    }

    // Méthodes de test
    @Override
    public boolean canWriteTest(Game game, Support support) {
        if (isBlocked) {
            return false;
        }
        // Vérifier que le testeur possède le jeu et a joué suffisamment
        if (!ownsGame(game) || getPlayTime(game) < MIN_PLAYTIME_FOR_TEST) {
            return false;
        }
        // Vérifier qu'il n'y a pas déjà un test pour ce jeu/support
        return !support.hasTest();
    }
    public Test writeTest(Game game, Support support, String text,
                          Map<String, Double> categoryScores, String version) {
        if (!canWriteTest(game, support)) {
            return null;
        }

        Test test = new Test(this, game, support, text, categoryScores, version);
        writtenTests.add(test);
        support.setTest(test);

        // Gagner des jetons pour avoir publié un test
        addTokens(TOKENS_PER_TEST);

        // Libérer les jetons placés sur ce jeu
        support.releaseTokens();

        return test;
    }
    public List<Test> getWrittenTests() {
        return new ArrayList<>(writtenTests);  // Copie défensive
    }

    public int getTestCount() {
        return writtenTests.size();
    }

    // Méthodes de signalement
    public void reportReview(Review review, String reason) {
        if (isBlocked) {
            return;
        }

        review.report(reason);
        if (!reportedReviews.contains(review)) {
            reportedReviews.add(review);
        }
    }
    public List<Review> getReportedReviews() {
        return new ArrayList<>(reportedReviews);  // Copie défensive
    }

    // Recherche de jeux à tester (nécessite GameService, à implémenter dans le service)
    public List<Game> findGamesToTest(List<Game> allGames) {
        List<GameTokenPair> gamesWithTokens = new ArrayList<>();
        for (Game game : allGames) {
            // Vérifier si le testeur possède le jeu et peut le tester
            if (ownsGame(game) && getPlayTime(game) >= MIN_PLAYTIME_FOR_TEST) {
                for (Support support : game.getAvailableSupports()) {
                    if (!support.hasTest()) {
                        int tokens = support.getTokensPlaced();
                        gamesWithTokens.add(new GameTokenPair(game, tokens));
                        break;  // Un seul support sans test suffit
                    }
                }
            }
        }
        // Trier par nombre de jetons décroissant
        gamesWithTokens.sort((g1, g2) -> Integer.compare(g2.tokens, g1.tokens));

        List<Game> result = new ArrayList<>();
        for (GameTokenPair pair : gamesWithTokens) {
            result.add(pair.game);
        }

        return result;
    }

    // Classe interne pour le tri
    private static class GameTokenPair {
        Game game;
        int tokens;

        GameTokenPair(Game game, int tokens) {
            this.game = game;
            this.tokens = tokens;
        }
    }
}