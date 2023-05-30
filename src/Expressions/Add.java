package Expressions;

import Exceptions.MacchiatoException;
import Instructions.Block;
import Instructions.Instruction;

public class Add extends Operator {

    protected Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(Block parent, Instruction caller) throws MacchiatoException {
        return left.evaluate(parent, caller) + right.evaluate(parent, caller);
    }

    @Override
    protected int getWeight() {
        return 100;
    }

    @Override
    protected String getSymbol() {
        return "+";
    }
}
