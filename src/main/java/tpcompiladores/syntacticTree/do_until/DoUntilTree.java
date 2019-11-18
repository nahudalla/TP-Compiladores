package tpcompiladores.syntacticTree.do_until;

import java.io.PrintStream;
import java.util.Stack;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class DoUntilTree extends SyntacticTree {
    public static Stack<String> jumpLabels = new Stack<>();
    private static int labelCount = 0;

    public DoUntilTree(SyntacticTree leftTree, DoUntilComparisonTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        String label = jumpLabels.push("DoUntilBegin_" + labelCount++);

        printStream.println(label + ":");

        this.leftTree.generateCodeWithResult(printStream, registers);
        this.rightTree.generateCodeWithResult(printStream, registers);

        return null;
    }
}
