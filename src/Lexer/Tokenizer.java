package Lexer;

import java.util.ArrayList;
import java.util.HashMap;

public class Tokenizer {
    private static final HashMap<Character, Token.TokenType> operationChars = new HashMap<>();

    static {
        operationChars.put('+', Token.TokenType.PLUS);
        operationChars.put('-', Token.TokenType.MINUS);
        operationChars.put('*', Token.TokenType.MULTIPLY);
        operationChars.put('/', Token.TokenType.DIVIDE);
        operationChars.put('^', Token.TokenType.POWER);
        operationChars.put('!', Token.TokenType.FACTORIAL);
        operationChars.put('(', Token.TokenType.LPAREN);
        operationChars.put(')', Token.TokenType.RPAREN);
        operationChars.put('=', Token.TokenType.EQUALS);
    }

    public static ArrayList<Token> generateTokens(String input) throws Exception {
        ArrayList<Token> tokenList = new ArrayList<>();
        boolean containsDecimalPoint = false;

        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isDigit(c) || c == '.') {
                int j;

                for(j=i; j<input.length(); j++) {
                    char curr = input.charAt(j);

                    if(curr == '.') {
                        if(containsDecimalPoint) throw new Exception("Too much decimal points");
                        else containsDecimalPoint = true;
                    }

                    else if(!Character.isDigit(curr)) break;
                }

                String substring = "0" + input.substring(i,j);
                Token token = new Token(Token.TokenType.VALUE, Double.parseDouble(substring));
                tokenList.add(token);
                containsDecimalPoint = false;
                i = j-1;
            }
            else if(operationChars.containsKey(c)) {
                Token token = new Token(operationChars.get(c));
                tokenList.add(token);
            }
            else if(Character.isLetter(c)) {
                int j;

                for(j=i; j<input.length(); j++) {
                    char curr = input.charAt(j);
                    if(!Character.isLetter(curr)) break;
                }
                //TODO: O TUTAJ FUNKCJE MOGA BYC
                String substring = input.substring(i,j);
                Token token = new Token(Token.TokenType.VARIABLE, substring);
                tokenList.add(token);
                i = j-1;
            }
            else if(!Character.isWhitespace(c)) throw new Exception("Nieznany znak");
            //TODO: DODAC ZNAK = ZEBY ROZPOZNAWALO DEFINIOWANIE FUNKCJI
            //TODO: DODAC ROZPOZNAWANIE FUNKCJI tryg, hiperbolicznych, itd. tez bedzie trzeba dodac w Parser
        }
        tokenList.add(new Token(Token.TokenType.END));
        return tokenList;
    }
}
