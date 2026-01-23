package data;

import models.*;
import java.io.*;
import java.util.*;

public class CSVDataLoader {

    /**
     * Charge les jeux depuis un fichier local
     */
    public static List<Game> loadGamesFromFile(String filepath) throws IOException {
        System.out.println("Chargement des données depuis " + filepath + "...");

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            return parseCSV(reader);
        }
    }

    /**
     * Parse le contenu CSV
     */
    private static List<Game> parseCSV(BufferedReader reader) throws IOException {
        Map<String, Game> gamesMap = new HashMap<>();
        String line;
        int lineNumber = 0;

        // Lire la ligne d'en-tête
        String header = reader.readLine();
        if (header == null) {
            throw new IOException("Fichier CSV vide");
        }
        lineNumber++;

        // Lire chaque ligne
        while ((line = reader.readLine()) != null) {
            lineNumber++;

            try {
                // Parser la ligne
                String[] fields = parseCSVLine(line);

                if (fields.length < 17) {
                    System.err.println("Ligne " + lineNumber + " ignorée (pas assez de champs)");
                    continue;
                }

                // Extraire les données
                String name = fields[1].trim();
                String platformStr = fields[2].trim();
                String yearStr = fields[3].trim();
                String genre = fields[4].trim();
                String publisher = fields[5].trim();
                String rating = fields[16].trim();

                // Données spécifiques à la plateforme
                double globalSales = parseDouble(fields[10]);
                double criticScore = parseDouble(fields[11]);
                int criticCount = parseInt(fields[12]);
                double userScore = parseDouble(fields[13]);
                int userCount = parseInt(fields[14]);
                String developer = fields[15].trim();
                int releaseYear = parseInt(yearStr);

                // Créer ou récupérer le jeu
                Game game = gamesMap.get(name);
                if (game == null) {
                    game = new Game(name, genre, publisher, rating);
                    gamesMap.put(name, game);
                }

                // Créer le support
                Support support = new Support(
                        game,
                        platformStr,
                        releaseYear,
                        developer,
                        globalSales,
                        criticCount,
                        criticScore,
                        userCount,
                        userScore
                );

                game.addSupport(support);

            } catch (Exception e) {
                System.err.println("Erreur ligne " + lineNumber + ": " + e.getMessage());
            }
        }

        System.out.println("Chargement terminé: " + gamesMap.size() + " jeux chargés");
        return new ArrayList<>(gamesMap.values());
    }

    /**
     * Parse une ligne CSV en tenant compte des virgules dans les guillemets
     */
    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        fields.add(field.toString());

        return fields.toArray(new String[0]);
    }

    /**
     * Parse un double de manière sécurisée
     */
    private static double parseDouble(String str) {
        if (str == null || str.trim().isEmpty() || str.equalsIgnoreCase("N/A")) {
            return 0.0;
        }
        try {
            return Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Parse un int de manière sécurisée
     */
    private static int parseInt(String str) {
        if (str == null || str.trim().isEmpty() || str.equalsIgnoreCase("N/A")) {
            return 0;
        }
        try {
            return (int) Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}