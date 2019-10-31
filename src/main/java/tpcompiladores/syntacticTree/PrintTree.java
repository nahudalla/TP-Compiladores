package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class PrintTree extends SyntacticTree {
    public PrintTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
