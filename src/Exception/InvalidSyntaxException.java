package Exception;

public class InvalidSyntaxException extends RuntimeException {
    private int position;
    public InvalidSyntaxException(String message, int position) {
        super(message);
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "Blad: %s, na pozycji: %s".formatted(super.getMessage(), position);
    }
}
