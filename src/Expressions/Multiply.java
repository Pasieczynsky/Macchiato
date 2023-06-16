package Expressions;

import Exceptions.MacchiatoException;
import Instructions.BlockInstruction;

public class Multiply extends Operator {

    protected Multiply(Instruction left, Instruction right) {
        super(left, right);
    }

    @Override
    public int evaluate(BlockInstruction parent, Instructions.Instruction caller) throws MacchiatoException {
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
