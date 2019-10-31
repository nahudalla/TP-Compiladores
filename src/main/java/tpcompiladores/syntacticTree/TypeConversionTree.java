package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class TypeConversionTree extends SyntacticTree {
    public TypeConversionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
