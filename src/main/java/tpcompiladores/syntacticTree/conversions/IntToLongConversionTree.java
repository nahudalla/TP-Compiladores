package tpcompiladores.syntacticTree.conversions;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IntToLongConversionTree extends SyntacticTree {
    public IntToLongConversionTree(SyntacticTree node) {
        super(node, null);
    }

    @Override
    public Type resultType() {
        return Type.LONG;
    }
}
