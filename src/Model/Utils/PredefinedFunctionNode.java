package Model.Utils;

import java.util.List;
import java.util.function.Function;

public class PredefinedFunctionNode implements Node{
    private final String name;
    private final List<String> parameters;
    private final Function<Double, Double> function;

    public PredefinedFunctionNode(String name, List<String> parameters, Function<Double, Double> function) {
        this.name = name;
        this.parameters = parameters;
        this.function = function;
    }

    public String getName() {
        return name;
    }
    public List<String> getParameters() {
        return parameters;
    }
    public Function<Double, Double> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "FuncDef(%s, %s, %s)".formatted(name, parameters, function);
    }
}
