package Instructions;

import Expressions.Constant;
import Macchiato.Macchiato;
import ProgramBuilder.ProgramBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class VariableDeclarationTest {
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
    public void testVariableDeclaration() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .build();
        program.run();
        assertEquals("x = 1\r\n", outContent.toString());
    }
    @Test
    public void testVariableRedeclaration() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(99))
                .declareVariable('x', Constant.create(1))
                .build();
        program.run();
        assertEquals("Exception: Exceptions.VariableRedeclarationException: \n var x = 1\n{x=99}\r\n", outContent.toString());
    }

}