package Utils;

public class Negation extends UnaryOperator {

    public Negation(Node next){
        super(next);
    }

    @Override
    public Double evaluate() {
        return -getNext().evaluate();
    }

    @Override
    public String toString() {
        return "(-%s)".formatted(getNext());
    }
}
