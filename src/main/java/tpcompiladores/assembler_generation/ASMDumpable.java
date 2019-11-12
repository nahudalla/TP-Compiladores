package tpcompiladores.assembler_generation;
import java.io.PrintStream;

public interface ASMDumpable {
    void generateData (PrintStream printStream);
    void generateCode (PrintStream printStream, Registers registers);
}
