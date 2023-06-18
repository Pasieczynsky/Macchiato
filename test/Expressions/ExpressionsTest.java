package Expressions;

import Macchiato.Macchiato;
import ProgramBuilder.ProgramBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionsTest {

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
    public void testAdd() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .print(new Variable('x').add(Constant.create(5)))
                .build();
        program.run();
        assertEquals("""
                6\r
                x = 1\r
                """, outContent.toString());
    }
    @Test
    public void testConstant() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .build();
        program.run();
        assertEquals("x = 1\r\n", outContent.toString());
    }
    @Test
    public void testDiv() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(10))
                .print(new Variable('x').div(Constant.create(5)))
                .build();
        program.run();
        assertEquals("""
                2\r
                x = 10\r
                """, outContent.toString());
    }
    @Test
    public void testMod(){
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(10))
                .print(new Variable('x').mod(Constant.create(5)))
                .build();
        program.run();
        assertEquals("""
                0\r
                x = 10\r
                """, outContent.toString());
    }
    @Test
    public void testMul() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(10))
                .print(new Variable('x').multiply(Constant.create(5)))
                .build();
        program.run();
        assertEquals("""
                50\r
                x = 10\r
                """, outContent.toString());
    }
    @Test
    public void testSub() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(10))
                .print(new Variable('x').sub(Constant.create(5)))
                .build();
        program.run();
        assertEquals("""
                5\r
                x = 10\r
                """, outContent.toString());
    }
    @Test
    public void testVariable() {
        Macchiato program = new ProgramBuilder()
                .declareVariable('x', Constant.create(1))
                .declareVariable('y', new Variable('x'))
                .build();
        program.run();
        assertEquals("""
                x = 1\r
                y = 1\r
                """, outContent.toString());
    }
}
