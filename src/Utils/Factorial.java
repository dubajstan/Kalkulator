package Utils;

import Exception.CalculatorException;

public class Factorial extends UnaryOperator {
    public Factorial(Node next){
        super(next);
    }

    @Override
    public Double evaluate()  throws CalculatorException {
        Double n = super.getNext().evaluate();
        if(n != n.intValue()){
            throw new CalculatorException("Argument silni powinien byc typu calkowitego!");
        }
        if(n < 0){
            throw new CalculatorException("Argument silni powinien byc nieujemny!");
        }

        double result = 1;
        int p = n.intValue();

        for(int i = 1; i <= p; i++){
            result *= i;
        }
        return result;
    }

    @Override
    public String toString(){
        return "(%s)!".formatted(super.getNext());
    }
}
