package Instructions;

import Expressions.Constant;
import Expressions.Variable;
import Macchiato.Macchiato;
import ProgramBuilder.ProgramBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {
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
    public void testPrint() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .print(new Variable('x'))
                .build();
        program.run();
        assertEquals("""
                1
                x = 1
                """, outContent.toString());
    }

}