package Main;

import Lexer.Token;
import Lexer.Tokenizer;
import Parser.Parser;
import Utils.Node;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TEST_Parser {
    public static void main(String[] args) {
        String s = "5+2^7-1-(-5/3!)/2-1+2.0";

        try {
            Parser parser = new Parser(s);
            Node root = parser.parse();
            System.out.println(root);
            System.out.println(root.evaluate());

        } catch(IllegalArgumentException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
