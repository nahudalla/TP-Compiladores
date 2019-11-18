package tpcompiladores.syntacticTree.do_until;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class DoUntilComparisonTree extends SyntacticTree {
    public DoUntilComparisonTree(SyntacticTree comparisonTree) {
        super(comparisonTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult result = this.leftTree.generateCodeWithResult(printStream, registers);

        String label = DoUntilTree.jumpLabels.pop();
        printStream.println(result.completeJump(label));

        return null;
    }
}
