package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class IfTree extends SyntacticTree {
    public IfTree(IfComparisonTree leftTree, IfBranchesTree rightTree) {
        super(leftTree, rightTree);
    }

    private IfComparisonTree getComparisonTree () {
        return (IfComparisonTree) super.leftTree;
    }

    private IfBranchesTree getBranchesTree () {
        return (IfBranchesTree) super.rightTree;
    }
}
