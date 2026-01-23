package data;

import models.*;
import java.util.*;

public class DataStore {
    // Singleton
    private static DataStore instance;

    // Collections de données
    private Map<String, Game> gamesByName;
    private List<Game> allGames;
    private Map<Integer, Member> membersById;
    private Map<String, Member> membersByPseudo;
    private List<Review> allReviews;
    private List<Test> allTests;

    // Constructeur privé
    private DataStore() {
        this.gamesByName = new HashMap<>();
        this.allGames = new ArrayList<>();
        this.membersById = new HashMap<>();
        this.membersByPseudo = new HashMap<>();
        this.allReviews = new ArrayList<>();
        this.allTests = new ArrayList<>();
    }

    /**
     * Récupère l'instance unique
     */
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    /**
     * Réinitialise le DataStore (utile pour les tests)
     */
    public static void reset() {
        instance = new DataStore();
    }

    // ========== Gestion des jeux ==========

    public void addGame(Game game) {
        if (game != null && !gamesByName.containsKey(game.getName().toLowerCase())) {
            gamesByName.put(game.getName().toLowerCase(), game);
            allGames.add(game);
        }
    }

    public void addGames(List<Game> games) {
        for (Game game : games) {
            addGame(game);
        }
    }

    public Game getGame(String name) {
        return gamesByName.get(name.toLowerCase());
    }

    public List<Game> getAllGames() {
        return new ArrayList<>(allGames);
    }

    public int getGameCount() {
        return allGames.size();
    }

    // ========== Gestion des membres ==========

    public void addMember(Member member) {
        if (member != null) {
            membersById.put(member.getId(), member);
            membersByPseudo.put(member.getPseudo(), member);
        }
    }

    public void removeMember(Member member) {
        if (member != null) {
            membersById.remove(member.getId());
            membersByPseudo.remove(member.getPseudo());
        }
    }

    public Member getMemberById(int id) {
        return membersById.get(id);
    }

    public Member getMemberByPseudo(String pseudo) {
        return membersByPseudo.get(pseudo);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(membersById.values());
    }

    public boolean pseudoExists(String pseudo) {
        return membersByPseudo.containsKey(pseudo);
    }

    public int getMemberCount() {
        return membersById.size();
    }

    // ========== Gestion des reviews ==========

    public void addReview(Review review) {
        if (review != null) {
            allReviews.add(review);
        }
    }

    public Review getReview(int id) {
        for (Review review : allReviews) {
            if (review.getId() == id) {
                return review;
            }
        }
        return null;
    }

    public List<Review> getAllReviews() {
        return new ArrayList<>(allReviews);
    }

    public int getReviewCount() {
        return allReviews.size();
    }

    // ========== Gestion des tests ==========

    public void addTest(Test test) {
        if (test != null) {
            allTests.add(test);
        }
    }

    public Test getTest(int id) {
        for (Test test : allTests) {
            if (test.getId() == id) {
                return test;
            }
        }
        return null;
    }

    public List<Test> getAllTests() {
        return new ArrayList<>(allTests);
    }

    public int getTestCount() {
        return allTests.size();
    }

    // ========== Statistiques globales ==========

    public void printStatistics() {
        System.out.println("=== Statistiques du DataStore ===");
        System.out.println("Jeux: " + getGameCount());
        System.out.println("Membres: " + getMemberCount());
        System.out.println("Reviews: " + getReviewCount());
        System.out.println("Tests: " + getTestCount());
    }
}
