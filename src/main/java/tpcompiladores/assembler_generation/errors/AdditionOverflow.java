package tpcompiladores.assembler_generation.errors;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;

public class AdditionOverflow extends BaseError {
    public static final String label = "@AdditionOverflow";

    public AdditionOverflow () {
        super("ERROR: Overflow en una suma!");
    }

    @Override
    public void generateCode(PrintStream printStream, Registers registers) {
        super.generateCode(label, printStream, registers);
    }
}
