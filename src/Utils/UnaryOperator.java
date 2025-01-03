package Utils;

public abstract class UnaryOperator extends Node {
    private Node next;

    public UnaryOperator(){
        next = null;
    }

    public UnaryOperator(Node n){
        next = n;
    }

    public Node getNext(){
        return next;
    }
    public void setNext(Node n){
        next = n;
    }
}
