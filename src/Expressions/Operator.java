package Expressions;

public abstract class Operator extends Instruction {

    protected final Instruction left;
    protected final Instruction right;

    protected Operator(Instruction left, Instruction right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected String asString() {
        String str = "";
        if (left.getWeight() < getWeight()) {
            str += "(" + left + ")";
        } else {
            str += left.toString();
        }

        str += " " + getSymbol() + " ";

        if (right.getWeight() < getWeight()) {
            str += "(" + right + ")";
        } else {
            str += right.toString();
        }

        return str;
    }

    protected abstract String getSymbol();
}
