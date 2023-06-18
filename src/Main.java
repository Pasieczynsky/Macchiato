import Instructions.*;
import Expressions.*;
import Instructions.BlockInstruction;
import Macchiato.Macchiato;
import ProgramBuilder.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        builder();
        //        example1();
//        example1Builder();
//        procedureExampleBuilder();
//        procedureExample();
//        procedureExample2();
    }
    public static void example(){
//        BlockInstruction main = new BlockInstruction(
//                new VariableDeclaration[]{new VariableDeclaration('n', Constant.create(30))},
//                new Instruction[]{new ForLoop('k', (new Variable('n').sub(Constant.create(1))),
//                                new Instructions.Instruction[]{new BlockInstruction(
//                                                //variable declarations
//                                                new VariableDeclaration[]{new VariableDeclaration('p', Constant.create(1))},
//                                                //instructions
//                                                new Instructions.Instruction[]{
//                                                        new VariableAssignment('k', (new Variable('k').add(Constant.create(2)))),
//                                                        new ForLoop('i',
//                                                                (new Variable('k').sub(Constant.create(2))),
//                                                                new ArrayList<>(List.of(
//                                                                        new VariableAssignment('i', (new Variable('i').add(Constant.create(2)))),
//                                                                        new IfStatement(
//                                                                                ((new Variable('k').mod(new Variable('i')))),
//                                                                                Constant.create(0),
//                                                                                "=",
//                                                                                new ArrayList<>(List.of(new VariableAssignment('p',Constant.create(0)))) )
////                                                                        new IfStatement(((new Variable('k').mod(new Variable('i')))),){
////                                                                        new VariableAssignment('i', (new Variable('i').add(Constant.create(2)))),
////                                                                        new IfStatement(
////                                                                                ((new Variable('k').mod(new Variable('i')))),
////                                                                                Constant.create(0),
////                                                                                "=",
////                                                                                new ArrayList<>(List.of(new VariableAssignment('p',Constant.create(0)))) )
////                                                                }
//                                                        ),
//                                                        new IfStatement(
//                                                                (new Variable('p')), Constant.create(1), "=",
//                                                                new ArrayList<>(List.of(new Print(new Variable('k')))))
////                                                                new Instruction[]{new Print(new Variable('k'))})
//                                                }
//                                        )
//                                }
//                        )
//                }
//        );
//
//        Macchiato program = new Macchiato(main);
//        program.run();
//        program.debug();
    }
    public static void example1(){
        BlockInstruction main = new BlockInstruction(
                new ArrayList<>(List.of(new VariableDeclaration('n', Constant.create(30)))),
                new ArrayList<>(List.of(new ForLoop('k', (new Variable('n').sub(Constant.create(1))),
                        new ArrayList<>(List.of(new BlockInstruction(
                                //variable declarations
                                new ArrayList<>(List.of(new VariableDeclaration('p', Constant.create(1)))),
                                //instructions
                                new ArrayList<>(List.of(
                                        new VariableAssignment('k', (new Variable('k').add(Constant.create(2)))),
                                        new ForLoop('i',
                                                (new Variable('k').sub(Constant.create(2))),
                                                new ArrayList<>(List.of(
                                                        new VariableAssignment('i', (new Variable('i').add(Constant.create(2)))),
                                                        new IfStatement(
                                                                ((new Variable('k').mod(new Variable('i')))),
                                                                Constant.create(0),
                                                                "=",
                                                                new ArrayList<>(List.of(new VariableAssignment('p',Constant.create(0)))) )
                ))
        ),new IfStatement(new Variable('p'), Constant.create(1), "=",new ArrayList<>(List.of(new Print(new Variable('k'))))))))))))));
//                                        )))))))));
        Macchiato program = new Macchiato(main);
        program.run();
        program.debug();
    }
    public static void example1Builder() {
        BlockInstruction main = new BlockInstructionBuilder()
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
                                                        "=",
                                                        new InstructionBuilder()
                                                                .assignVariable('p',Constant.create(0))
                                                                .build()
                                                )
                                                .build()
                                )
                                .ifStatement(new Variable('p'), Constant.create(1), "=",
                                        new InstructionBuilder()
                                                .print(new Variable('k'))
                                                .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();

        Macchiato program = new Macchiato(main);
        program.run();
    }



