package Lexer;

public class Token {
    public enum TokenType {
        VALUE,
        VARIABLE,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        POWER,
        FACTORIAL,
        LPAREN,
        RPAREN,
        END
    }

    public final TokenType type;
    public final Double val;
    public final String name;

    public Token(TokenType type, Double val) {
        this.type = type;
        this.val = val;
        name = null;
    }

    public Token(TokenType type) {
        this.type = type;
        this.val = null;
        name = null;
    }

    public Token(TokenType type, String name) {
        this.type = type;
        this.val = null;
        this.name = name;
    }

    public TokenType getType() {
        return type;
    }
    public Double getVal() {
        return val;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (val == null && name == null) {
            return "Token{type: %s}".formatted(type);
        }
        if(val == null) {
            return "Token{type: %s, name: %s}".formatted(type, name);
        }
        return "Token{type: %s, value: %s".formatted(type, val);
    }
}
