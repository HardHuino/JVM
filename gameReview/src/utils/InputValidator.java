package utils;

public class InputValidator {

    // Constantes de validation
    private static final int MIN_PSEUDO_LENGTH = 3;
    private static final int MAX_PSEUDO_LENGTH = 20;
    private static final int MIN_TEXT_LENGTH = 10;
    private static final int MAX_TEXT_LENGTH = 5000;

    /**
     * Valide un pseudo
     */
    public static boolean isValidPseudo(String pseudo) {
        if (pseudo == null || pseudo.trim().isEmpty()) {
            return false;
        }

        String trimmed = pseudo.trim();

        // Vérifier la longueur
        if (trimmed.length() < MIN_PSEUDO_LENGTH || trimmed.length() > MAX_PSEUDO_LENGTH) {
            return false;
        }

        // Vérifier les caractères (lettres, chiffres, underscore, tiret)
        return trimmed.matches("^[a-zA-Z0-9_-]+$");
    }

    /**
     * Valide un score
     */
    public static boolean isValidScore(double score, double min, double max) {
        return score >= min && score <= max;
    }

    /**
     * Valide un texte (review, test, etc.)
     */
    public static boolean isValidText(String text, int minLength) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        String trimmed = text.trim();
        return trimmed.length() >= minLength && trimmed.length() <= MAX_TEXT_LENGTH;
    }

    /**
     * Valide un entier positif
     */
    public static boolean isPositiveInteger(int value) {
        return value > 0;
    }

    /**
     * Valide un entier non-négatif
     */
    public static boolean isNonNegativeInteger(int value) {
        return value >= 0;
    }

    /**
     * Valide une version (format: x.x ou x.x.x)
     */
    public static boolean isValidVersion(String version) {
        if (version == null || version.trim().isEmpty()) {
            return false;
        }

        return version.matches("^\\d+(\\.\\d+){1,2}$");
    }

    /**
     * Messages d'erreur pour les validations
     */
    public static String getPseudoErrorMessage() {
        return "Le pseudo doit contenir entre " + MIN_PSEUDO_LENGTH + " et " +
                MAX_PSEUDO_LENGTH + " caractères (lettres, chiffres, _, -)";
    }

    public static String getScoreErrorMessage(double min, double max) {
        return "Le score doit être entre " + min + " et " + max;
    }

    public static String getTextErrorMessage(int minLength) {
        return "Le texte doit contenir entre " + minLength + " et " +
                MAX_TEXT_LENGTH + " caractères";
    }
}