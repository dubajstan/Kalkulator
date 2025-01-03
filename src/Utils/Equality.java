package Utils;

public class Equality extends BinaryOperator {
    public Equality(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Double evaluate() {
        if(getLeft().evaluate().equals(getRight().evaluate())) {
            return 1.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "%s = %s".formatted(getLeft(), getRight());
    }
}
