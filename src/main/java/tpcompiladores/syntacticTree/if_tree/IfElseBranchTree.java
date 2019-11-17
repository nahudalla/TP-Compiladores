package tpcompiladores.syntacticTree.if_tree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IfElseBranchTree extends SyntacticTree {
    public IfElseBranchTree(SyntacticTree ifElseBranchTree) {
        super(ifElseBranchTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        if (this.leftTree != null) {
            this.leftTree.generateCodeWithResult(printStream, registers);
        }

        printStream.println(IfTree.jumpLabels.pop() + ":");

        return null;
    }
}
