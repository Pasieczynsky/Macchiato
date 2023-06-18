package ProgramBuilder;

import Expressions.Expression;
import Instructions.*;

import java.util.ArrayList;

public class BlockInstructionBuilder{

    private BlockInstruction blockInstruction;

    public BlockInstructionBuilder() {
        reset();
    }

    public void reset() {
         this.blockInstruction = new BlockInstruction(new ArrayList<VariableDeclaration>(), new ArrayList<Instruction>());
    }
    public BlockInstructionBuilder declareVariable(char name, Expression value) {
        blockInstruction.addVariableDeclaration(new VariableDeclaration(name, value));
        return this;
    }
    public BlockInstructionBuilder declareProcedure(String name, char[] args, ProcedureBlock block) {
        blockInstruction.addInstruction(new ProcedureDeclaration(name, args, block));
        return this;
    }
    public BlockInstructionBuilder print(Expression value) {
        blockInstruction.addInstruction(new Print(value));
        return this;
    }
    public BlockInstructionBuilder assignVariable(char name, Expression value) {
        blockInstruction.addInstruction(new VariableAssignment(name, value));
        return this;
    }
    public BlockInstructionBuilder invoke(String name, Expression[] args) {
        blockInstruction.addInstruction(new ProcedureInvoke(name, args));
        return this;
    }
    public BlockInstructionBuilder block(BlockInstruction block) {
        blockInstruction.addInstruction(block);
        return this;
    }

    public BlockInstructionBuilder forLoop(char variable, Expression expression, ArrayList<Instruction> instructions) {
        blockInstruction.addInstruction(new ForLoop(variable, expression,instructions));
        return this;
    }

    public BlockInstructionBuilder ifStatement(Expression firstExpression, Expression secondExpression, String operator,ArrayList<Instruction> trueInstructions) {
        blockInstruction.addInstruction(new IfStatement(firstExpression, secondExpression, operator, trueInstructions));
        return this;
    }
    public BlockInstructionBuilder ifElseStatement(Expression firstExpression, Expression secondExpression, String operator,ArrayList<Instruction> trueInstructions, ArrayList<Instruction> falseInstructions) {
        blockInstruction.addInstruction(new IfStatement(firstExpression, secondExpression, operator, trueInstructions, falseInstructions));
        return this;
    }
    public BlockInstruction build() {
        BlockInstruction product = this.blockInstruction;
        reset();
        return product;
    }

}
