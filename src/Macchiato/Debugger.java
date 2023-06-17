package Macchiato;

import Exceptions.MacchiatoException;
import Instructions.BlockInstruction;

import java.util.Scanner;

public class Debugger {
    private final Scanner scanner = new Scanner(System.in);
    private final BlockInstruction program;
    private boolean working = true;
    boolean programFinished = false;
    private int number = 0;

    public Debugger(BlockInstruction program) {
        this.program = program;
    }

    public void debugExecute() throws MacchiatoException {
        System.out.println("Debug mode write 'c' to continue, 's' to step, 'd' to display, 'e' to exit");
        while (working) {
            String token = scanner.next();
            switch (token) {
                case "c" -> {
                    System.out.println("Continue");
                    while (!programFinished) {
                        programFinished = program.nextInstructionExecute(null);
                    }
                }
                case "s" -> {
                    number = scanner.nextInt();
                    System.out.println("Step " + number);
                    for(int i = 0; i < number && !programFinished; i++){
                       programFinished = program.nextInstructionExecute(null);
                    }
                    if(!programFinished)
                        program.printNextInstruction(null);
                }
                case "d" -> {
                    number = scanner.nextInt();
                    System.out.println("Display " + number);
                    program.display(null, number);
                }
                case "e" -> {
                    System.out.println("Exit");
                    working = false;
                }
//                Debugger dla Macchiato w wersji 1.1 zawiera wsparcie dla nowego polecenia dump,
//                umożliwiającego wykonanie zrzutu pamięci programu do pliku.
//                Polecenie to ma symbol m i wymaga jednego parametru będącego ścieżką do pliku.
//                Efektem działania polecenia powinno być zapisanie w podanym pliku w formie tekstowej zrzutu pamięci programu.
//                Na zrzut pamięci programu składają się:
//                  widoczne deklaracje procedur, to jest ich nazwy wraz z nazwami parametrów (bez treści),
//                  bieżące wartościowanie zmiennych (takie jak w poleceniu d 0).
                case "m" -> {
                    String path = scanner.next();
                    System.out.println("Dump " + path);
                    program.dump(path);
                }
                default -> System.out.println("Unknown command");
            }
            if(programFinished){
                System.out.println("Program finished");
            }
        }
    }
}
