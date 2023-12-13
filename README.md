# Macchiato Lungo

A project simulating the operation of a simple programming language, written for the object-oriented programming course at the University of Warsaw.

## How to start?

The program offers two modes for execution:

1) Run without Debugging:  
In this mode, the program is executed from beginning to end (unless
an execution error will occur, but even then we only print a message and finish execution,
without running the debugger)

2) Run with Debugging:  
This mode initiates a pause in the program's execution before the first instruction is executed. During this pause, the program awaits commands from the standard input.

## Debugger commands

Set of debugger commands:

- c(ontinue):  
Parameterless command, resumes the debugging process. If the program has already completed, the command prints an appropriate message and does nothing else.
- s(tep) \<number>  
Debugger makes exactly \<number> steps. Each step corresponds to the execution of a single instruction (including the execution of all nested component instructions). After executing the specified number of steps, the debugger prints the instruction (possibly complex) that is to be executed next. If the execution reaches the end of the program before completing the specified number of instructions, only an appropriate message is printed, and the program ends normally.
- d(isplay) \<number>  
Displays the current valuation. The parameter indicates how many levels higher in the block hierarchy the valuation of variables should be displayed. The command d 0 signifies displaying the current valuation. If the given number is greater than the level of nesting of the current program instruction, only an appropriate message is printed.
- e(xit)  
Terminates the execution of the program and concludes the debugger's operation. The final valuation of variables is not printed.
- m(emory dump) \<file path>  
Creates a memory dump of the program and saves it to the specified file path. The memory dump includes visible procedure declarations (names and parameter names) and the current valuation of variables, similar to the d 0 command.

## Programs may contain the following instructions

- Block:  
A block consists of variable declarations and a sequence of instructions. Each of these parts can be empty. Declarations within a block are visible from their declaration point until the end of their block (and nowhere else). Local declarations can override external declarations.

- For Loop \<variable> \<expression> \<instructions>:  
Executes the instructions \<value times>, and during each iteration, \<instructions> are executed in a block with \<variable> taking successive values from the range 0 to \<value of the expression>-1. The value of the expression is calculated only once, just before the loop starts (so even if the executed instructions change this value, the number of loop iterations and the variable's values at the beginning of each iteration won't change). If the calculated value of the expression is less than or equal to zero, the loop doesn't perform any iterations. An error in the expression calculation interrupts further loop execution (the instructions in the loop are not executed even once).

- Conditional Statement if \<expr1> =|<>|<|>|<=|>= \<expr2> then \<instructions> else \<instructions>:  
Standard meaning.
First, we evaluate the first and then the second expression.
An error in the expression calculation interrupts further execution of this statement.
The else \<instructions> part can be omitted.

- Assigning a Value to a Variable \<name> \<expr>:  
Assigns the variable a value equal to the calculated value of the expression.
An error in the expression calculation interrupts further execution of this instruction (i.e., in such a situation, the variable's value remains unchanged).
Ends with an error if there is no visible declared variable with the given name at this point in the program.

- Print Expression Value print \<expr>:  
Evaluates the expression value and then prints it on the standard output in the next line.
If the expression evaluation fails, this instruction does not print anything.  

Within blocks, the following declarations occur:

- Variable Declaration \<name> \<expr>:  
Introduces a variable into the current scope of visibility (associated with the block containing this declaration) and initializes it with the calculated value of the expression.  
In one block, you cannot declare two (or more) variables with the same name.  
Variable names from different blocks can be the same, and variables may override (instructions and expressions always see the variable declared in the most closely enclosing block).  
Variables can only be declared at the beginning of a block (i.e., within variable declarations).  
An error in the expression calculation interrupts further processing of declarations (i.e., in such a situation, the variable is not declared).  

If an error occurs during the execution of an instruction, the program execution is interrupted, and a message containing the values of all variables visible in the block where the error occurred (these variables can be declared in this or surrounding blocks) is printed. Additionally, the instruction directly causing the error is printed.

Macchiato 1.1 introduces procedures, functions without a return value. The declaration includes the name, parameters (of integer type), and the body. Procedures are similar to variables: declarations are at the beginning of the block, visible until its end, can be passed as arguments. It's not allowed to declare two procedures with the same name in one block. Calling a procedure executes its body with dynamic variable binding.

## Expressions in Macchiato

In the Macchiato language, all expressions have integer values. An expression can take one of the following forms:

- Integer Literal:  
 The value of such an expression is the value of the literal. The syntax and range of literals are the same as in Java for the int type.

- Variable: The value of such an expression is the value of the variable visible at that point in the program. If there is no visible variable with the specified name at that point, the evaluation of the variable's value results in an error.

- Addition \<expr1> \<expr2>:  
The sum of two expressions. First, we evaluate the first expression, then the second one, and finally, we perform the addition on the obtained values. If the result exceeds the range, the outcome should be the same as Java for equivalent values.

- Subtraction \<expr1> \<expr2>:  
The difference between two expressions. First, we evaluate the first expression, then the second one, and finally, we subtract the value of the second expression from the value of the first one. If the result exceeds the range, the outcome should be the same as Java for equivalent values.

- Multiplication \<expr1> \<expr2>:  
The product of two expressions. First, we evaluate the first expression, then the second one, and finally, we multiply the obtained values. If the result exceeds the range, the outcome should be the same as Java for equivalent values.

- Division \<expr1> \<expr2>:  
Integer division of two expressions. First, we evaluate the first expression, then the second one, and finally, we divide the value of the first expression by the value of the second one. For negative numbers or different signs, the result should be the same as Java for equivalent values. The calculation results in an error when the value of the second expression is zero.

- Modulo \<expr1> \<expr2>:  
The remainder of the integer division of two expressions. First, we evaluate the first expression, then the second one, and finally, we divide the value of the first expression by the value of the second one, and the result is the remainder. For negative numbers or different signs, the result should be the same as Java for equivalent values. The calculation results in an error when the value of the second expression is zero.

## Convenient Macchiato Program Creation in Java

Macchiato programs in version 1.1 can be created in a much more convenient way than in the previous version. A set of classes that serve as a small SDK for Macchiato provides the ability to create programs and their individual parts in a manner similar to DSL. This allows for sequentially adding declarations and instructions through calls to corresponding methods. Additionally, it enables the creation of expressions using clear, static functions.

Create a program with the following meta syntax:

```java
beginblock
     var x 101
     var y 1
     proc out
         print a+x
     end percentage
     x := x - y
     out(x)
     out(100) // this should print 200
     beginblock
         var x 10
         out(100) // here statically still 200, dynamically 110
     end block
end block
```

it could go like this, for example:

```java
Macchiato main = new ProgramBuilder()
        .declareVariable('x', Constant.create(101))
        .declareVariable('y', Constant.create(1))
        .declareProcedure("out", new char[]{'a'}, new ProcedureBlockBuilder()
                .print(new Variable('a').add(new Variable('x')))
                .build()
        )
        .assignVariable('x', new Variable('x').sub(new Variable('y')))
        .invoke("out", new Expression[]{new Variable('x'), new Variable('y')})
        .invoke("out", new Expression[]{Constant.create(100)})
        .block(new BlockInstructionBuilder()
                .declareVariable('x', Constant.create(10))
                .invoke("out", new Expression[]{Constant.create(100)})
                .build()
        )
        .build();
main.run();
```
