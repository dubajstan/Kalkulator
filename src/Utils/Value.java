package Utils;

public class Value extends Node {
    private Double val;
    public Value(double val){
        this.val = val;
    }
    public double getVal(){
        return val;
    }
    public void setVal(double val){
        this.val = val;
    }

    @Override
    public Double evaluate(){
        return val;
    }

    @Override
    public String toString(){
        return Double.toString(val);
    }
}
