package Model.Utils;

public class VariableAssigmentNode implements Node{
    private final String variable;
    private final Node value;

    public VariableAssigmentNode(String variable, Node value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }
    public Node getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VarAs(%s, %s)".formatted(variable, value);
    }
}
