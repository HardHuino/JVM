package models;

public class Guest extends User {

    // Constructeur
    public Guest() {
        super(UserType.GUEST);
    }

    // Méthodes - permissions limitées
    @Override
    public boolean canSearchGames() {
        return true;  // Les invités peuvent chercher des jeux
    }

    @Override
    public boolean canViewReviews() {
        return true;  // Les invités peuvent consulter des évaluations
    }

    @Override
    public boolean canWriteReview(Game game, Support support) {
        return false;  // Les invités ne peuvent pas écrire d'évaluations
    }

    @Override
    public boolean canWriteTest(Game game, Support support) {
        return false;  // Les invités ne peuvent pas écrire de tests
    }
}