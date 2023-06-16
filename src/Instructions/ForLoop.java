package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Constant;
import Expressions.Instruction;

public class ForLoop implements Instructions.Instruction {
    private final char variable;
    private final Instruction instruction;
    private final Instructions.Instruction[] instructions;
    private int instructionIndex = 0;
    private int iterations = 0;
    private int loopCounter = 0;
    private Instructions.Instruction overwritingVariable;

    public ForLoop(char variable, Instruction instruction, Instructions.Instruction[] instructions) {
        this.variable = variable;
        this.instruction = instruction;
        this.instructions = instructions;
    }

    @Override
    public void execute(BlockInstruction parent) throws MacchiatoException {
        int iterations = instruction.evaluate(parent, this);
        if (iterations <= 0) {
            return;
        }
        for (int i = 0; i < iterations; i++) {
            BlockInstruction forLoop = new BlockInstruction(new VariableDeclaration[]{(new VariableDeclaration(variable, Constant.create(i)))}, instructions);
            forLoop.execute(parent);
        }
    }

    @Override
    public Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException {
        if (iterations == 0) {
            return forInitialization(parent);
        }
        if (instructionIndex < instructions.length) {
            if (instructions[instructionIndex].nextInstructionExecute(parent)) {
                instructionIndex++;
            }
            if (instructionIndex == instructions.length) { //end of loop
                instructionIndex = 0;
                loopCounter++;
                if (loopCounter == iterations) { //end of for
                    iterations = 0;
                    loopCounter = 0;
                    if (overwritingVariable != null) {
                        overwritingVariable.execute(parent);
                    }
                    return true;
                }
                new VariableAssignment(variable, Constant.create(loopCounter)).execute(parent);
            }
        }
        return false;
    }

    private Boolean forInitialization(BlockInstruction parent) throws MacchiatoException {
        iterations = instruction.evaluate(parent, this);
        if (iterations <= 0) {
            return true;
        }
        if (parent.getVariables().containsKey(variable)) {
            new VariableAssignment(variable, Constant.create(loopCounter)).execute(parent);
            this.overwritingVariable = new VariableDeclaration(variable, Constant.create(parent.getVariables().get(variable)));
        } else {
            new VariableDeclaration(variable, Constant.create(loopCounter)).execute(parent);
        }
        return false;
    }

    @Override
    public void printNextInstruction(BlockInstruction parent) {
        if (iterations == 0) {
            System.out.println("for " + variable + " in " + instruction.toString() + "{");
            return;
        }
        if (instructionIndex < instructions.length) {
            instructions[instructionIndex].printNextInstruction(parent);
        }
    }

    @Override
    public void display(BlockInstruction parent, int depth) {
        if (iterations == 0) {
            parent.printVariables(depth);
            return;
        }
        if (instructionIndex < instructions.length) {
            instructions[instructionIndex].display(parent, depth);
        }
    }

}