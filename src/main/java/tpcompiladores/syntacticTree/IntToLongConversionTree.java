package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class IntToLongConversionTree extends SyntacticTree {
    public IntToLongConversionTree(SyntacticTree node) {
        super(node, null);
    }

    @Override
    public Type resultType() {
        return Type.LONG;
    }
}
