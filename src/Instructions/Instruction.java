package Instructions;

import Exceptions.MacchiatoException;

public interface Instruction {
    void execute(BlockInstruction parent) throws MacchiatoException;

//    Returns true if instruction has ended
    Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException;
    void printNextInstruction(BlockInstruction parent);
    void display(BlockInstruction parent, int depth);

}
