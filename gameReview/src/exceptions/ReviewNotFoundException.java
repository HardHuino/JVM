package exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(int message) {
        super(message);
    }
}
