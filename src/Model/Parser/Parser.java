package Model.Parser;

import java.util.ArrayList;
import java.util.List;
import Model.Lexer.*;
import Model.Exception.CalculatorException;
import Model.Utils.*;

public class Parser {
    private final List<Token> tokens;
    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        position = 0;
    }

    private void advance() {
        if(tokens.get(position).getType() == Token.TokenType.EOF) return;
        position++;
    }

    private Token getNext() {
        return tokens.get(position);
    }

    private boolean match(Token.TokenType type) {
        if(getNext().getType() == type){
            advance();
            return true;
        }
        return false;
    }
    private void expect(Token.TokenType type) {
        if(!match(type)) {
            throw new CalculatorException("Spodziwan sie symbolu: %s, zastano: %s".formatted(type, getNext()));
        }
    }

    public Node parse() throws CalculatorException{
        return parseProgram();
    }

    private Node parseProgram() throws CalculatorException{
        Token token = getNext();

        if(token.getType() == Token.TokenType.IDENTIFIER) {
            String name = token.getValue();
            advance();

            if(match(Token.TokenType.LPAREN)) {
                List<String> parameters = parseParameterList();
                try{
                    expect(Token.TokenType.RPAREN);
                    if(match(Token.TokenType.EQUALS)){
                        Node body = parseExpression();
                        expect(Token.TokenType.EOF);
                        return new FunctionDefinitionNode(name, parameters, body);
                    }
                }catch(CalculatorException _){

                }

                position = 0;
                Node node = parseExpression();
                expect(Token.TokenType.EOF);
                return node;
            }
            else if(getNext().getType() == Token.TokenType.EQUALS) {
                advance();
                Node value = parseExpression();
                expect(Token.TokenType.EOF);
                return new VariableAssigmentNode(name, value);
            }
        }
        position = 0;
        Node node = parseExpression();
        expect(Token.TokenType.EOF);
        return node;
    }

    private List<String> parseParameterList() throws CalculatorException{
        List<String> parameters = new ArrayList<>();
        while(getNext().getType() == Token.TokenType.IDENTIFIER){
            parameters.add(getNext().getValue());
            advance();
            if(!match(Token.TokenType.COMMA)){
                break;
            }
        }
        return parameters;
    }

    private Node parseExpression() throws CalculatorException{
        Node left = parseTerm();

        while(getNext().getType() == Token.TokenType.PLUS || getNext().getType() == Token.TokenType.MINUS){
            String operator = getNext().getValue();
            advance();
            Node right = parseTerm();
            left = new BinaryOperatorNode(left, operator, right);
        }
        return left;
    }

    private Node parseTerm() throws CalculatorException{
        Node left = parsePower();

        while(getNext().getType() == Token.TokenType.MULTIPLY || getNext().getType() == Token.TokenType.DIVIDE){
            String operator = getNext().getValue();
            advance();
            Node right = parsePower();
            left = new BinaryOperatorNode(left, operator, right);
        }
        return left;
    }

    private Node parsePower() throws CalculatorException{
        Node left = parseFactorial();

        if(getNext().getType() == Token.TokenType.POWER){
            String operator = getNext().getValue();
            advance();
            Node right = parsePower();
            return new BinaryOperatorNode(left, operator, right);
        }
        return left;
    }

    private Node parseFactorial() throws CalculatorException{
        Node node = parsePrimary();

        while(getNext().getType() == Token.TokenType.FACTORIAL) {
            String operator = getNext().getValue();
            advance();
            node = new UnaryOperatorNode(node, operator);
        }
        return node;
    }

    private Node parsePrimary() throws CalculatorException{
        Token token = getNext();

        if(token.getType() == Token.TokenType.NUMBER) {
            advance();
            return new NumberNode(Double.parseDouble(token.getValue()));
        }
        else if(token.getType() == Token.TokenType.IDENTIFIER){
            String name = token.getValue();
            advance();

            if(match(Token.TokenType.LPAREN)) {
                Node funcCall =  parseFunctionCall(name);
                expect(Token.TokenType.RPAREN);
                return funcCall;
            }

            return new VariableNode(name);
        }
        else if(token.getType() == Token.TokenType.LPAREN){
            advance();
            Node node = parseExpression();
            expect(Token.TokenType.RPAREN);
            return node;
        }
        else{
            throw new CalculatorException("Niespodziewany token: " + token.getType());
        }
    }

    private Node parseFunctionCall(String name) throws CalculatorException{
        List<Node> arguments = new ArrayList<>();
        if(!match(Token.TokenType.RPAREN)){
            do{
                arguments.add(parseExpression());
            } while(match(Token.TokenType.COMMA));
        }
        return new FunctionCallNode(name, arguments);
    }
}
