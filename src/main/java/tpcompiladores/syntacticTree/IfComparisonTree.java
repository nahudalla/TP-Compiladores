package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class IfComparisonTree extends SyntacticTree {
    public IfComparisonTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
