package Instructions;

import java.util.Map;

import Exceptions.MacchiatoException;
import Exceptions.VariableRedeclarationException;
import Expressions.Instruction;

public class VariableDeclaration implements Instructions.Instruction {
    private final char variable;
    private final Instruction instruction;

    public VariableDeclaration(char variable, Instruction instruction) {
        this.variable = variable;
        this.instruction = instruction;
    }

    @Override
    public void execute(BlockInstruction parent) throws MacchiatoException {
        Map<Character, Integer> variables = parent.getVariables();
        if (variables.containsKey(variable)) {
            throw new VariableRedeclarationException("\n " + this + "\n" + parent.variablesToString(0));
        }
        int value = instruction.evaluate(parent, this);
        variables.put(variable, value);
    }

    @Override
    public Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(BlockInstruction parent) {
        System.out.println(this);
    }

    @Override
    public void display(BlockInstruction parent, int depth) {
        parent.printVariables(depth);
    }
    @Override
    public String toString() {
        return ("var " + variable + " = " + instruction.toString());
    }
    protected Character getVariable() {
        return variable;
    }
}
