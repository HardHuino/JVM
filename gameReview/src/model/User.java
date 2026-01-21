package model;

public abstract class User {
    // Attributs
    protected String id;
    protected UserType type;

    // Constructeur
    public User(String id, UserType type) {
        this.id = id;
        this.type = type;
    }

    // Méthodes
    public abstract boolean canSearchGames();
    public abstract boolean canViewReviews();
    public abstract boolean canWriteReview(Game game, Support support);
    public abstract boolean canWriteTest(Game game, Support support);
    public String getId() {
        return id;
    }
    public UserType getType() {
        return type;
    }
}
