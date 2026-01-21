package model;

public class Guest extends User {
    // Constructeur
    public Guest();

    // Méthodes
    @Override
    public boolean canSearchGames();
    @Override
    public boolean canViewReviews();
    @Override
    public boolean canWriteReview(Game game, Support support);
    @Override
    public boolean canWriteTest(Game game, Support support);
}
