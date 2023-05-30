package Instructions;

import java.util.Map;

import Exceptions.MacchiatoException;
import Exceptions.VariableRedeclarationException;
import Expressions.Expression;

public class VariableDeclaration implements Instruction {
    private final char variable;
    private final Expression expression;

    public VariableDeclaration(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        Map<Character, Integer> variables = parent.getVariables();
        if (variables.containsKey(variable)) {
            throw new VariableRedeclarationException("\n " + this + "\n" + parent.variablesToString(0));
        }
        int value = expression.evaluate(parent, this);
        variables.put(variable, value);
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(Block parent) {
        System.out.println(this);
    }

    @Override
    public void display(Block parent, int depth) {
        parent.printVariables(depth);
    }
    @Override
    public String toString() {
        return ("var " + variable + " = " + expression.toString());
    }
    protected Character getVariable() {
        return variable;
    }
}
