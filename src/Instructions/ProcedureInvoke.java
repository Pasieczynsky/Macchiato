package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.WrongNumberOfArgumentsException;
import Expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class ProcedureInvoke implements Instruction {
    private final String procedureName;
    private final Expression[] arguments;
    private ProcedureBlock procedureBlockInstruction = null;

    public ProcedureInvoke(String procedureName, Expression[] arguments) {
        this.procedureName = procedureName;
        this.arguments = arguments;
    }

    private void checkArguments(ProcedureDeclaration procedure) throws MacchiatoException {
        if (arguments.length != procedure.getParameters().length) {
            throw new WrongNumberOfArgumentsException("Procedure: " + procedureName
                    + " Expected number of arguments: " + procedure.getParameters().length
                    + " Given number of arguments: " + arguments.length);
        }
    }

    private void makeProcedureBlock(ProcedureDeclaration procedure) throws MacchiatoException {
        checkArguments(procedure);
        ArrayList<VariableDeclaration> parameters = new ArrayList<>();
        for (int i = 0; i < arguments.length; i++) {
            parameters.add(new VariableDeclaration(procedure.getParameters()[i], arguments[i]));
        }
        procedureBlockInstruction = new ProcedureBlock(parameters, new ArrayList<>(List.of(procedure.getProcedureDefinition())));
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        makeProcedureBlock(parent.getProcedure(procedureName));
        procedureBlockInstruction.execute(parent);
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        if (procedureBlockInstruction == null) {
            //stwórz blok procedury
            ProcedureDeclaration procedure = parent.getProcedure(procedureName);
            makeProcedureBlock(procedure);
        } else {
            //wykonaj kolejną instrukcję
            if (procedureBlockInstruction.nextInstructionExecute(parent)) {
                procedureBlockInstruction = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void printNextInstruction(Block parent) {
        if (procedureBlockInstruction == null) {
            //wypisz nazwę procedury i argumenty
            System.out.print(procedureName + "(");
            for (int i = 0; i < arguments.length; i++) {
                System.out.print(arguments[i].toString());
                if (i < arguments.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        } else {
            //wykonaj kolejną instrukcję
            procedureBlockInstruction.printNextInstruction(parent);
        }
    }

    @Override
    public void display(Block parent, int depth) {
        procedureBlockInstruction.display(parent, depth);
    }
}
