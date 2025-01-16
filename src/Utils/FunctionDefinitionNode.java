package Utils;

import java.util.List;

public class FunctionDefinitionNode implements Node {
    private final String name;
    private final List<String> parameters;
    private final Node body;

    public FunctionDefinitionNode(String name, List<String> parameters, Node body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    public String getName() {
        return name;
    }
    public List<String> getParameters() {
        return parameters;
    }
    public Node getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "FuncDef(%s, %s, %s)".formatted(name, parameters, body);
    }
}
