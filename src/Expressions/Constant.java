package Expressions;

import Instructions.Block;
import Instructions.Instruction;

public class Constant extends Expression {

    private final int value;

    protected Constant(int value) {
        this.value = value;
    }

    public static Constant create(int value) {
        if (value == 0) {
            return ZeroConstant.instance;
        }
        if (value == 1) {
            return OneConstant.instance;
        }
        return new Constant(value);
    }

    @Override
    public int evaluate(Block parent, Instruction caller) {
        return value;
    }

    @Override
    public String asString() {
        if (value < 0) {
            return "(" + value + ")";
        }
        return "" + value;
    }
}
