import Expressions.*;
import Macchiato.Macchiato;
import ProgramBuilder.*;

public class Main {
    public static void main(String[] args) {
//        example();
        procedure();
    }

    public static void example() {
        Macchiato main = new ProgramBuilder()
                .declareVariable('n', Constant.create(30))
                .forLoop('k', (new Variable('n').sub(Constant.create(1))),new InstructionBuilder()
                        .block(new BlockInstructionBuilder()
                                .declareVariable('p', Constant.create(1))
                                .assignVariable('k', (new Variable('k').add(Constant.create(2))))
                                .forLoop('i',
                                        (new Variable('k').sub(Constant.create(2))),
                                        new InstructionBuilder()
                                                .assignVariable('i', (new Variable('i').add(Constant.create(2))))
                                                .ifStatement(
                                                        ((new Variable('k').mod(new Variable('i')))),
                                                        Constant.create(0),
                                                        "==",
                                                        new InstructionBuilder()
                                                                .assignVariable('p',Constant.create(0))
                                                                .build()
                                                )
                                                .build()
                                )
                                .ifStatement(new Variable('p'), Constant.create(1), "==",
                                        new InstructionBuilder()
                                                .print(new Variable('k'))
                                                .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();
        main.run();
        main.debug();
    }
    public static void procedure(){
        Macchiato main = new ProgramBuilder()
                .declareVariable('x', Constant.create(101))
                .declareVariable('y', Constant.create(1))
                .declareProcedure("out", new char[]{'a'}, new ProcedureBlockBuilder()
                        .print(new Variable('a').add(new Variable('x')))
                        .build()
                )
                .assignVariable('x', new Variable('x').sub(new Variable('y')))
                .invoke("out", new Expression[]{new Variable('x'), new Variable('y')})
                .invoke("out", new Expression[]{Constant.create(100)})
                .block(new BlockInstructionBuilder()
                        .declareVariable('x', Constant.create(10))
                        .invoke("out", new Expression[]{Constant.create(100)})
                        .build()
                )
                .build();
        main.run();

    }
}