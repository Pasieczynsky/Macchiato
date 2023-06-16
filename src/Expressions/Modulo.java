package Expressions;

import Exceptions.MacchiatoException;
import Instructions.BlockInstruction;

public class Modulo extends Operator {

    protected Modulo(Instruction left, Instruction right) {
        super(left, right);
    }

    @Override
    public int evaluate(BlockInstruction parent, Instructions.Instruction caller) throws MacchiatoException {
        int rightValue = right.evaluate(parent, caller);
        if (rightValue == 0) {
            throw new Exceptions.DivideByZeroException("\n " + caller.toString() + "\n" + parent.variablesToString(0));
        } else {
            return left.evaluate(parent, caller) % rightValue;
        }

    }

    @Override
    protected int getWeight() {
        return 1000;
    }

    @Override
    protected String getSymbol() {
        return "%";
    }
}
