package models;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import exceptions.*;

public abstract class Member extends User {

    // Attributs
    protected String pseudo;
    protected int availableTokens;
    protected Map<Game, Integer> gamePlaytime; // jeu -> heures de jeu
    protected Map<Game, Integer> placedTokens; // jetons placés par jeu
    protected boolean isBlocked;

    // Constructeur
    public Member(String pseudo, UserType type) {
        super(type);  // Le pseudo sert d'ID
        this.pseudo = pseudo;
        this.availableTokens = 3;  // Jetons initiaux
        this.gamePlaytime = new HashMap<>();
        this.placedTokens = new HashMap<>();
        this.isBlocked = false;
    }

    // Méthodes de gestion des jeux
    public void addGame(Game game) {
        if (!ownsGame(game)) {
            gamePlaytime.put(game, 0);
        }
    }
    public void addPlayTime(Game game, int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Le temps de jeu doit être positif");
        }
        if (!ownsGame(game)) {
            addGame(game);
        }
        gamePlaytime.put(game, gamePlaytime.get(game) + hours);;
    }
    public int getPlayTime(Game game) {
        return gamePlaytime.getOrDefault(game, 0);
    }
    public boolean ownsGame(Game game) {
        return gamePlaytime.containsKey(game);
    }

    // A RECHECK
    public Map<Game, Integer> getOwnedGamesSortedByPlayTime() {
        // Trier par temps de jeu décroissant
        List<Map.Entry<Game, Integer>> entries = new ArrayList<>(gamePlaytime.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        Map<Game, Integer> sorted = new LinkedHashMap<>();
        for (Map.Entry<Game, Integer> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }

    public int getTotalPlayTime() {
        int total = 0;
        for (int hours : gamePlaytime.values()) {
            total += hours;
        }
        return total;
    }

    // Méthodes de gestion des jetons
    public void placeTokens(Game game, int amount) throws InsufficientTokensException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le nombre de jetons doit être positif");
        }
        if (availableTokens < amount) {
            throw new InsufficientTokensException(amount, availableTokens);
        }
        int current = placedTokens.getOrDefault(game, 0);
        placedTokens.put(game, current + amount);
        availableTokens -= amount;
    }
    public void removeTokens(Game game, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le nombre de jetons doit être positif");
        }
        int current = placedTokens.getOrDefault(game, 0);
        int toRemove = Math.min(current, amount);
        if (toRemove > 0) {
            placedTokens.put(game, current - toRemove);
            availableTokens += toRemove;
        }
    }
    public int getPlacedTokens(Game game) {
        return placedTokens.get(game);
    }
    public int getAvailableTokens() {
        return availableTokens;
    }
    public void addTokens(int amount) {
        if (amount > 0) {
            availableTokens += amount;
        }
    }
    public void removeTokensAmount(int amount) {
        if (amount > 0) {
            availableTokens = Math.max(0, availableTokens - amount);
        }
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

    // Méthodes héritées de User
    @Override
    public boolean canSearchGames() {
        return !isBlocked;
    }

    @Override
    public boolean canViewReviews() {
        return !isBlocked;
    }

    // Getters
    public String getPseudo() {
        return pseudo;
    }

    public Map<Game, Integer> getGamePlaytime() {
        return new HashMap<>(gamePlaytime);  // Copie défensive
    }

    public Map<Game, Integer> getPlacedTokensMap() {
        return new HashMap<>(placedTokens);  // Copie défensive
    }
}