package Utils;

import Exception.CalculatorException;

public class Division extends BinaryOperator {
    public Division(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Double evaluate() throws CalculatorException {
        double r = getRight().evaluate();
        if(r == 0.00){
            throw new CalculatorException("Dzielenie przez 0 jest niedozwolone!");
        }

        return getLeft().evaluate() / r;
    }

    @Override
    public String toString() {
        return "(%s / %s)".formatted(getLeft(), getRight());
    }
}
