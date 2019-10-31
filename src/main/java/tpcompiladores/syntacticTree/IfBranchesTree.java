package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class IfBranchesTree extends SyntacticTree {
    public IfBranchesTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
