package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.ProcedureRedeclarationException;


public class ProcedureDeclaration implements Instruction{
    private final String name;
    private final char [] parameters;
    private final ProcedureBlock definition;

    public ProcedureDeclaration(String name, char [] parameters, ProcedureBlock blockInstruction){
        this.name = name;
        this.parameters = parameters;
        this.definition = blockInstruction;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        for(ProcedureDeclaration procedureDeclaration : parent.getProcedures()){
            if(procedureDeclaration.getProcedureName().equals(name)){
                throw new ProcedureRedeclarationException(this + "\n" + parent.variablesToString(0));
            }
        }
        parent.addProcedure(this);
    }
    public String getName(){
        return name;
    }
    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(Block parent) {
        String params = getParametersString();
        System.out.println("Proc " + name + " (" + params  + ")" );
    }
    private String getParametersString(){
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            params.append(parameters[i]);
            if(i != parameters.length - 1){
                params.append(", ");
            }
        }
        return params.toString();
    }
    @Override
    public void display(Block parent, int depth) {
        parent.printVariables(depth);
    }

    public String getProcedureName() {
        return name;
    }
    public ProcedureBlock getProcedureDefinition(){
        return definition;
    }
    public char [] getParameters(){
        return parameters;
    }
    @Override
    public String toString(){
        return "Procedure: " + name + " (" + getParametersString() + ")";
    }
}
