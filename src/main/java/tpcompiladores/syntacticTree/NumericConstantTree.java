package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class NumericConstantTree extends SyntacticTree {
    public NumericConstantTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
