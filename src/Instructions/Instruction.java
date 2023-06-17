package Instructions;

import Exceptions.MacchiatoException;

public interface Instruction {
    void execute(Block parent) throws MacchiatoException;

//    Returns true if instruction has ended
    Boolean nextInstructionExecute(Block parent) throws MacchiatoException;
    void printNextInstruction(Block parent);
    void display(Block parent, int depth);

}
