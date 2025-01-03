package Main;

import Parser.Parser;
import Utils.Node;

public class TEST_Parser {
    public static void main(String[] args) {
        String s = "3!!  =720 ";

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
