package Expressions;

import Exceptions.MacchiatoException;
import Instructions.BlockInstruction;

public class Add extends Operator {

    protected Add(Instruction left, Instruction right) {
        super(left, right);
    }

    @Override
    public int evaluate(BlockInstruction parent, Instructions.Instruction caller) throws MacchiatoException {
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
