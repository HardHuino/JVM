package services;

import models.*;
import exceptions.*;

import java.util.*;

public class manageGames {
    // Attributs
    private List<Game> games;

    // Constructeur
    public manageGames() {
        this.games = new ArrayList<>();
    }

    // Méthodes de recherche
    public Game searchByName(String name) throws GameNotFoundException {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        throw new GameNotFoundException(name);
    }
    public List<Game> searchByGenre(String genre) {
        List<Game> result = new ArrayList<>();
        for (Game game : games) {
            if (game.getGenre().equalsIgnoreCase(genre)) { // equals spécial pour eviter les differences de casse
                result.add(game);
            }
        }
        return result;
    }
    public List<Game> searchByPublisher(String publisher) {
        List<Game> result = new ArrayList<>();
        for (Game game : games) {
            if (game.getPublisher().equals(publisher)) {
                result.add(game);
            }
        }
        return result;
    }
    public List<Game> searchByPlatform(Support support) {
        List<Game> result = new ArrayList<>();
        for (Game game : games) {
            if (game.getAvailableSupports().contains(support)) {
                result.add(game);
            }
        }
        return result;
    }
    //public List<Game> searchByCriteria(Map<String, Object> criteria);

    // Méthodes de consultation
    public Game getGame(String name) throws GameNotFoundException {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        throw new GameNotFoundException(name);
    }
    public void displayGameInfo(Game game, String platform) throws SupportNotFoundException {
        Support support = game.getSupport(platform);
        if (support == null) {
            throw new SupportNotFoundException(game.getName(), platform);
        }

        System.out.println("=== " + game.getName() + " ===");
        System.out.println("Genre: " + game.getGenre());
        System.out.println("Éditeur: " + game.getPublisher());
        System.out.println("Classification: " + game.getRating());
        System.out.println("\n--- Informations " + platform + " ---");
        System.out.println("Année de sortie: " + support.getReleaseYear());
        System.out.println("Développeur: " + support.getDeveloper());
        System.out.println("Ventes mondiales: " + support.getGlobalSales() + "M");
        System.out.println("Score critiques: " + support.getCriticScore() + "/100 (" +
                support.getCriticCount() + " critiques)");
        System.out.println("Score utilisateurs: " + support.getUserScore() + "/10 (" +
                support.getUserCount() + " évaluations)");
        System.out.println("\nJetons placés pour un test: " + support.getTokensPlaced());
        System.out.println("Test disponible: " + (support.hasTest() ? "Oui" : "Non"));
        System.out.println("Nombre d'évaluations: " + support.getReviewCount());
    }

    // Méthodes de gestion
    public void addGame(Game game) {
        if (!games.contains(game)) {
            games.add(game);
        }
    }
    public List<Game> getAllGames() {
        return games;
    }
    public List<Game> getGamesSortedByTokens() {
        List<GameTokenPair> pairs = new ArrayList<>();

        for (Game game : games) {
            int maxTokens = 0;
            for (Support support : game.getAvailableSupports()) {
                maxTokens = Math.max(maxTokens, support.getTokensPlaced());
            }
            pairs.add(new GameTokenPair(game, maxTokens));
        }

        // Trier par nombre de jetons décroissant
        pairs.sort((p1, p2) -> Integer.compare(p2.tokens, p1.tokens));

        List<Game> result = new ArrayList<>();
        for (GameTokenPair pair : pairs) {
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
