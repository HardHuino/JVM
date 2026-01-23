package models;

import java.util.*;

public class Player extends Member {
    // Attributs
    private List<Review> writtenReviews;
    private Map<Review, ReviewRating> givenRatings; // évaluations d'évaluations
    private static final int MIN_PLAYTIME_FOR_REVIEW = 2; // heures

    // Constructeur
    public Player(String pseudo) {
        super(pseudo, UserType.PLAYER);
        this.writtenReviews = new ArrayList<>();
        this.givenRatings = new HashMap<>();
    }

    // Méthodes d'évaluation
    @Override
    public boolean canWriteReview(Game game, Support support) {
        if (isBlocked) {
            return false;
        }
        return ownsGame(game) && getPlayTime(game) >= MIN_PLAYTIME_FOR_REVIEW;
    }
    @Override
    public boolean canWriteTest(Game game, Support support) {
        return false;  // Les joueurs ne peuvent pas écrire de tests
    }
    public Review writeReview(Game game, Support support, String text, double score, String version) {
        if (!canWriteReview(game, support)) {
            return null;
        }

        Review review = new Review(this, game, support, text, score, version);
        writtenReviews.add(review);
        support.addReview(review);

        return review;
    }
    public void rateReview(Review review, ReviewRating rating) {
        review.addRating(this,rating);
        givenRatings.put(review, rating);
    }
    public ReviewRating getRatingForReview(Review review) {
        return givenRatings.get(review);
    }

    // Méthodes de statistiques
    public int getReviewCount() {
        return writtenReviews.size();
    }
    public List<Review> getWrittenReviews() {
        return new ArrayList<>(writtenReviews);  // Copie défensive
    }
    public int getPositiveRatingsCount() {
        int count = 0;
        for (ReviewRating reviewRating : givenRatings.values()) {
            if (reviewRating == ReviewRating.POSITIVE) {
                count++;
            }
        }
        return count;
    }
    public int getNeutralRatingsCount() {
        int count = 0;
        for (ReviewRating reviewRating : givenRatings.values()) {
            if (reviewRating == ReviewRating.NEUTRAL) {
                count++;
            }
        }
        return count;
    }
    public int getNegativeRatingsCount() {
        int count = 0;
        for (ReviewRating reviewRating : givenRatings.values()) {
            if (reviewRating == ReviewRating.NEGATIVE) {
                count++;
            }
        }
        return count;
    }

    // Calcul des jetons gagnés
    public void updateTokensFromReviewRatings() {
        int positiveCount = getPositiveRatingsCount();
        int tokensEarned = positiveCount / 10;  // 1 jeton par groupe de 10 évaluations positives

        // Calculer combien de jetons ont déjà été accordés
        int currentTokensFromReviews = availableTokens - 3;  // Soustraire les jetons initiaux

        // Ajouter uniquement les nouveaux jetons
        if (tokensEarned > currentTokensFromReviews) {
            addTokens(tokensEarned - currentTokensFromReviews);
        }
    }
}