//    begin block
//    var x 101
//    var y 1
//    proc out(a)
//    print a+x
//    end proc
//    x := x - y
//    out(x)
//    out(100)  // tu powinno wypisać 200
//      begin block
//      var x 10
//      out(100) // tu statycznie wciąż 200, dynamicznie 110
//      end block
//    end block
//    mogłoby przebiegać na przykład tak:
//
//    var program = new ProgramBuilder()
//            .declareVariable('x', Constant.of(101))
//            .declareVariable('y', Constant.of(1))
//            .declareProcedure('out', List.of('a'), new BlockBuilder()
//                    .print(Addition.of(Variable.named('a'), Variable.named('x')))
//                    .build()
//            )
//            .assign('x', Subtraction.of(Variable.named('x'), Variable.named('y')))
//            .invoke('out', List.of(Variable.named('x')))
//            .invoke('out', List.of(Constant.of(100)))
//            .block(new BlockBuilder()
//                    .declareVariable('x', Constant.of(10))
//                    .invoke('out', List.of(Constant.of(100)))
//                    .build()
//            )
//            .build();
    public static void procedureExample(){
        BlockInstruction main = new BlockInstruction(
                    new ArrayList<>(List.of(new VariableDeclaration('x', Constant.create(101)),
                                            new VariableDeclaration('y', Constant.create(1)))),
                    new ArrayList<>(List.of(
                            new ProcedureDeclaration("out", new char[]{'a'},
                                new ProcedureBlock(
                                        new ArrayList<>(),
                                        new ArrayList<>(List.of(new Print(new Variable('a').add(new Variable('x')))))
                                )
                            ),
                            new VariableAssignment('x', (new Variable('x').sub(new Variable('y')))),
                            new ProcedureInvoke("out", new Expressions.Expression[]{new Variable('x')}),
                            new ProcedureInvoke("out", new Expressions.Expression[]{Constant.create(100)}),
                            new BlockInstruction(
                                new ArrayList<>(List.of(new VariableDeclaration('x', Constant.create(10)))),
                                new ArrayList<>(List.of(new ProcedureInvoke("out", new Expressions.Expression[]{Constant.create(100)})))
                                )
                            )
                    )
        );

        Macchiato program = new Macchiato(main);
//        program.run();
        program.debug();
    }
    public static void procedureExample2(){
//        BlockInstruction main = new BlockInstruction(
//                new VariableDeclaration[]{new VariableDeclaration('x', Constant.create(101)),
//                        new VariableDeclaration('y', Constant.create(1))},
//                new Instructions.Instruction[]{
//                        new ProcedureDeclaration("out", new char[]{'a', 'b'},
//                                new ProcedureBlock(
//                                        new VariableDeclaration[]{},
//                                        new Instructions.Instruction[]{
//                                                new Print(new Variable('b').add(new Variable('x'))),
//                                                new Print(new Variable('a').add(new Variable('x')))})),
//                        new ProcedureDeclaration("outn", new char[]{'a', 'b'},
//                                new ProcedureBlock(
//                                        new VariableDeclaration[]{},
//                                        new Instructions.Instruction[]{
//                                                new Print(new Variable('b').add(new Variable('x'))),
//                                                new Print(new Variable('a').add(new Variable('x')))})),
//                        new VariableAssignment('x', new Variable('x').sub(new Variable('y'))),
//                        new ProcedureInvoke("out", new Expressions.Expression[]{new Variable('x'), Constant.create(300)}),
//                        new ProcedureInvoke("out", new Expressions.Expression[]{Constant.create(100),Constant.create(200)}),
//                        new BlockInstruction(
//                                new VariableDeclaration[]{new VariableDeclaration('x', Constant.create(10))},
//                                new Instructions.Instruction[]{new ProcedureInvoke("out", new Expressions.Expression[]{Constant.create(100), Constant.create(400)})})
//                }
//        );

//        Macchiato program = new Macchiato(main);
////        program.run();
//        program.debug();
    }
    public static void procedureExampleBuilder() {
        BlockInstruction main = new BlockInstructionBuilder()
                .declareVariable('x', Constant.create(101))
                .declareVariable('y', Constant.create(1))
                .declareProcedure("out", new char[]{'a'}, new ProcedureBlockBuilder()
                        .print(new Variable('a').add(new Variable('x')))
                        .build()
                )
                .assignVariable('x', new Variable('x').sub(new Variable('y')))
                .invoke("out", new Expression[]{new Variable('x')})
                .invoke("out", new Expression[]{Constant.create(100)})
                .block(new BlockInstructionBuilder()
                        .declareVariable('x', Constant.create(10))
                        .invoke("out", new Expression[]{Constant.create(100)})
                        .build()
                )
                .build();

        Macchiato program = new Macchiato(main);
        program.debug();
        program.run();
    }
    public static void builder(){
        Macchiato main = new ProgramBuilder()
                .declareVariable('x', Constant.create(101))
                .declareVariable('y', Constant.create(1))
                .declareProcedure("out", new char[]{'a'}, new ProcedureBlockBuilder()
                        .print(new Variable('a').add(new Variable('x')))
                        .build()
                )
                .assignVariable('x', new Variable('x').sub(new Variable('y')))
                .invoke("out", new Expression[]{new Variable('x')})
                .invoke("out", new Expression[]{Constant.create(100)})
                .block(new BlockInstructionBuilder()
                        .declareVariable('x', Constant.create(10))
                        .invoke("out", new Expression[]{Constant.create(100)})
                        .build()
                )
                .build();
        main.debug();

    }
}