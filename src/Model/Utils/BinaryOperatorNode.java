package Model.Utils;

public class BinaryOperatorNode implements Node {
    private final Node left;
    private final Node right;
    private final String operator;

    public BinaryOperatorNode(Node left, String operator, Node right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "BinOpr(%s %s %s)".formatted(left, operator, right);
    }
}
