package ProgramBuilder;

import Expressions.Expression;
import Instructions.*;
import Macchiato.Macchiato;

import java.util.ArrayList;

public class ProgramBuilder {


    private BlockInstruction blockInstruction;

    public ProgramBuilder() {
        reset();
    }

    public void reset() {
        this.blockInstruction = new BlockInstruction(new ArrayList<VariableDeclaration>(), new ArrayList<Instruction>());
    }
    public ProgramBuilder declareVariable(char name, Expression value) {
        blockInstruction.addVariableDeclaration(new VariableDeclaration(name, value));
        return this;
    }
    public ProgramBuilder declareProcedure(String name, char[] args, ProcedureBlock block) {
        blockInstruction.addInstruction(new ProcedureDeclaration(name, args, block));
        return this;
    }
    public ProgramBuilder print(Expression value) {
        blockInstruction.addInstruction(new Print(value));
        return this;
    }
    public ProgramBuilder assignVariable(char name, Expression value) {
        blockInstruction.addInstruction(new VariableAssignment(name, value));
        return this;
    }
    public ProgramBuilder invoke(String name, Expression[] args) {
        blockInstruction.addInstruction(new ProcedureInvoke(name, args));
        return this;
    }
    public ProgramBuilder block(BlockInstruction block) {
        blockInstruction.addInstruction(block);
        return this;
    }

    public ProgramBuilder forLoop(char variable, Expression expression, ArrayList<Instruction> instructions) {
        blockInstruction.addInstruction(new ForLoop(variable, expression,instructions));
        return this;
    }

    public ProgramBuilder ifStatement(Expression firstExpression, Expression secondExpression, String operator,ArrayList<Instruction> trueInstructions) {
        blockInstruction.addInstruction(new IfStatement(firstExpression, secondExpression, operator, trueInstructions));
        return this;
    }
    public ProgramBuilder ifElseStatement(Expression firstExpression, Expression secondExpression, String operator,ArrayList<Instruction> trueInstructions, ArrayList<Instruction> falseInstructions) {
        blockInstruction.addInstruction(new IfStatement(firstExpression, secondExpression, operator, trueInstructions, falseInstructions));
        return this;
    }
    public Macchiato build() {
        Macchiato program = new Macchiato(this.blockInstruction);
        reset();
        return program;
    }
}
