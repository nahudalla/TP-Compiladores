package tpcompiladores.syntacticTree.if_tree;

import java.io.PrintStream;
import java.util.Stack;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IfTree extends SyntacticTree {
    public static Stack<String> jumpLabels = new Stack<>();

    public IfTree(IfComparisonTree leftTree, IfBranchesTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        this.leftTree.generateCodeWithResult(printStream, registers);
        this.rightTree.generateCodeWithResult(printStream, registers);

        return null;
    }
}
