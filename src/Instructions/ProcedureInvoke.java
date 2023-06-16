package Instructions;

import Exceptions.MacchiatoException;
import Expressions.Instruction;

public class ProcedureInvoke implements Instructions.Instruction {
    private final String procedureName;
    private final Instruction[] arguments;
    private BlockInstruction procedureBlockInstruction = null;
//    private int instructionIndex = -1;
    public ProcedureInvoke(String procedureName, Instruction[] arguments){
        this.procedureName = procedureName;
        this.arguments = arguments;
    }
    private void checkArguments(BlockInstruction parent, ProcedureDeclaration procedure) throws MacchiatoException{
        if(arguments.length != procedure.getParameters().length){
            throw new MacchiatoException("Wrong number of arguments");
        }
    }
    private void makeProcedureBlock(BlockInstruction parent, ProcedureDeclaration procedure) throws MacchiatoException{
        checkArguments(parent,procedure);
        VariableDeclaration [] parameters = new VariableDeclaration[arguments.length];
        for(int i = 0; i < arguments.length; i++){
            parameters[i] = new VariableDeclaration(procedure.getParameters()[i], arguments[i]);
        }
        procedureBlockInstruction = new BlockInstruction(parameters, new BlockInstruction[]{procedure.getProcedureDefinition()});
    }
    @Override
    public void execute(BlockInstruction parent) throws MacchiatoException {
        ProcedureDeclaration procedure = parent.getProcedure(procedureName);
        makeProcedureBlock(parent,procedure);
        VariableDeclaration [] parameters = new VariableDeclaration[arguments.length];
        for(int i = 0; i < arguments.length; i++){
            parameters[i] = new VariableDeclaration(procedure.getParameters()[i], arguments[i]);
        }
        procedureBlockInstruction = new BlockInstruction(parameters, new BlockInstruction[]{procedure.getProcedureDefinition()});
        procedureBlockInstruction.execute(parent);
    }

    @Override
    public Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException {
        if(procedureBlockInstruction == null){
            //stwórz blok procedury
            ProcedureDeclaration procedure = parent.getProcedure(procedureName);
            makeProcedureBlock(parent,procedure);
        }else{
            //wykonaj kolejną instrukcję
            if(procedureBlockInstruction.nextInstructionExecute(parent)){
                procedureBlockInstruction = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void printNextInstruction(BlockInstruction parent) {
        if(procedureBlockInstruction == null){
            //wypisz nazwę procedury i argumenty
            System.out.print(procedureName + "(");
            for(int i = 0; i < arguments.length; i++){
                System.out.print(arguments[i].toString());
                if(i < arguments.length - 1){
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }else{
            //wykonaj kolejną instrukcję
            procedureBlockInstruction.printNextInstruction(parent);
        }
    }

    @Override
    public void display(BlockInstruction parent, int depth) {

    }
}
