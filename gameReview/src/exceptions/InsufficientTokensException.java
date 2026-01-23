package exceptions;

public class InsufficientTokensException extends RuntimeException {
    public InsufficientTokensException(String message) {
        super(message);
    }

    public InsufficientTokensException(int amount, int availableTokens) {
        super("Insufficient tokens: " + availableTokens + " of " + amount);
    }
}
