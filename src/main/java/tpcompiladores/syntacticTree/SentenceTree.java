package tpcompiladores.syntacticTree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;

public class SentenceTree extends SyntacticTree {
    public SentenceTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        if (this.leftTree != null) this.leftTree.generateCode(printStream, registers);
        if (this.rightTree != null) this.rightTree.generateCode(printStream, registers);

        return null;
    }
}
