package Instructions;

import Expressions.Constant;
import Expressions.Variable;
import Macchiato.Macchiato;
import ProgramBuilder.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IfStatementTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void testIfStatementNotEquals() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(5))
                .declareVariable('y', Constant.create(3))
                .ifElseStatement(new Variable('x'),new Variable('y'),"==",
                        new InstructionBuilder()
                            .print(new Variable('x').add(new Variable('y')))
                            .build(),
                        new InstructionBuilder()
                            .print(new Variable('x').sub(new Variable('y')))
                            .build()
                )
                .build();
        program.run();
        assertEquals("2\r\n" + "x = 5\r\n" + "y = 3\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementEquals() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(3))
                .declareVariable('y', Constant.create(3))
                .ifElseStatement(new Variable('x'),new Variable('y'),"==",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("6\r\n" + "x = 3\r\n" + "y = 3\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementLess() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(2))
                .declareVariable('y', Constant.create(5))
                .ifElseStatement(new Variable('x'),new Variable('y'),"<",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("7\r\n" + "x = 2\r\n" + "y = 5\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementNotLess() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(5))
                .declareVariable('y', Constant.create(5))
                .ifElseStatement(new Variable('x'),new Variable('y'),"<",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("0\r\n" + "x = 5\r\n" + "y = 5\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementGreater() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(6))
                .declareVariable('y', Constant.create(3))
                .ifElseStatement(new Variable('x'),new Variable('y'),">",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("9\r\n" + "x = 6\r\n" + "y = 3\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementNotGreater() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(6))
                .declareVariable('y', Constant.create(12))
                .ifElseStatement(new Variable('x'),new Variable('y'),">",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("-6\r\n" + "x = 6\r\n" + "y = 12\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementGreaterOrEqual() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(6))
                .declareVariable('y', Constant.create(6))
                .ifElseStatement(new Variable('x'),new Variable('y'),">=",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("12\r\n" + "x = 6\r\n" + "y = 6\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementNotGreaterOrEqual() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(6))
                .declareVariable('y', Constant.create(7))
                .ifElseStatement(new Variable('x'),new Variable('y'),">=",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("-1\r\n" + "x = 6\r\n" + "y = 7\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementLessOrEqual() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(6))
                .declareVariable('y', Constant.create(6))
                .ifElseStatement(new Variable('x'),new Variable('y'),"<=",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("12\r\n" + "x = 6\r\n" + "y = 6\r\n", outContent.toString());
    }
    @Test
    public void testIfStatementNotLessOrEqual() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(7))
                .declareVariable('y', Constant.create(6))
                .ifElseStatement(new Variable('x'),new Variable('y'),"<",
                        new InstructionBuilder()
                                .print(new Variable('x').add(new Variable('y')))
                                .build(),
                        new InstructionBuilder()
                                .print(new Variable('x').sub(new Variable('y')))
                                .build()
                )
                .build();
        program.run();
        assertEquals("1\r\n" + "x = 7\r\n" + "y = 6\r\n", outContent.toString());
    }
}