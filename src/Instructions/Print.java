package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Instruction;

public class Print implements Instructions.Instruction {
    private final Instruction instruction;

    public Print(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        int value = instruction.evaluate(parent,this );
        System.out.println(value);
    }
    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(Block parent) {
        System.out.println("print " + instruction.toString());
    }

    @Override
    public void display(Block parent, int depth) {
        parent.printVariables(depth);
    }
}
