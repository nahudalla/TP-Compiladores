package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class IfTree extends SyntacticTree {
    public IfTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
