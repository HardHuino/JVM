package services;

import models.*;
import exceptions.*;
import java.util.*;

public class manageTests {
    // Attributs
    private List<Test> allTests;

    // Constructeur
    public manageTests() {
        this.allTests = new ArrayList<>();
    }

    // ========== Méthodes de création ==========

    public Test createTest(Tester author, Support support, String text,
                           Map<String, Double> categoryScores, String version)
            throws InsufficientPlayTimeException, UnauthorizedActionException {

        if (support == null) {
            throw new UnauthorizedActionException("Support invalide");
        }

        if (!author.canWriteTest(support.getGame(), support)) {
            if (support.hasTest()) {
                throw new UnauthorizedActionException("Un test existe déjà pour ce support");
            }
            int playTime = author.getPlayTime(support.getGame());
            throw new InsufficientPlayTimeException(support.getGame().getName(), 10, playTime);
        }

        Test test = author.writeTest(support.getGame(), support, text, categoryScores, version);
        if (test != null) {
            allTests.add(test);
        }

        return test;
    }

    // ========== Méthodes de consultation ==========

    public Test getTestForSupport(Support support) {
        if (support == null) {
            return null;
        }
        return support.getTest();
    }

    public Test getTest(int id) throws TestNotFoundException {
        for (Test test : allTests) {
            if (test.getId() == id) {
                return test;
            }
        }
        throw new TestNotFoundException(id);
    }

    public boolean hasTest(Support support) {
        return support != null && support.hasTest();
    }

    // ========== Méthodes de recherche ==========

    public List<Game> findGamesToTest(Tester tester, List<Game> allGames) {
        return tester.findGamesToTest(allGames);
    }

    public List<Support> getSupportsWithoutTest(List<Game> allGames) {
        List<Support> supportsWithoutTest = new ArrayList<>();

        for (Game game : allGames) {
            for (Support support : game.getAvailableSupports()) {
                if (!support.hasTest()) {
                    supportsWithoutTest.add(support);
                }
            }
        }

        return supportsWithoutTest;
    }

    public List<Support> getSupportsSortedByTokens(List<Game> allGames) {
        List<SupportTokenPair> pairs = new ArrayList<>();

        for (Game game : allGames) {
            for (Support support : game.getAvailableSupports()) {
                if (!support.hasTest()) {
                    pairs.add(new SupportTokenPair(support, support.getTokensPlaced()));
                }
            }
        }

        // Trier par nombre de jetons décroissant
        pairs.sort((p1, p2) -> Integer.compare(p2.tokens, p1.tokens));

        List<Support> result = new ArrayList<>();
        for (SupportTokenPair pair : pairs) {
            result.add(pair.support);
        }

        return result;
    }

    public List<Test> getAllTests() {
        return new ArrayList<>(allTests);
    }

    public int getTotalTestCount() {
        return allTests.size();
    }

    public List<Test> getTestsByAuthor(Tester author) {
        List<Test> authorTests = new ArrayList<>();
        for (Test test : allTests) {
            if (test.getAuthor().getId() == author.getId()) {
                authorTests.add(test);
            }
        }
        return authorTests;
    }

    // Classe interne pour le tri
    private static class SupportTokenPair {
        Support support;
        int tokens;

        SupportTokenPair(Support support, int tokens) {
            this.support = support;
            this.tokens = tokens;
        }
    }
}