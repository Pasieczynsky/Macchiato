package Expressions;

import Exceptions.MacchiatoException;
import Exceptions.UndeclaredVariableException;
import Instructions.Block;

import java.util.Map;

public class Variable extends Expression {
    private final char name;

    public Variable(char name) {
        this.name = name;
    }

    @Override
    public int evaluate(Block parent, Instructions.Instruction caller) throws MacchiatoException {
        Map<Character, Integer> variables = parent.getVariables();
        Block tmp;
        for (tmp = parent; tmp != null && !variables.containsKey(this.name); variables = tmp.getVariables()) {
            tmp = tmp.getParent();
        }
        if (tmp == null) {
            throw new UndeclaredVariableException("\n " + caller + "\n " + parent.variablesToString(0));
        }
        return variables.get(name);
    }

    @Override
    public String asString() {
        return String.valueOf(name);
    }
}
