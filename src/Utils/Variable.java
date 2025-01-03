package Utils;

import Exception.CalculatorException;


public class Variable extends Node{
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Double evaluate() throws CalculatorException{
        try {
            return Variables.getVariable(name);
        } catch (CalculatorException e) {
            throw new CalculatorException(e.getMessage());
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
