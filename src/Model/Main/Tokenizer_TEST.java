package Model.Main;

import Model.Lexer.Token;
import Model.Lexer.Tokenizer;

import java.util.List;

public class Tokenizer_TEST {
    public static void main(String[] args) {
        String s = """
                x = 10!!
                y = x + 5
                f(a,b) = a* b
                """;

        try{
            Tokenizer tokenizer = new Tokenizer(s);
            List<Token> tokens = tokenizer.tokenize();

            for (Token token : tokens) {
                System.out.println(token);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
