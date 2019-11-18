package tpcompiladores.syntacticTree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;

public class ASMCodeTree extends SyntacticTree {
    private String asmCode;

    public ASMCodeTree (String asmCode) {
        this.asmCode = asmCode;
    }

    @Override
    public ASMOperationResult generateCodeWithResult(PrintStream printStream, Registers registers) {
        printStream.println(this.asmCode);

        return null;
    }
}
