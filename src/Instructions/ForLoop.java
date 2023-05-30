package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Constant;
import Expressions.Expression;

public class ForLoop implements Instruction {
    private final char variable;
    private final Expression expression;
    private final Instruction[] instructions;
    private int instructionIndex = 0;
    private int iterations = 0;
    private int loopCounter = 0;
    private Instruction overwritingVariable;

    public ForLoop(char variable, Expression expression, Instruction[] instructions) {
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
            Block forLoop = new Block(new VariableDeclaration[]{(new VariableDeclaration(variable, Constant.create(i)))}, instructions);
            forLoop.execute(parent);
        }
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
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
        if (instructionIndex < instructions.length) {
            instructions[instructionIndex].printNextInstruction(parent);
        }
    }

    @Override
    public void display(Block parent, int depth) {
        if (iterations == 0) {
            parent.printVariables(depth);
            return;
        }
        if (instructionIndex < instructions.length) {
            instructions[instructionIndex].display(parent, depth);
        }
    }

}