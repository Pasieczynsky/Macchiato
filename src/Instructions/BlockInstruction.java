package Instructions;

import Exceptions.MacchiatoException;
import Exceptions.UndeclaredProcedureException;

import java.util.*;

public class BlockInstruction extends Block {

//    private final VariableDeclaration[] variableDeclarations;
//    private final Instruction[] instructions;
//    private ArrayList<ProcedureDeclaration> procedures = new ArrayList<>();
//    private final Map<Character, Integer> variables = new HashMap<>();
//    private Block parent;
//    private int variableDeclarationIndex = -1;
//    private int instructionIndex = 0;


    public BlockInstruction(VariableDeclaration[] variableDeclarations, Instruction[] instructions) {
        super(variableDeclarations, instructions);
    }

}
