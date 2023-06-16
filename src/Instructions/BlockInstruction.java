package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.UndeclaredProcedureException;

import java.util.*;

public class BlockInstruction implements Instruction {

    private final VariableDeclaration[] variableDeclarations;
    private final Instruction[] instructions;
    private ArrayList<ProcedureDeclaration> procedures = new ArrayList<>();
    private final Map<Character, Integer> variables = new HashMap<>();
    private BlockInstruction parent;
    private int variableDeclarationIndex = -1;
    private int instructionIndex = 0;


    public BlockInstruction(VariableDeclaration[] variableDeclarations, Instruction[] instructions) {
        this.variableDeclarations = variableDeclarations;
        this.instructions = instructions;
    }


    protected void setParent(BlockInstruction parent) {
        this.parent = parent;
    }
    public void addProcedure(ProcedureDeclaration procedureDeclaration){
        procedures.add(procedureDeclaration);
    }

    @Override
    public void execute(BlockInstruction parent) throws MacchiatoException {
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
    public Boolean nextInstructionExecute(BlockInstruction parent) throws MacchiatoException {
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

    public void printNextInstruction(BlockInstruction parent) {
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

    @Override
    public void display(BlockInstruction parent, int depth) {
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

    public BlockInstruction getParent() {
        return parent;
    }

    public void printVariables(int depth) {
        System.out.println(variablesToString(depth));
    }

    public String variablesToString(int depth) {
        BlockInstruction tmp = this;
        while (depth > 0) {
            tmp = tmp.getParent();
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
