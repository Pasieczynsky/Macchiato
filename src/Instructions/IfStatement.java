package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Instruction;

public class IfStatement implements Instructions.Instruction {
    private final Instruction firstInstruction;
    private final Instruction secondInstruction;
    private final String operator;
    private final Instructions.Instruction[] trueInstructions;
    private final Instructions.Instruction[] falseInstructions;
    private Instructions.Instruction[] instructions;
    private int instructionIndex = -1;

    public IfStatement(Instruction firstInstruction, Instruction secondInstruction, String operator, Instructions.Instruction[] trueInstructions, Instructions.Instruction[] falseInstructions) {
        this.firstInstruction = firstInstruction;
        this.secondInstruction = secondInstruction;
        this.operator = operator;
        this.trueInstructions = trueInstructions;
        this.falseInstructions = falseInstructions;
    }

    public IfStatement(Instruction firstInstruction, Instruction secondInstruction, String operator, Instructions.Instruction[] trueInstructions) {
        this.firstInstruction = firstInstruction;
        this.secondInstruction = secondInstruction;
        this.operator = operator;
        this.trueInstructions = trueInstructions;
        this.falseInstructions = null;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        executeStatement(parent);
        if (instructions == null) return;
        for (Instructions.Instruction instruction : instructions) {
            instruction.execute(parent);
        }
    }

    private void executeStatement(Block parent) throws MacchiatoException {
        int firstValue = firstInstruction.evaluate(parent,this );
        int secondValue = secondInstruction.evaluate(parent,this );
        boolean condition = switch (operator) {
            case "=" -> firstValue == secondValue;
            case "<>" -> firstValue != secondValue;
            case "<" -> firstValue < secondValue;
            case ">" -> firstValue > secondValue;
            case "<=" -> firstValue <= secondValue;
            case ">=" -> firstValue >= secondValue;
            default -> false;
        };
        instructions = condition ? trueInstructions : falseInstructions;
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        if (instructionIndex == -1) {
            executeStatement(parent);
            if (instructions == null) return true;
            instructionIndex++;
        } else {
            instructions[instructionIndex].nextInstructionExecute(parent);
            instructionIndex++;
            if (instructionIndex == instructions.length) {
                instructionIndex = -1;
                return true;
            }
        }
        return false;
    }

    @Override
    public void printNextInstruction(Block parent) {
        if (instructionIndex == -1) {
            System.out.println("if " + firstInstruction.toString() + " " + operator + " " + secondInstruction.toString());
        } else {
            instructions[instructionIndex].printNextInstruction(parent);
        }
    }

    @Override
    public void display(Block parent, int depth) {
        if (instructionIndex == -1) {
            parent.printVariables(depth);
        } else {
            instructions[instructionIndex].display(parent, depth);
        }
    }
}
