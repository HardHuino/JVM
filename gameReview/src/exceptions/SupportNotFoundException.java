package exceptions;

public class SupportNotFoundException extends RuntimeException {
    public SupportNotFoundException(String gameName, String platform) {
        super("Game '" + gameName + "' platform '" + platform + "' not found.");
    }

}
