package Utils;

import java.util.List;

public class FunctionCallNode implements Node {
    String name;
    List<Node> arguments;

    public FunctionCallNode(String name, List<Node> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }
    public List<Node> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "%s(%s)".formatted(name, arguments);
    }
}
