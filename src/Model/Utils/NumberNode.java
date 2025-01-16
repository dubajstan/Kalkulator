package Model.Utils;

public class NumberNode implements Node {
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Number(%s)".formatted(value);
    }
}
