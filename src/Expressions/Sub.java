package Expressions;

import Exceptions.MacchiatoException;
import Instructions.Block;

public class Sub extends Operator {

    protected Sub(Instruction left, Instruction right) {
        super(left, right);
    }

    @Override
    public int evaluate(Block parent, Instructions.Instruction caller) throws MacchiatoException {
        return left.evaluate(parent, caller) - right.evaluate(parent, caller);
    }

    @Override
    protected int getWeight() {
        return 100;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }
}
