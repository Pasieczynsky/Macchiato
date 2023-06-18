package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Expression;

public class Print implements Instructions.Instruction {
    private final Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        int value = expression.evaluate(parent,this );
        System.out.println(value);
    }
    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(Block parent) {
        System.out.println("print " + expression.toString());
    }

    @Override
    public void display(Block parent, int depth) {
        parent.printVariables(depth);
    }
}
