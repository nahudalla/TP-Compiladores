package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class StringConstantTree extends SyntacticTree {
    public StringConstantTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
