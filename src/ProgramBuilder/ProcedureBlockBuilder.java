package ProgramBuilder;

import Expressions.Expression;
import Instructions.*;
import Instructions.ProcedureBlock;
import Instructions.VariableDeclaration;

import java.util.ArrayList;

public class ProcedureBlockBuilder{
    private ProcedureBlock procedureBlock;
    public void reset() {
        this.procedureBlock = new ProcedureBlock(new ArrayList<VariableDeclaration>(), new ArrayList<Instruction>()) ;
    }
    public ProcedureBlockBuilder() {
        reset();
    }
    public ProcedureBlockBuilder declareVariable(char name, Expression value) {
        this.procedureBlock.addVariableDeclaration(new VariableDeclaration(name, value));
        return this;
    }

    public ProcedureBlockBuilder print(Expression value) {
        this.procedureBlock.addInstruction(new Print(value));
        return this;
    }

    public ProcedureBlockBuilder assignVariable(char name, Expression value) {
        this.procedureBlock.addInstruction(new VariableAssignment(name, value));
        return this;
    }

    public ProcedureBlockBuilder invoke(String name, Expression[] args) {
        this.procedureBlock.addInstruction(new ProcedureInvoke(name, args));
        return this;
    }

    public ProcedureBlockBuilder ifElseStatement(Expression firstExpression, Expression secondExpression, String operator, ArrayList<Instruction> trueInstructions, ArrayList<Instruction> falseInstructions) {
        this.procedureBlock.addInstruction(new IfStatement(firstExpression, secondExpression, operator, trueInstructions, falseInstructions));
        return this;
    }

    public ProcedureBlockBuilder ifStatement(Expression firstExpression, Expression secondExpression, String operator, ArrayList<Instruction> trueInstructions) {
        this.procedureBlock.addInstruction(new IfStatement(firstExpression, secondExpression, operator, trueInstructions));
        return this;
    }

    public ProcedureBlockBuilder block(BlockInstruction block) {
        this.procedureBlock.addInstruction(block);
        return this;
    }

    public ProcedureBlockBuilder forLoop(char variable, Expression expression, ArrayList<Instruction> instructions) {
        this.procedureBlock.addInstruction(new ForLoop(variable, expression, instructions));
        return this;
    }

    public ProcedureBlock build() {
        ProcedureBlock product = this.procedureBlock;
        reset();
        return product;
    }
}
