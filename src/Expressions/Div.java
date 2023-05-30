package Expressions;

import Exceptions.DivideByZeroException;
import Exceptions.MacchiatoException;
import Instructions.Block;
import Instructions.Instruction;

public class Div extends Operator {

    protected Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(Block parent, Instruction caller) throws MacchiatoException {
        int rightValue = right.evaluate(parent, caller);
        if (rightValue == 0) {
            throw new DivideByZeroException("\n " + caller.toString() + "\n" + parent.variablesToString(0));
        }
        return left.evaluate(parent, caller) / rightValue;
    }

    @Override
    protected int getWeight() {
        return 1000;
    }

    @Override
    protected String getSymbol() {
        return "/";
    }
}
