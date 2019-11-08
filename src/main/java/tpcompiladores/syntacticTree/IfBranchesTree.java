package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

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
}
