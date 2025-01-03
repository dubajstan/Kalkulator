package Utils;

import java.util.HashMap;
import Exception.CalculatorException;

public class Variables {
    private static HashMap<String, Node> variables = new HashMap<>();

    public static HashMap<String, Node> getVariables() {
        return variables;
    }

    public static void setVariables(HashMap<String, Node> variables) {
        Variables.variables = variables;
    }

    public static Double getVariable(String name) throws CalculatorException {
        Double var = variables.get(name).evaluate();
        if (var == null) {
            throw new CalculatorException("Zmienna '%s' jest niezdefiniowana".formatted(name));
        }
        return var;
    }

    public static void deleteVariable(String name) throws CalculatorException {
        variables.remove(name);
    }

    public static void setVariable(String name, Node value) throws CalculatorException {
        try{
            deleteVariable(name);
        } catch (CalculatorException e) {
            throw e;
        }
        variables.put(name, value);
    }

    public static void addVariable(String name, Node value) throws CalculatorException {
        if(variables.containsKey(name)){
            throw new CalculatorException("Zmienna %s juz jest zdefiniowana".formatted(name));
        }
        variables.put(name, value);
    }
}