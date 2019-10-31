package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class ComparisonTree extends SyntacticTree {
    public ComparisonTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
