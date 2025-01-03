package Main;

import Lexer.Token;
import Lexer.Tokenizer;

import java.util.ArrayList;

public class TEST_tokenizacja {
    public static void main(String[] args) {
        String s = "cos(5)";

        try {
            ArrayList<Token> tokens = Tokenizer.generateTokens(s);
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
