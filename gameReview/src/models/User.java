package models;

public abstract class User {
    // Générateur d'ID unique pour tous les utilisateurs
    private static int nextId = 1;

    // Attributs
    protected int id;
    protected UserType type;

    // Constructeur
    protected User(UserType type) {
        this.id = nextId++;
        this.type = type;
    }

    // Méthodes
    public abstract boolean canSearchGames();
    public abstract boolean canViewReviews();
    public abstract boolean canWriteReview(Game game, Support support);
    public abstract boolean canWriteTest(Game game, Support support);

    // Getters
    public int getId() {
        return id;
    }
    public UserType getType() {
        return type;
    }
}
