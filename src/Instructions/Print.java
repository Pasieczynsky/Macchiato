package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Instruction;

public class Print implements Instructions.Instruction {
    private final Instruction instruction;

    public Print(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public void execute(BlockInstruction parent) throws MacchiatoException {
        int value = instruction.evaluate(parent,this );
        System.out.println(value);
    }
    @Override
    public Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(BlockInstruction parent) {
        System.out.println("print " + instruction.toString());
    }

    @Override
    public void display(BlockInstruction parent, int depth) {
        parent.printVariables(depth);
    }
}
