package Instructions;

import Expressions.*;
import Macchiato.Macchiato;
import ProgramBuilder.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ForLoopTest {
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
    public void testForLoop() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .forLoop('i',Constant.create(10), new InstructionBuilder()
                        .print(new Variable('x').add(new Variable('i')))
                        .build()
                )
                .build();
        program.run();
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            expected.append(i + 1).append("\n");
        }
        expected.append("x = 1\n");
        assertEquals(expected.toString(), outContent.toString());
    }

}