package services;

import models.*;
import exceptions.*;
import java.util.*;
import java.util.stream.Collectors;

public class manageReviews {
    // Attributs
    private List<Review> allReviews;

    // Constructeur
    public manageReviews() {
        this.allReviews = new ArrayList<>();
    }

    // ========== Méthodes de création ==========

    public Review createReview(Player author, Support support,
                               String text, double score, String version)
            throws InsufficientPlayTimeException, UnauthorizedActionException {

        if (support == null) {
            throw new UnauthorizedActionException("Support invalide");
        }

        if (!author.canWriteReview(support.getGame(), support)) {
            int playTime = author.getPlayTime(support.getGame());
            throw new InsufficientPlayTimeException(support.getGame().getName(), 2, playTime);
        }

        Review review = author.writeReview(support.getGame(), support, text, score, version);
        if (review != null) {
            allReviews.add(review);
        }

        return review;
    }

    // ========== Méthodes de consultation ==========

    public List<Review> getReviewsForSupport(Support support) {
        if (support == null) {
            return new ArrayList<>();
        }
        return support.getReviews();
    }

    public List<Review> getSortedReviews(Support support) {
        if (support == null) {
            return new ArrayList<>();
        }

        List<Review> reviews = new ArrayList<>(support.getReviews());

        // Trier par utilité (score positif - négatif) décroissant, puis par date croissante
        reviews.sort((r1, r2) -> {
            double util1 = r1.getUtilityScore();
            double util2 = r2.getUtilityScore();

            if (util1 != util2) {
                return Double.compare(util2, util1);  // Décroissant
            }

            // Si même utilité, trier par date croissante (plus ancien d'abord)
            return r1.getDate().compareTo(r2.getDate());
        });

        // Filtrer les reviews supprimées
        return reviews.stream()
                .filter(r -> !r.isDeleted())
                .collect(Collectors.toList());
    }

    public Review getReview(int id) throws ReviewNotFoundException {
        for (Review review : allReviews) {
            if (review.getId() == id) {
                return review;
            }
        }
        throw new ReviewNotFoundException(id);
    }

    // ========== Méthodes d'évaluation ==========

    public void rateReview(Player rater, Review review, ReviewRating rating)
            throws UnauthorizedActionException {
        if (rater.isBlocked()) {
            throw new UnauthorizedActionException("Utilisateur bloqué");
        }

        if (review.getAuthor().getId() == rater.getId()) {
            throw new UnauthorizedActionException("Impossible d'évaluer sa propre review");
        }

        rater.rateReview(review, rating);

        // Mettre à jour les jetons du joueur qui a écrit la review
        Player author = review.getAuthor();
        author.updateTokensFromReviewRatings();
    }

    // ========== Méthodes de modération ==========

    public void deleteReview(int reviewId, Admin admin)
            throws ReviewNotFoundException, UnauthorizedActionException {
        if (admin == null) {
            throw new UnauthorizedActionException("Seul un Admin peut supprimer des reviews");
        }

        Review review = getReview(reviewId);
        admin.deleteReview(review);
    }

    public void reportReview(Review review, Tester tester, String reason)
            throws UnauthorizedActionException {
        if (tester == null) {
            throw new UnauthorizedActionException("Seul un Tester peut signaler des reviews");
        }

        tester.reportReview(review, reason);
    }

    public List<Review> getReportedReviews() {
        return allReviews.stream()
                .filter(r -> r.isReported() && !r.isDeleted())
                .collect(Collectors.toList());
    }

    // ========== Méthodes de statistiques ==========

    public double calculateAverageScore(Support support) {
        if (support == null) {
            return 0.0;
        }

        List<Review> reviews = support.getReviews().stream()
                .filter(r -> !r.isDeleted())
                .collect(Collectors.toList());

        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getScore();
        }

        return sum / reviews.size();
    }

    public List<Review> getAllReviews() {
        return new ArrayList<>(allReviews);
    }

    public int getTotalReviewCount() {
        return allReviews.size();
    }

    public int getActiveReviewCount() {
        return (int) allReviews.stream()
                .filter(r -> !r.isDeleted())
                .count();
    }
}