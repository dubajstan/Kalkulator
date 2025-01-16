package Model.Lexer;

public class Token {
    public enum TokenType {
        IDENTIFIER,
        NUMBER,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        POWER,
        FACTORIAL,
        LPAREN,
        RPAREN,
        COMMA,
        EQUALS,
        LBRACKET,
        RBRACKET,
        EOF
    }

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token(%s, %s)".formatted(type, value);
    }
}
