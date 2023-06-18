package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Constant;
import Expressions.Expression;

import java.util.ArrayList;

public class ForLoop implements Instructions.Instruction {
    private char variable;
    private Expressions.Expression expression;
    private ArrayList<Instruction> instructions;
    private int instructionIndex = 0;
    private int iterations = 0;
    private int loopCounter = 0;
    private Instructions.Instruction overwritingVariable;

    public ForLoop(char variable, Expression expression, ArrayList<Instruction> instructions) {
        this.variable = variable;
        this.expression = expression;
        this.instructions = instructions;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        int iterations = expression.evaluate(parent, this);
        if (iterations <= 0) {
            return;
        }
        for (int i = 0; i < iterations; i++) {
            ArrayList<VariableDeclaration> variableDeclaration = new ArrayList<VariableDeclaration>();
            variableDeclaration.add(new VariableDeclaration(variable, Constant.create(i)));
            BlockInstruction forLoop = new BlockInstruction(variableDeclaration, instructions);
            forLoop.execute(parent);
        }
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        if (iterations == 0) {
            return forInitialization(parent);
        }
        if (instructionIndex < instructions.size()) {
            if (instructions.get(instructionIndex).nextInstructionExecute(parent)) {
                instructionIndex++;
            }
            if (instructionIndex == instructions.size()) { //end of loop
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

    private Boolean forInitialization(Block parent) throws MacchiatoException {
        iterations = expression.evaluate(parent, this);
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
    public void printNextInstruction(Block parent) {
        if (iterations == 0) {
            System.out.println("for " + variable + " in " + expression.toString() + "{");
            return;
        }
        if (instructionIndex < instructions.size()) {
            instructions.get(instructionIndex).printNextInstruction(parent);
        }
    }

    @Override
    public void display(Block parent, int depth) {
        if (iterations == 0) {
            parent.printVariables(depth);
            return;
        }
        if (instructionIndex < instructions.size()) {
            instructions.get(instructionIndex).display(parent, depth);
        }
    }

}