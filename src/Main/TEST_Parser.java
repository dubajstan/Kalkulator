package Main;

import Lexer.Token;
import Lexer.Tokenizer;
import Parser.Parser;
import Utils.Node;
import java.util.List;

public class TEST_Parser {
    public static void main(String[] args) {
        String s = "f(a,b) = a* b";

        try{
            Tokenizer tokenizer = new Tokenizer(s);
            List<Token> tokens = tokenizer.tokenize();

            Parser parser = new Parser(tokens);

            Node node = parser.parse();
            System.out.println(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
