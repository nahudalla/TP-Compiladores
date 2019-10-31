package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class DoUntilTree extends SyntacticTree {
    public DoUntilTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
