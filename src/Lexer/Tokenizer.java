package Lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Exception.LexerException;

public class Tokenizer {
    private static final List<Pair<String, Token.TokenType>> TOKEN_REGEX = Arrays.asList(
            new Pair<>("[\\s]+", null),
            new Pair<>("[0-9]+(\\.[0-9]+)?", Token.TokenType.NUMBER),
            new Pair<>("[a-zA-Z_][a-zA-Z0-9]*", Token.TokenType.IDENTIFIER),
            new Pair<>("\\+", Token.TokenType.PLUS),
            new Pair<>("-", Token.TokenType.MINUS),
            new Pair<>("\\*", Token.TokenType.MULTIPLY),
            new Pair<>("/", Token.TokenType.DIVIDE),
            new Pair<>("\\(", Token.TokenType.LPAREN),
            new Pair<>("\\)", Token.TokenType.RPAREN),
            new Pair<>(",", Token.TokenType.COMMA),
            new Pair<>("=", Token.TokenType.EQUALS),
            new Pair<>("!", Token.TokenType.FACTORIAL),
            new Pair<>("^", Token.TokenType.POWER)
    );

    private final String inputText;
    private int position;

    public Tokenizer(String inputText) {
        this.inputText = inputText;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < inputText.length()) {
            boolean matched = false;

            for(Pair<String, Token.TokenType> token : TOKEN_REGEX) {
                String pattern = token.getKey();
                Token.TokenType type = token.getValue();

                Pattern compiledPattern = Pattern.compile(pattern);
                Matcher matcher = compiledPattern.matcher(inputText.substring(position));

                if(matcher.lookingAt()) {
                    matched = true;

                    if(type != null) {
                        String value = matcher.group();
                        tokens.add(new Token(type, value));
                    }

                    position += matcher.end();
                    break;
                }
            }

            if(!matched) {
                throw new LexerException("Nieznany symbol: " + inputText.charAt(position) + position);
            }
        }

        tokens.add(new Token(Token.TokenType.EOF, null));
        return tokens;
    }


    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
}
