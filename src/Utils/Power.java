package Utils;

import Exception.CalculatorException;

public class Power extends BinaryOperator {
    public Power(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Double evaluate() throws CalculatorException {
        return Math.pow(getLeft().evaluate(), getRight().evaluate());
    }

    @Override
    public String toString() {
        return "(%s)^(%s)".formatted(getLeft(), getRight());
    }
}
