package tpcompiladores.syntacticTree.if_tree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IfBranchesTree extends SyntacticTree {
    public IfBranchesTree(IfThenBranchTree ifThenBranchTree, IfElseBranchTree ifElseBranchTree) {
        super(ifThenBranchTree, ifElseBranchTree);
    }

    private IfThenBranchTree getThenTree () {
        return (IfThenBranchTree) super.leftTree;
    }

    private IfElseBranchTree getElseTree () {
        return (IfElseBranchTree) super.rightTree;
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        this.getThenTree().generateCodeWithResult(printStream, registers);
        this.getElseTree().generateCodeWithResult(printStream, registers);

        return null;
    }
}
