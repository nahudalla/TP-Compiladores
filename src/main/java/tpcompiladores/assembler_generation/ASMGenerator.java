package tpcompiladores.assembler_generation;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ASMGenerator {
    private List<ASMDumpable> dumpables = new ArrayList<>();

    public void generateASM (PrintStream printStream) {
        this.printMainHeader(printStream);
        this.printDataSection(printStream);
        this.printCodeSection(printStream);
    }

    private void printCodeSection(PrintStream printStream) {
        Registers registers = new Registers();

        printStream.println(".code");

        for (ASMDumpable dumpable: this.dumpables){
            dumpable.generateCode(printStream, registers);
        }

        printStream.println();
    }

    private void printDataSection(PrintStream printStream) {
        printStream.println(".data");

        for (ASMDumpable dumpable: this.dumpables){
            dumpable.generateData(printStream);
        }

        printStream.println();
    }

    private void printMainHeader(PrintStream stream) {
        stream.println(".386");
        stream.println(".model flat, stdcall");
        stream.println("option casemap :none");
        stream.println("include \\masm32\\include\\windows.inc");
        stream.println("include \\masm32\\include\\kernel32.inc");
        stream.println("include \\masm32\\include\\user32.inc");
        stream.println("includelib \\masm32\\lib\\kernel32.lib");
        stream.println("includelib \\masm32\\lib\\user32.lib");
    }

    public void addDumpable (ASMDumpable dumpable) {
        this.dumpables.add(dumpable);
    }
}
