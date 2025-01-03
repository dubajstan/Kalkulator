package Parser;

import Lexer.*;
import java.util.ArrayList;
import Utils.*;

public class Parser {
    private int position;
    private ArrayList<Token> tokens;

    public Parser(String input) {
        this.position = 0;
        try{
            this.tokens = Tokenizer.generateTokens(input);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private Token currentToken() {
        return tokens.get(position);
    }

    private void advance() {
        if(!currentToken().getType().equals(Token.TokenType.END)) {
            position++;
        }
    }

    public Node parse() throws Exception {
        Node root = expression();
        if(root == null || !currentToken().getType().equals(Token.TokenType.END)) throw new Exception("Bledna skladnia");
        return root;
    }

    private Node expression() {
        Node left = term();

        while(currentToken().getType().equals(Token.TokenType.PLUS) || currentToken().getType().equals(Token.TokenType.MINUS)) {
            Token.TokenType operator = currentToken().getType();
            advance();
            Node right = term();
            switch(operator){
                case PLUS:
                    left = new Addition(left, right);
                    break;
                case MINUS:
                    left = new Substraction(left, right);
                    break;
                default:
                    break;
            }
        }
        return left;
    }

    private Node term() {
        Node left = power();

        while(currentToken().getType().equals(Token.TokenType.MULTIPLY) || currentToken().getType().equals(Token.TokenType.DIVIDE)) {
            Token.TokenType operator = currentToken().getType();
            advance();
            Node right = power();
            switch(operator){
                case MULTIPLY:
                    left = new Multiplication(left, right);
                    break;
                case DIVIDE:
                    left = new Division(left, right);
                    break;
                default:
                    break;
            }
        }
        return left;
    }

    private Node power() {
        Node left = factorial();

        if(currentToken().getType().equals(Token.TokenType.POWER)) {
            Token.TokenType operator = currentToken().getType();
            advance();
            Node right = factorial();
            return new Power(left, right);
        }
        return left;
    }

    private Node factorial() {
        Node node = primary();

        while(currentToken().getType().equals(Token.TokenType.FACTORIAL)){
            Token.TokenType operator = currentToken().getType();
            advance();
            node = new Factorial(node);
        }
        return node;
    }

    private Node primary() {
        Token token = currentToken();

        if(token.getType().equals(Token.TokenType.VALUE)) {
            advance();
            return new Value(token.getVal());
        }

        if(token.getType().equals(Token.TokenType.VARIABLE)) {
            advance();
            return new Variable(token.getName());
        }

        if(token.getType().equals(Token.TokenType.MINUS)) {
            advance();
            return new Negation(factorial());
        }

        if(token.getType().equals(Token.TokenType.LPAREN)) {
            advance();
            Node node = expression();
            if(!currentToken().getType().equals(Token.TokenType.RPAREN)) {
                throw new IllegalArgumentException("Spodziewano sie nawiasu zamykajacego");
            }
            advance();
            return node;
        }
        throw new IllegalArgumentException("Niespodziewany token"+token);
    }
}
