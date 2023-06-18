package ProgramBuilder;

import Expressions.Expression;
import Instructions.*;

import java.util.ArrayList;

public class InstructionBuilder {

    private ArrayList<Instruction> instructions;
    public InstructionBuilder() {
        reset();
    }
    public void reset() {
        this.instructions = new ArrayList<Instruction>();
    }

    public InstructionBuilder print(Expression value) {
        this.instructions.add(new Print(value));
        return this;
    }

    public InstructionBuilder assignVariable(char name, Expression value) {
        this.instructions.add(new VariableAssignment(name, value));
        return this;
    }

    public InstructionBuilder invoke(String name, Expression[] args) {
        this.instructions.add(new ProcedureInvoke(name, args));
        return this;
    }

    public InstructionBuilder block(BlockInstruction block) {
        this.instructions.add(block);
        return this;
    }

    public InstructionBuilder ifElseStatement(Expression firstExpression, Expression secondExpression, String operator, ArrayList<Instruction> trueInstructions, ArrayList<Instruction> falseInstructions) {
        this.instructions.add(new IfStatement(firstExpression, secondExpression, operator, trueInstructions, falseInstructions));
        return this;
    }

    public InstructionBuilder ifStatement(Expression firstExpression, Expression secondExpression, String operator, ArrayList<Instruction> trueInstructions) {
        this.instructions.add(new IfStatement(firstExpression, secondExpression, operator, trueInstructions));
        return this;
    }

    public InstructionBuilder forLoop(char variable, Expression expression, InstructionBuilder instructions) {
        this.instructions.add(new ForLoop(variable, expression, instructions.build()));
        return this;
    }

    public ArrayList<Instruction> build() {
        ArrayList<Instruction> product = this.instructions;
        reset();
        return product;
    }
}
