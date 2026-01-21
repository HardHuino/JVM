package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Test {
    // Attributs
    private String id;
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
    public Test(Tester author, Game game, Support support, String text, Map<String, Double> categoryScores, String version) {
        this.author = author;
        this.game = game;
        this.support = support;
        this.text = text;
        this.categoryScores = categoryScores;
        this.version = version;
    }

    // Méthodes de gestion du contenu
    public void addStrength(String strength) {
        strengths.add(strength);
    }
    public void addWeakness(String weakness) {
        weaknesses.add(weakness);
    }
    public void setTestConditions(String conditions) {
        testConditions = conditions;
    }
    public void addSimilarGame(Game game) {
        similarGames.add(game);
    }
    public void addGenreSpecificScore(String category, double score) {
        genreSpecificScores.put(category, score);
    }

    // Méthodes de calcul
    public double getAverageScore();

    // Getters
    public String getId() {
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
        return categoryScores;
    }
    public List<String> getStrengths() {
        return strengths;
    }
    public List<String> getWeaknesses() {
        return weaknesses;
    }
}
