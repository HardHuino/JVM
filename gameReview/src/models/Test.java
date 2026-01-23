package models;

import java.time.LocalDate;
import java.util.*;

public class Test {
    // Générateur d'ID unique
    private static int nextId = 1;

    // Attributs
    private int id;
    private Tester author;
    private Game game;
    private Support support;
    private LocalDate date;
    private String text;
    private String version;
    private Map<String, Double> categoryScores; // interface, gameplay, etc.
    private Map<String, Double> genreSpecificScores;
    private List<String> strengths;
    private List<String> weaknesses;
    private String testConditions;
    private List<Game> similarGames;

    // Constructeur
    public Test(Tester author, Game game, Support support, String text,
                Map<String, Double> categoryScores, String version) {
        this.id = nextId++;  // ID auto-généré
        this.author = author;
        this.game = game;
        this.support = support;
        this.text = text;
        this.categoryScores = new HashMap<>(categoryScores);  // Copie défensive
        this.version = version;
        this.date = LocalDate.now();  // Date auto-générée
        this.genreSpecificScores = new HashMap<>();
        this.strengths = new ArrayList<>();
        this.weaknesses = new ArrayList<>();
        this.testConditions = "";
        this.similarGames = new ArrayList<>();
    }

    // Méthodes de gestion du contenu
    public void addStrength(String strength) {
        if (strength != null && !strength.isEmpty()) {
            strengths.add(strength);
        }
    }
    public void addWeakness(String weakness) {
        if (weakness != null && !weakness.isEmpty()) {
            weaknesses.add(weakness);
        }
    }
    public void setTestConditions(String conditions) {
        this.testConditions = conditions != null ? conditions : "";
    }
    public void addSimilarGame(Game game) {
        if (game != null && !similarGames.contains(game)) {
            similarGames.add(game);
        }
    }
    public void addGenreSpecificScore(String category, double score) {
        if (category != null && !category.isEmpty()) {
            genreSpecificScores.put(category, score);
        }
    }

    // Méthodes de calcul
    public double getAverageScore() {
        if (categoryScores.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double score : categoryScores.values()) {
            sum += score;
        }
        return sum / categoryScores.size();
    }

    public double getOverallScore() {
        // Score global incluant les catégories générales et spécifiques au genre
        Map<String, Double> allScores = new HashMap<>(categoryScores);
        allScores.putAll(genreSpecificScores);
        if (allScores.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double score : allScores.values()) {
            sum += score;
        }
        return sum / allScores.size();
    }

    // Getters
    public int getId() {
        return id;
    }
    public Tester getAuthor() {
        return author;
    }
    public Game getGame() {
        return game;
    }
    public Support getSupport() {
        return support;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public String getVersion() {
        return version;
    }
    public Map<String, Double> getCategoryScores() {
        return new HashMap<>(categoryScores);  // Copie défensive
    }

    public Map<String, Double> getGenreSpecificScores() {
        return new HashMap<>(genreSpecificScores);  // Copie défensive
    }

    public List<String> getStrengths() {
        return new ArrayList<>(strengths);  // Copie défensive
    }

    public List<String> getWeaknesses() {
        return new ArrayList<>(weaknesses);  // Copie défensive
    }

    public String getTestConditions() {
        return testConditions;
    }

    public List<Game> getSimilarGames() {
        return new ArrayList<>(similarGames);  // Copie défensive
    }
}
