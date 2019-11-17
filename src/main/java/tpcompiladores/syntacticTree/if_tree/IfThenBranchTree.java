package tpcompiladores.syntacticTree.if_tree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IfThenBranchTree extends SyntacticTree {
    public IfThenBranchTree(SyntacticTree ifThenBranchTree) {
        super(ifThenBranchTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        this.leftTree.generateCodeWithResult(printStream, registers);

        String labelElseEnd = IfTree.jumpLabels.pop();

        printStream.println("JMP " + labelElseEnd);
        printStream.println(IfTree.jumpLabels.pop() + ":");

        IfTree.jumpLabels.push(labelElseEnd);

        return null;
    }
}
