package Exception;

public class LexerException extends RuntimeException {
    public LexerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
