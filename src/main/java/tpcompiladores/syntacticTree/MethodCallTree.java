package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class MethodCallTree extends SyntacticTree {
    public MethodCallTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
