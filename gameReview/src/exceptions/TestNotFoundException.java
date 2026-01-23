package exceptions;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException(int message) {
        super(message);
    }
}
