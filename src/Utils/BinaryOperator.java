package Utils;

public abstract class BinaryOperator extends Node {
    private Node left;
    private Node right;

    public BinaryOperator() {
        left = null;
        right = null;
    }

    public BinaryOperator(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
}
