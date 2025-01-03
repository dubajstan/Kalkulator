package Main;

import Exception.CalculatorException;
import Utils.*;

public class TEST_evaluate {
    public static void main(String[] args) {
        Variables.addVariable("x", new Value(2.0));

        Node v1 = new Value(1.0);
        Node v2 = new Value(2.0);
        Node a1 = new Addition(v1,v2);
        Node v3 = new Variable("x");
        Node d1 = new Division(a1,v3);
        Node v4 = new Value(5.0);
        Node a2 = new Addition(d1,v4);
        Node v5 = new Value(5.0);
        Node v8 = new Value(1.0);
        Node s1 = new Substraction(v5,v8);
        Node v6 = new Value(2.0);
        Node m1 = new Multiplication(v6,s1);
        Node s2 = new Substraction(a2,m1);
        Node v7 = new Value(2.0);
        Node root = new Substraction(s2,v7);

        try{
            double result = root.evaluate();
            System.out.println(root);
            System.out.println(result);
        } catch(CalculatorException e){
            e.printStackTrace();
        }
    }
}
