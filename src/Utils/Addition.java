package Utils;

public class Addition extends BinaryOperator {
    public Addition(Node left, Node right){
        super(left, right);
    }

    @Override
    public Double evaluate() {
        return getLeft().evaluate() + getRight().evaluate();
    }

    @Override
    public String toString() {
        return "(%s + %s)".formatted(getLeft(), getRight());
    }

}
