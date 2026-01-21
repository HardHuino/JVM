package model;

import java.util.List;
import java.util.Map;

public class Player extends Member {
    // Attributs
    private List<Review> writtenReviews;
    private Map<Review, ReviewRating> givenRatings; // évaluations d'évaluations
    private static final int MIN_PLAYTIME_FOR_REVIEW = 2; // heures

    // Constructeur
    public Player(String pseudo) ();

    // Méthodes d'évaluation
    @Override
    public boolean canWriteReview(Game game, Support support) {
        return super.getPlayTime(game) >= MIN_PLAYTIME_FOR_REVIEW;
    }
    public Review writeReview(Game game, Support support, String text, double score, String version) {
        if (canWriteTest(game, support)) {
            Review review = new Review(this, game, support, text, score, version);
            support.addReview(review);
            writtenReviews.add(review);
            return review;
        }
        return null;
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
        return writtenReviews;
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
    public void updateTokensFromReviewRatings();
}
