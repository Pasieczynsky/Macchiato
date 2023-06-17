package Expressions;

import Exceptions.MacchiatoException;
import Instructions.Block;

public abstract class Instruction {

    public abstract int evaluate(Block parent, Instructions.Instruction caller) throws MacchiatoException;

    @Override
    public String toString() {
        return asString();
    }

    protected abstract String asString();

    protected int getWeight() {
        return Integer.MAX_VALUE;
    }

    public Instruction multiply(Instruction other) {
        return other.multiplyHelper(this);
    }

    protected Instruction multiplyHelper(Instruction other) {
        return new Multiply(other, this);
    }

    public Instruction add(Instruction other) {
        return other.addHelper(this);
    }

    protected Instruction addHelper(Instruction other) {
        return new Add(other, this);
    }

    public Instruction sub(Instruction other) {
        return other.subHelper(this);
    }

    protected Instruction subHelper(Instruction other) {
        return new Sub(other, this);
    }

    public Instruction div(Instruction other) {
        return other.divHelper(this);
    }

    protected Instruction divHelper(Instruction other) {
        return new Div(other, this);
    }

    public Instruction mod(Instruction other) {
        return other.modHelper(this);
    }

    protected Instruction modHelper(Instruction other) {
        return new Modulo(other, this);
    }
}
