package Instructions;

import Expressions.Constant;
import Expressions.Expression;
import Expressions.Variable;
import Macchiato.Macchiato;
import ProgramBuilder.ProgramBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class VariableAssignmentTest {
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
    public void testVariableAssignment() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(99))
                .declareVariable('y', Constant.create(1))
                .assignVariable('x', new Variable('y'))
                .print(new Variable('x'))
                .build();
        program.run();
        assertEquals("""
                1\r
                x = 1\r
                y = 1\r
                """, outContent.toString());
    }

    @Test
    public void testUndeclaredVariableAssignment() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(99))
                .declareVariable('y', Constant.create(1))
                .assignVariable('z', new Variable('y'))
                .build();
        program.run();
        assertEquals("Exception: Exceptions.UndeclaredVariableException: \n z := y\n {x=99, y=1}\r\n", outContent.toString());
    }

}