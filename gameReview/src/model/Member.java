package model;

import java.util.*;

public abstract class Member extends User {
    // Attributs
    protected String pseudo;
    protected int availableTokens;
    protected Map<Game, Integer> ownedGames; // jeu -> heures de jeu
    protected Map<Game, Integer> placedTokens; // jetons placés par jeu
    protected boolean isBlocked;

    // Constructeur
    public Member(String pseudo, UserType type) {
        this.pseudo = pseudo;
        this.availableTokens = 0;
        this.ownedGames = new HashMap<>();
        this.placedTokens = new HashMap<>();
        this.isBlocked = false;
    }

    // Méthodes de gestion des jeux
    public void addGame(Game game) {
        ownedGames.put(game, 0);
    }
    public void addPlayTime(Game game, int hours) {
        ownedGames.put(game, ownedGames.get(game) + hours);;
    }
    public int getPlayTime(Game game) {
        return ownedGames.get(game);
    }
    public boolean ownsGame(Game game) {
        return ownedGames.containsKey(game);
    }
    //public Map<Game, Integer> getOwnedGamesSortedByPlayTime() {}
    //public int getTotalPlayTime() {}

    // Méthodes de gestion des jetons
    public void placeTokens(Game game, int amount) throws InsufficientTokensException {
        placedTokens.put(game, ownedGames.get(game) + amount);
    }
    public void removeTokens(Game game, int amount) {
        ownedGames.put(game, ownedGames.get(game) - amount);
    }
    public int getPlacedTokens(Game game) {
        return placedTokens.get(game);
    }
    public int getAvailableTokens() {
        return availableTokens;
    }
    public void addTokens(int amount) {
        availableTokens += amount;
    }
    public void removeTokensAmount(int amount) {
        availableTokens -= amount;
    }

    // Méthodes de blocage
    public void block() {
        isBlocked = true;
    }
    public void unblock() {
        isBlocked = false;
    }
    public boolean isBlocked() {
        return isBlocked;
    }

    // Getters
    public String getPseudo() {
        return pseudo;
    }
    public Map<Game, Integer> getOwnedGames() {
        return ownedGames;
    }
}