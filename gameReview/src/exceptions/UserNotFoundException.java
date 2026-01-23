package exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String pseudo) {
        super("Utilisateur non trouvé : " + pseudo);
    }
}