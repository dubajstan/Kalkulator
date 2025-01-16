package Model.Utils;

public class IntegralNode implements Node{
    private final Node func;
    private final Node down;
    private final Node up;
    private final String par;

    public IntegralNode(Node func, Node down, Node up, String par) {
        this.func = func;
        this.down = down;
        this.up = up;
        this.par = par;
    }

    public Node getFunc() {
        return func;
    }
    public Node getDown() {
        return down;
    }
    public Node getUp() {
        return up;
    }
    public String getPar() {
        return par;
    }

    @Override
    public String toString() {
        return "int{%s}{%s}(%s)d%s".formatted(down, up, func, par);
    }
}
