package tpcompiladores.syntacticTree.if_tree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IfComparisonTree extends SyntacticTree {
    private static int labelCount = 0;

    public IfComparisonTree(SyntacticTree comparisonTree) {
        super(comparisonTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult result = this.leftTree.generateCodeWithResult(printStream, registers);

        String label = IfTree.jumpLabels.push("IfThenBranchEnd_" + labelCount++);

        printStream.println(result.completeJump(label));

        return null;
    }
}
