package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Expression;

import java.util.ArrayList;

public class IfStatement implements Instructions.Instruction {
    private final Expression firstExpression;
    private final Expression secondExpression;
    private final String operator;
    private final ArrayList<Instruction> trueInstructions;
    private final ArrayList<Instruction> falseInstructions;
    private ArrayList<Instruction> instructions;
    private int instructionIndex = -1;

    public IfStatement(Expression firstExpression, Expression secondExpression, String operator, ArrayList<Instruction> trueInstructions, ArrayList<Instruction> falseInstructions) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
        this.trueInstructions = trueInstructions;
        this.falseInstructions = falseInstructions;
    }

    public IfStatement(Expression firstExpression, Expression secondExpression, String operator, ArrayList<Instruction> trueInstructions) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
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
        int firstValue = firstExpression.evaluate(parent,this );
        int secondValue = secondExpression.evaluate(parent,this );
        boolean condition = switch (operator) {
            case "==" -> firstValue == secondValue;
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
            instructions.get(instructionIndex).nextInstructionExecute(parent);
            instructionIndex++;
            if (instructionIndex == instructions.size()) {
                instructionIndex = -1;
                return true;
            }
        }
        return false;
    }

    @Override
    public void printNextInstruction(Block parent) {
        if (instructionIndex == -1) {
            System.out.println("if " + firstExpression.toString() + " " + operator + " " + secondExpression.toString());
        } else {
            instructions.get(instructionIndex).printNextInstruction(parent);
        }
    }

    @Override
    public void display(Block parent, int depth) {
        if (instructionIndex == -1) {
            parent.printVariables(depth);
        } else {
            instructions.get(instructionIndex).display(parent, depth);
        }
    }
}
