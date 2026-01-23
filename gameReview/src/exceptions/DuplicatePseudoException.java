package exceptions;

public class DuplicatePseudoException extends RuntimeException {
    public DuplicatePseudoException(String message) {
        super(message);
    }
}
