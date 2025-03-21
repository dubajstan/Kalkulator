package Model.Interpreter;

import Model.Utils.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import Model.Utils.*;
import Model.Exception.CalculatorException;

public class Interpreter {
    private final HashMap<String, Double> variables;
    private final HashMap<String, FunctionDefinitionNode> functions;

    public Interpreter() {
        this.variables = new HashMap<>();
        this.functions = new HashMap<>();
        variables.put("pi", Math.PI);
        variables.put("e", Math.E);
        functions.put("sin", new FunctionDefinitionNode("sin", List.of("$$$"), new PredefinedFunctionNode("sin", List.of("$$$"), Math::sin)));
        functions.put("cos", new FunctionDefinitionNode("cos", List.of("$$$"), new PredefinedFunctionNode("cos", List.of("$$$"), Math::cos)));
        functions.put("tg", new FunctionDefinitionNode("tg", List.of("$$$"), new PredefinedFunctionNode("tg", List.of("$$$"), Math::tan)));
        functions.put("sqrt", new FunctionDefinitionNode("sqrt", List.of("$$$"), new PredefinedFunctionNode("sgrt", List.of("$$$"), Math::sqrt)));
        functions.put("arcsin", new FunctionDefinitionNode("arcsin", List.of("$$$"), new PredefinedFunctionNode("arcsin", List.of("$$$"), Math::asin)));
        functions.put("arccos", new FunctionDefinitionNode("arccos", List.of("$$$"), new PredefinedFunctionNode("arccos", List.of("$$$"), Math::acos)));
        functions.put("arctg", new FunctionDefinitionNode("arctg", List.of("$$$"), new PredefinedFunctionNode("arctg", List.of("$$$"), Math::atan)));
        functions.put("ln", new FunctionDefinitionNode("ln", List.of("$$$"), new PredefinedFunctionNode("ln", List.of("$$$"), Math::log)));
        functions.put("log", new FunctionDefinitionNode("log", List.of("$$$"), new PredefinedFunctionNode("log", List.of("$$$"), Math::log10)));
        functions.put("_3root", new FunctionDefinitionNode("_3root", List.of("$$$"), new PredefinedFunctionNode("_3root", List.of("$$$"), Math::cbrt)));
        functions.put("ceil", new FunctionDefinitionNode("ceil", List.of("$$$"), new PredefinedFunctionNode("ceil", List.of("$$$"), Math::ceil)));
        functions.put("floor", new FunctionDefinitionNode("floor", List.of("$$$"), new PredefinedFunctionNode("floor", List.of("$$$"), Math::floor)));
        functions.put("abs", new FunctionDefinitionNode("abs", List.of("$$$"), new PredefinedFunctionNode("abs", List.of("$$$"), Math::abs)));
        functions.put("sinh", new FunctionDefinitionNode("sinh", List.of("$$$"), new PredefinedFunctionNode("sinh", List.of("$$$"), Math::sinh)));
        functions.put("cosh", new FunctionDefinitionNode("cosh", List.of("$$$"), new PredefinedFunctionNode("cosh", List.of("$$$"), Math::cosh)));
        functions.put("tgh", new FunctionDefinitionNode("tgh", List.of("$$$"), new PredefinedFunctionNode("tgh", List.of("$$$"), Math::tanh)));

    }

    public double evaluate(Node node) {
        if(node instanceof NumberNode) {
            return ((NumberNode) node).getValue();
        }
        else if(node instanceof VariableNode) {
            String varName = ((VariableNode) node).getName();
            if(!variables.containsKey(varName)) {
                throw new CalculatorException("Zmienna " + varName + " jest niezdefiniowana");
            }
            return variables.get(varName);
        }
        else if(node instanceof BinaryOperatorNode){
            BinaryOperatorNode op = (BinaryOperatorNode) node;
            double left = evaluate(op.getLeft());
            double right = evaluate(op.getRight());

            return switch(op.getOperator()){
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> left / right;
                case "^" -> Math.pow(left, right);
                default -> throw new CalculatorException("Nieznany operator " + op.getOperator());
            };
        }
        else if(node instanceof VariableAssigmentNode){
            VariableAssigmentNode var = (VariableAssigmentNode) node;
            double value = evaluate(var.getValue());
            variables.put(var.getVariable(), value);
            return value;
        }
        else if(node instanceof UnaryOperatorNode){
            UnaryOperatorNode op = (UnaryOperatorNode) node;
            double result = evaluate(op.getNode());
            if (op.getOperator().equals("!")) {
                if (result < 0) {
                    throw new CalculatorException("Silnia z liczby ujemnej nie istnieje!");
                } else if (result != Math.floor(result)) {
                    throw new CalculatorException("Silnia z liczby niecalkowitej nie jestnieje!");
                } else if (result == 0.0) {
                    return 0.0;
                } else {
                    int n = 1;
                    for (int i = 2; i < result; i++) {
                        n *= i;
                    }
                    return (double) n;
                }
            }
            throw new CalculatorException("Nieznany operator " + op.getOperator());
        }
        else if(node instanceof FunctionDefinitionNode) {
            FunctionDefinitionNode func = (FunctionDefinitionNode) node;
            functions.put(func.getName(), func);
            return 0;
        }
        else if(node instanceof FunctionCallNode) {
            FunctionCallNode funcCall = (FunctionCallNode) node;
            FunctionDefinitionNode funcDef = functions.get(funcCall.getName());
            if(funcDef == null) {
                throw new CalculatorException("Funkcja %s jest niezdefiniowana".formatted(funcCall.getName()));
            }
            if(funcDef.getParameters().size() != funcCall.getArguments().size()) {
                throw new CalculatorException("Niewlasciwa ilosc wargumentow funkcji" + funcCall.getName());
            }

            HashMap<String, Double> localVariables = new HashMap<>(variables);
            for(int i=0;i<funcDef.getParameters().size();i++) {
                String parameterName = funcDef.getParameters().get(i);
                double argValue = evaluate(funcCall.getArguments().get(i));
                localVariables.put(parameterName, argValue);
            }
            HashMap<String, Double> variablesCopy = new HashMap<>(variables);

            variables.putAll(localVariables);
            double result;
            if(funcDef.getBody() instanceof PredefinedFunctionNode) {
                result = ((PredefinedFunctionNode) funcDef.getBody()).getFunction().apply(evaluate(funcCall.getArguments().get(0))); // zrobione dla jednego argumentu
            }else {
                result = evaluate(funcDef.getBody());
            }

            variables.clear();
            variables.putAll(variablesCopy);
            variablesCopy.clear();
            return result;
        }
        else{
            throw new CalculatorException("Wystapil nieznany blad w drzewie wyrazenia");
        }
    }

    public void reset(){
        variables.clear();
        functions.clear();
    }

    public Double execute(Node node) {
        List<Class> classes = new ArrayList<>();
        classes.add(BinaryOperatorNode.class);
        classes.add(UnaryOperatorNode.class);
        classes.add(FunctionDefinitionNode.class);
        classes.add(FunctionCallNode.class);
        classes.add(VariableAssigmentNode.class);
        classes.add(VariableNode.class);
        classes.add(NumberNode.class);
        if(classes.contains(node.getClass())) {
            return evaluate(node);
        }
        else{
            throw new CalculatorException("Nieobslugiwany typ Node");
        }
    }

    public HashMap<String, Double> getVariables() {
        return variables;
    }
    public HashMap<String, FunctionDefinitionNode> getFunctions() {
        return functions;
    }

}
