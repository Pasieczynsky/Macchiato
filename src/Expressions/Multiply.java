package Expressions;

import Exceptions.MacchiatoException;
import Instructions.Block;
import Instructions.Instruction;

public class Multiply extends Operator {

    protected Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(Block parent, Instruction caller) throws MacchiatoException {
        return left.evaluate(parent, caller) * right.evaluate(parent, caller);
    }

    @Override
    protected int getWeight() {
        return 1000;
    }

    @Override
    protected String getSymbol() {
        return "*";
    }
}
