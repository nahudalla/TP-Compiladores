package tpcompiladores.assembler_generation;

import java.io.PrintStream;

import tpcompiladores.syntacticTree.SyntacticTree;

public class ASMMainProgram implements ASMDumpable {
    private SyntacticTree tree;

    public ASMMainProgram(SyntacticTree tree) {
        this.tree = tree;
    }

    @Override
    public void generateData(PrintStream printStream) {
        this.tree.generateData(printStream);
    }

    @Override
    public void generateCode(PrintStream printStream, Registers registers) {
        printStream.println("START:");
        this.tree.generateCode(printStream, registers);
        printStream.println("invoke ExitProcess, 0");
        printStream.println("END START");
    }
}
