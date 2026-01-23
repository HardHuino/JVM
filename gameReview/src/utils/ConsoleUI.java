package utils;

import models.*;
import java.util.*;

public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    // Constantes pour l'affichage
    private static final String SEPARATOR = "================================================";
    private static final String SEPARATOR_SMALL = "------------------------";

    // ========== Menus ==========

    public static void displayMainMenu(User user) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("           PLATEFORME D'ÉVALUATION DE JEUX VIDÉO");
        System.out.println(SEPARATOR);
        System.out.println("Connecté en tant que: " + getUserDisplay(user));
        System.out.println();

        if (user instanceof Guest) {
            displayGuestMenu();
        } else if (user instanceof Admin) {
            displayAdminMenu();
        } else if (user instanceof Tester) {
            displayTesterMenu();
        } else if (user instanceof Player) {
            displayPlayerMenu();
        }
    }

    private static void displayGuestMenu() {
        System.out.println("1.  Rechercher des jeux");
        System.out.println("2.  Consulter des évaluations");
        System.out.println("3.  S'inscrire");
        System.out.println("4.  Se connecter");
        System.out.println("0.  Quitter");
    }

    private static void displayPlayerMenu() {
        System.out.println("1.  Rechercher des jeux");
        System.out.println("2.  Consulter des évaluations");
        System.out.println("3.  Écrire une évaluation");
        System.out.println("4.  Évaluer une évaluation");
        System.out.println("5.  Ajouter un jeu à ma collection");
        System.out.println("6.  Ajouter du temps de jeu");
        System.out.println("7.  Placer/Retirer des jetons");
        System.out.println("8.  Voir mes informations");
        System.out.println("9.  Se déconnecter");
        System.out.println("0.  Quitter");
    }

    private static void displayTesterMenu() {
        System.out.println("1.  Rechercher des jeux");
        System.out.println("2.  Consulter des évaluations et tests");
        System.out.println("3.  Écrire une évaluation");
        System.out.println("4.  Écrire un test");
        System.out.println("5.  Rechercher des jeux à tester");
        System.out.println("6.  Signaler une évaluation");
        System.out.println("7.  Ajouter un jeu à ma collection");
        System.out.println("8.  Voir mes informations");
        System.out.println("9.  Se déconnecter");
        System.out.println("0.  Quitter");
    }

    private static void displayAdminMenu() {
        System.out.println("1.  Rechercher des jeux");
        System.out.println("2.  Consulter des évaluations et tests");
        System.out.println("3.  Gérer les membres");
        System.out.println("4.  Voir les évaluations signalées");
        System.out.println("5.  Supprimer une évaluation");
        System.out.println("6.  Bloquer/Débloquer un membre");
        System.out.println("7.  Promouvoir un membre");
        System.out.println("8.  Voir mes informations");
        System.out.println("9.  Se déconnecter");
        System.out.println("0.  Quitter");
    }

    // ========== Affichage d'informations ==========

    public static void displayGameInfo(Game game) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  " + game.getName());
        System.out.println(SEPARATOR);
        System.out.println("Genre: " + game.getGenre());
        System.out.println("Éditeur: " + game.getPublisher());
        System.out.println("Classification: " + game.getRating());
        System.out.println("\nPlateformes disponibles:");

        Collection<Support> supports = game.getAvailableSupports();
        int i = 1;
        for (Support support : supports) {
            System.out.println("  " + i + ". " + support.getPlatform());
            i++;
        }
    }

    public static void displaySupportInfo(Support support) {
        System.out.println("\n" + SEPARATOR_SMALL);
        System.out.println("Plateforme: " + support.getPlatform());
        System.out.println("Année: " + support.getReleaseYear());
        System.out.println("Développeur: " + support.getDeveloper());
        System.out.println("Ventes: " + support.getGlobalSales() + "M");
        System.out.println("Score critiques: " + support.getCriticScore() + "/100 (" +
                support.getCriticCount() + " critiques)");
        System.out.println("Score joueurs: " + support.getUserScore() + "/10 (" +
                support.getUserCount() + " évaluations)");
        System.out.println("Jetons placés: " + support.getTokensPlaced());
        System.out.println("Test disponible: " + (support.hasTest() ? "✓ Oui" : "✗ Non"));
        System.out.println("Évaluations: " + support.getReviewCount());
    }

    public static void displayReview(Review review) {
        System.out.println("\n" + SEPARATOR_SMALL);
        System.out.println("Auteur: " + review.getAuthor().getPseudo());
        System.out.println("Date: " + review.getDate());
        System.out.println("Note: " + review.getScore() + "/10");
        System.out.println("Version: " + review.getVersion());
        System.out.println("\n" + review.getText());
        System.out.println("\nUtilité: 👍 " + review.getPositiveCount() +
                " | 😐 " + review.getNeutralCount() +
                " | 👎 " + review.getNegativeCount());
        if (review.isReported()) {
            System.out.println("⚠ SIGNALÉE: " + review.getReportReason());
        }
    }

    public static void displayTest(Test test) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  TEST - " + test.getGame().getName() + " (" +
                test.getSupport().getPlatform() + ")");
        System.out.println(SEPARATOR);
        System.out.println("Auteur: " + test.getAuthor().getPseudo());
        System.out.println("Date: " + test.getDate());
        System.out.println("Version testée: " + test.getVersion());

        System.out.println("\n--- Scores par catégorie ---");
        for (Map.Entry<String, Double> entry : test.getCategoryScores().entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + "/10");
        }

        if (!test.getGenreSpecificScores().isEmpty()) {
            System.out.println("\n--- Scores spécifiques au genre ---");
            for (Map.Entry<String, Double> entry : test.getGenreSpecificScores().entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue() + "/10");
            }
        }

        System.out.println("\n--- Analyse ---");
        System.out.println(test.getText());

        if (!test.getStrengths().isEmpty()) {
            System.out.println("\n✓ Points forts:");
            for (String strength : test.getStrengths()) {
                System.out.println("  • " + strength);
            }
        }

        if (!test.getWeaknesses().isEmpty()) {
            System.out.println("\n✗ Points faibles:");
            for (String weakness : test.getWeaknesses()) {
                System.out.println("  • " + weakness);
            }
        }

        if (!test.getTestConditions().isEmpty()) {
            System.out.println("\n⚙ Conditions du test:");
            System.out.println("  " + test.getTestConditions());
        }

        System.out.println("\nNote moyenne: " + test.getAverageScore() + "/10");
    }

    public static void displayMemberInfo(Member member) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  " + member.getPseudo());
        System.out.println(SEPARATOR);
        System.out.println("Type: " + member.getType());
        System.out.println("Jetons disponibles: " + member.getAvailableTokens());
        System.out.println("Jeux possédés: " + member.getGamePlaytime().size());
        System.out.println("Temps de jeu total: " + member.getTotalPlayTime() + "h");

        if (member instanceof Player) {
            Player player = (Player) member;
            System.out.println("Évaluations écrites: " + player.getReviewCount());
            System.out.println("👍 reçus: " + player.getPositiveRatingsCount());
        }

        if (member instanceof Tester) {
            Tester tester = (Tester) member;
            System.out.println("Tests écrits: " + tester.getTestCount());
        }
    }

    // ========== Saisie utilisateur ==========

    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                displayError("Veuillez entrer un nombre entier valide");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                displayError("Veuillez entrer un nombre valide");
            }
        }
    }

    public static boolean readConfirmation(String prompt) {
        System.out.print(prompt + " (o/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("o") || input.equals("oui") || input.equals("y") || input.equals("yes");
    }

    public static int readChoice(String prompt, int min, int max) {
        while (true) {
            int choice = readInt(prompt);
            if (choice >= min && choice <= max) {
                return choice;
            }
            displayError("Choix invalide. Veuillez choisir entre " + min + " et " + max);
        }
    }

    // ========== Messages ==========

    public static void displayMessage(String message) {
        System.out.println("\n✓ " + message);
    }

    public static void displayError(String error) {
        System.out.println("\n✗ Erreur: " + error);
    }

    public static void displayWarning(String warning) {
        System.out.println("\n⚠ Attention: " + warning);
    }

    public static void displaySuccess(String message) {
        System.out.println("\n✓ Succès: " + message);
    }

    public static void waitForEnter() {
        System.out.print("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        // Tentative de nettoyage d'écran (ne fonctionne pas partout)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // ========== Helpers ==========

    private static String getUserDisplay(User user) {
        if (user instanceof Guest) {
            return "Invité";
        } else if (user instanceof Member) {
            Member member = (Member) user;
            return member.getPseudo() + " (" + member.getType() + ")";
        }
        return "Inconnu";
    }
}
