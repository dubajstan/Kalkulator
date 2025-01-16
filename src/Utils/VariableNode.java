package Utils;

public class VariableNode implements Node {
    private final String name;

    public VariableNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Variable(%s)".formatted(name);
    }
}
