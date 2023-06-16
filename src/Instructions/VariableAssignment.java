package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.UndeclaredVariableException;
import Expressions.Instruction;

import java.util.Map;

public class VariableAssignment implements Instructions.Instruction {
    private final char variable;
    private final Instruction instruction;

    public VariableAssignment(char variable, Instruction instruction) {
        this.variable = variable;
        this.instruction = instruction;
    }

    @Override
    public void execute(BlockInstruction parent) throws MacchiatoException {
        Map<Character, Integer> variables = null;
        BlockInstruction tmp;
        for(tmp = parent; tmp != null; tmp = tmp.getParent()) {
            variables = tmp.getVariables();
            if (variables.containsKey(variable)) {
                break;
            }
        }
        if(tmp == null){
            throw new UndeclaredVariableException("\n " + this + "\n " + parent.variablesToString(0));
        }
        int value = instruction.evaluate(parent, this);
        variables.replace(variable, value);
    }

    @Override
    public Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException{
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(BlockInstruction parent) {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return (variable + " := " + instruction.toString());
    }
    @Override
    public void display(BlockInstruction parent, int depth) {
        parent.printVariables(depth);
    }
}
