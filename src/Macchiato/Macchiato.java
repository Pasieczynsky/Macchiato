package Macchiato;

import Exceptions.MacchiatoException;
import Instructions.BlockInstruction;

public class Macchiato {
    private final BlockInstruction program;

    public Macchiato(BlockInstruction program) {
        this.program = program;
    }
    public void run(){
        try{
            program.execute(null);
        } catch (MacchiatoException e){
            System.out.println("Exception: " + e);
        }

    }
    public void debug(){
        Debugger debugger = new Debugger(program);

        try {
            debugger.debugExecute();
        } catch (MacchiatoException e) {
            System.out.println("Exception: " + e);
        }
    }
}
