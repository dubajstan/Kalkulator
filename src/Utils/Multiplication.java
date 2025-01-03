package Utils;

public class Multiplication extends BinaryOperator {
    public Multiplication(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Double evaluate(){
        return getLeft().evaluate() * getRight().evaluate();
    }

    @Override
    public String toString() {
        return "(%s * %s)".formatted(getLeft(), getRight());
    }
}
