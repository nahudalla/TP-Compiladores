package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class IfThenBranchTree extends SyntacticTree {
    public IfThenBranchTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
