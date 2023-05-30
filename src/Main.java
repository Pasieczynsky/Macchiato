import Instructions.*;
import Expressions.*;
import Macchiato.Macchiato;

public class Main {
    public static void main(String[] args) {
        example();
    }
    public static void example(){
        Block main = new Block(
                new VariableDeclaration[]{new VariableDeclaration('n', Constant.create(30))},
                new Instruction[]{new ForLoop('k', (new Variable('n').sub(Constant.create(1))),
                                new Instruction[]{new Block(
                                                //variable declarations
                                                new VariableDeclaration[]{new VariableDeclaration('p', Constant.create(1))},
                                                //instructions
                                                new Instruction[]{
                                                        new VariableAssignment('k', (new Variable('k').add(Constant.create(2)))),
                                                        new ForLoop('i',
                                                                (new Variable('k').sub(Constant.create(2))),
                                                                new Instruction[]{
                                                                        new VariableAssignment('i', (new Variable('i').add(Constant.create(2)))),
                                                                        new IfStatement(
                                                                                ((new Variable('k').mod(new Variable('i')))),
                                                                                Constant.create(0),
                                                                                "=",
                                                                                new Instruction[]{new VariableAssignment('p', Constant.create(0))})
                                                                }
                                                        ),
                                                        new IfStatement((new Variable('p')), Constant.create(1), "=", new Instruction[]{new Print(new Variable('k'))})
                                                }
                                        )
                                }
                        )
                }
        );

        Macchiato program = new Macchiato(main);
        program.run();
        program.debug();
    }
}