package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.UndeclaredProcedureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Block implements Instruction{

    private final VariableDeclaration[] variableDeclarations;
    private final Instruction[] instructions;
    private ArrayList<ProcedureDeclaration> procedures = new ArrayList<>();
    private final Map<Character, Integer> variables = new HashMap<>();
    private Block parent;
    private int variableDeclarationIndex = -1;
    private int instructionIndex = 0;


    public Block(VariableDeclaration[] variableDeclarations, Instruction[] instructions) {
        this.variableDeclarations = variableDeclarations;
        this.instructions = instructions;
    }
    protected void setParent(Block parent) {
        this.parent = parent;
    }
    public void addProcedure(ProcedureDeclaration procedureDeclaration){
        procedures.add(procedureDeclaration);
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        setParent(parent);
        for (VariableDeclaration variableDeclaration : variableDeclarations) {
            variableDeclaration.execute(this);
        }
        for (Instruction instruction : instructions) {
            instruction.execute(this);
        }
        if (parent == null) {
            for (VariableDeclaration variableDeclaration : variableDeclarations) {
                System.out.println(variableDeclaration.getVariable() + " = " + variables.get(variableDeclaration.getVariable()));
            }
        }
        variables.clear();
    }

    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        if(this instanceof ProcedureBlock)
            return nextIntstructionProcedure(parent);
        if (parent != this.parent)
            setParent(parent);
        if (variableDeclarationIndex == -1) {
            variableDeclarationIndex++;
            return false;
        }
        if (variableDeclarationIndex < variableDeclarations.length) {
            variableDeclarations[variableDeclarationIndex].nextInstructionExecute(this);
            variableDeclarationIndex++;
        } else if (instructionIndex < instructions.length && instructions[instructionIndex].nextInstructionExecute(this)) {
            instructionIndex++;
            return false;
        }
        if (instructionIndex == instructions.length) {
            endBlock();
            return true;
        }
        return false;
    }
    private Boolean nextIntstructionProcedure(Block parent) throws MacchiatoException{
        if(parent != this.parent)
            setParent(parent);
        if(variableDeclarationIndex + 1< variableDeclarations.length){
            variableDeclarations[variableDeclarationIndex + 1].nextInstructionExecute(this);
            variableDeclarationIndex++;
        }else if(instructionIndex < instructions.length && instructions[instructionIndex].nextInstructionExecute(this)){
            instructionIndex++;
            if(instructionIndex == instructions.length){
                endBlock();
                return true;
            }
            return false;
        }
        return false;
    }
    private void endBlock() {
        variableDeclarationIndex = -1;
        instructionIndex = 0;
        if (parent == null) {
            for (VariableDeclaration variableDeclaration : variableDeclarations) {
                System.out.println(variableDeclaration.getVariable() + " = " + variables.get(variableDeclaration.getVariable()));
            }
        }
        variables.clear();
    }
    @Override
    public void printNextInstruction(Block parent){
        if(this instanceof BlockInstruction){
            printNextInstructionBlock(parent);
        }else{
            printNextInstructionProcedure(parent);
        }
    }

    private void printNextInstructionProcedure(Block parent) {
        if (variableDeclarationIndex + 1< variableDeclarations.length ) {
            variableDeclarations[variableDeclarationIndex + 1].printNextInstruction(this);
        } else {
            if (instructionIndex < instructions.length) {
                instructions[instructionIndex].printNextInstruction(this);
            }
        }
    }

    private void printNextInstructionBlock(Block parent) {
        if (variableDeclarationIndex == -1) {
            System.out.println("Begin block");
            return;
        }
        if (variableDeclarationIndex < variableDeclarations.length) {
            variableDeclarations[variableDeclarationIndex].printNextInstruction(this);
        } else {
            if (instructionIndex < instructions.length) {
                instructions[instructionIndex].printNextInstruction(this);
            } else {
                System.out.println("End block");
            }
        }
    }

//rozne dla roznych blokow
//    @Override
    public void display(Block parent, int depth) {
        if (variableDeclarationIndex == -1) {
            parent.printVariables(depth);
            return;
        }
        if (variableDeclarationIndex < variableDeclarations.length) {
            variableDeclarations[variableDeclarationIndex].display(this, depth);
        } else {
            if (instructionIndex < instructions.length) {
                instructions[instructionIndex].display(this, depth);
            } else {
                printVariables(depth);
            }
        }
    }


    public Map<Character, Integer> getVariables() {
        return variables;
    }

    public Block getParent() {
        return parent;
    }

    public void printVariables(int depth) {
        System.out.println(variablesToString(depth));
    }
//to trzeba przerobic zeby depth liczyl sie tylko dla Block
    public String variablesToString(int depth) {
        Block tmp = this;
        while (tmp != null && depth > 0) {
            //szukamy najblizszego bloku ktorzy nie jest procedura
            while(tmp instanceof ProcedureBlock){
                tmp = tmp.getParent();
            }
            depth--;
        }
        if (tmp == null)
            return "the given number is greater than the level nesting of the current program instruction";
        Map<Character, Integer> variablesToPrint = new HashMap<>(tmp.getVariables());
        if (tmp.getParent() != null) {
            for (tmp = tmp.getParent(); tmp != null; tmp = tmp.getParent()) {
                Map<Character, Integer> variablesParent = new HashMap<>(tmp.getVariables());
                variablesParent.putAll(variablesToPrint);
                variablesToPrint = variablesParent;
            }
        }
        return variablesToPrint.toString();
    }

    public ProcedureDeclaration getProcedure(String procedureName) throws UndeclaredProcedureException {
        for (ProcedureDeclaration procedureDeclaration : procedures) {
            if (procedureDeclaration.getProcedureName().equals(procedureName)) {
                return procedureDeclaration;
            }
        }
        //szukanie w bloku nadrzednym
        if (parent != null) {
            return parent.getProcedure(procedureName);
        }
        throw new UndeclaredProcedureException(procedureName);

    }
}
