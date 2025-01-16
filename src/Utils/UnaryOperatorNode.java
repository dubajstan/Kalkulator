package Utils;

public class UnaryOperatorNode implements Node{
    private final Node node;
    private final String operator;

    public UnaryOperatorNode(Node node, String operator) {
        this.node = node;
        this.operator = operator;
    }

    public Node getNode() {
        return node;
    }
    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "(%s)%s".formatted(node, operator);
    }
}
