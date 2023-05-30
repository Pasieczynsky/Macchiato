package Expressions;

import Exceptions.MacchiatoException;
import Instructions.Block;
import Instructions.Instruction;

public abstract class Expression {

    public abstract int evaluate(Block parent, Instruction caller) throws MacchiatoException;

    @Override
    public String toString() {
        return asString();
    }

    protected abstract String asString();

    protected int getWeight() {
        return Integer.MAX_VALUE;
    }

    public Expression multiply(Expression other) {
        return other.multiplyHelper(this);
    }

    protected Expression multiplyHelper(Expression other) {
        return new Multiply(other, this);
    }

    public Expression add(Expression other) {
        return other.addHelper(this);
    }

    protected Expression addHelper(Expression other) {
        return new Add(other, this);
    }

    public Expression sub(Expression other) {
        return other.subHelper(this);
    }

    protected Expression subHelper(Expression other) {
        return new Sub(other, this);
    }

    public Expression div(Expression other) {
        return other.divHelper(this);
    }

    protected Expression divHelper(Expression other) {
        return new Div(other, this);
    }

    public Expression mod(Expression other) {
        return other.modHelper(this);
    }

    protected Expression modHelper(Expression other) {
        return new Modulo(other, this);
    }
}
