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
        assertEquals("""
                2\r
                x = 5\r
                y = 3\r
                """, outContent.toString());
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
        assertEquals("""
                6\r
                x = 3\r
                y = 3\r
                """, outContent.toString());
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
        assertEquals("""
                7\r
                x = 2\r
                y = 5\r
                """, outContent.toString());
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
        assertEquals("""
                0\r
                x = 5\r
                y = 5\r
                """, outContent.toString());
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
        assertEquals("""
                9\r
                x = 6\r
                y = 3\r
                """, outContent.toString());
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
        assertEquals("""
                -6\r
                x = 6\r
                y = 12\r
                """, outContent.toString());
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
        assertEquals("""
                12\r
                x = 6\r
                y = 6\r
                """, outContent.toString());
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
        assertEquals("""
                -1\r
                x = 6\r
                y = 7\r
                """, outContent.toString());
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
        assertEquals("""
                12\r
                x = 6\r
                y = 6\r
                """, outContent.toString());
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
        assertEquals("""
                1\r
                x = 7\r
                y = 6\r
                """, outContent.toString());
    }
}