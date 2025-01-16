package Main;

import Interpreter.Interpreter;
import Lexer.Token;
import Lexer.Tokenizer;
import Parser.Parser;
import Utils.Node;

import java.util.List;

public class Interpreter_TEST {
    public static void main(String[] args) {
        String[] linie = {
                "x = 5",
                "f(x) = 2*x",
                "f(x)",
                "f(x)",
                "x",
                "f("
        };

        try{
            //Tokenizer tokenizer = new Tokenizer(s);
            //List<Token> tokens = tokenizer.tokenize();

            //Parser parser = new Parser(tokens);

            //Node node = parser.parse();
            //System.out.println(node);

            for(String l : linie){
                Tokenizer tokenizer = new Tokenizer(l);
                List<Token> tokens = tokenizer.tokenize();
                Parser parser = new Parser(tokens);
                Node node = parser.parse();
                Interpreter interpreter = new Interpreter();
                interpreter.execute(node);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
