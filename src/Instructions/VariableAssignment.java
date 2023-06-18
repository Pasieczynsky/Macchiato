package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.UndeclaredVariableException;
import Expressions.Expression;

import java.util.Map;

public class VariableAssignment implements Instructions.Instruction {
    private final char variable;
    private final Expression expression;

    public VariableAssignment(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        Map<Character, Integer> variables = null;
        Block tmp;
        for(tmp = parent; tmp != null; tmp = tmp.getParent()) {
            variables = tmp.getVariables();
            if (variables.containsKey(variable)) {
                break;
            }
        }
        if(tmp == null){
            throw new UndeclaredVariableException("\n " + this + "\n " + parent.variablesToString(0));
        }
        int value = expression.evaluate(parent, this);
        variables.replace(variable, value);
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException{
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(Block parent) {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return (variable + " := " + expression.toString());
    }
    @Override
    public void display(Block parent, int depth) {
        parent.printVariables(depth);
    }
}
