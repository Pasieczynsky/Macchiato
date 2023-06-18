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

class ProcedureDeclarationTest {
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
    public void testProcedureDeclaration() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .declareProcedure("out", new char[]{'a'}, new ProcedureBlockBuilder()
                        .print(new Variable('a').add(new Variable('x')))
                        .build()
                )
                .declareProcedure("out", new char[]{'b'}, new ProcedureBlockBuilder()
                        .print(new Variable('b').sub(new Variable('x')))
                        .build()
                )
                .build();
        program.run();
        assertEquals("Exception: Exceptions.ProcedureRedeclarationException: Procedure: out (b)\n" +
                "{x=1}\r\n", outContent.toString());
    }

}