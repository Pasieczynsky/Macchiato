package Instructions;

import Expressions.Constant;
import Expressions.Variable;
import Macchiato.Macchiato;
import ProgramBuilder.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BlockInstructionTest {
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
    public void testBlockInstruction() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .block(new BlockInstructionBuilder()
                        .declareVariable('y', Constant.create(2))
                        .print(new Variable('x').add(new Variable('y')))
                        .build()
                )
                .build();
        program.run();
        assertEquals("3\r\n" + "x = 1\r\n", outContent.toString());
    }

}