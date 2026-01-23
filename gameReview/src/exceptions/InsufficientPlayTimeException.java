package exceptions;

public class InsufficientPlayTimeException extends Exception {
    public InsufficientPlayTimeException(String gameName, int required, int actual) {
        super(String.format(
                "Temps de jeu insuffisant pour %s. Requis: %dh, actuel: %dh",
                gameName, required, actual
        ));
    }
}