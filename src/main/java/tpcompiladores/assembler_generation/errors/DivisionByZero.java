package tpcompiladores.assembler_generation.errors;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;

public class DivisionByZero extends BaseError {
    public static final String label = "@DivisionByZero";

    public DivisionByZero () {
        super("ERROR: Division por cero!");
    }

    @Override
    public void generateCode(PrintStream printStream, Registers registers) {
        super.generateCode(label, printStream, registers);
    }
}
