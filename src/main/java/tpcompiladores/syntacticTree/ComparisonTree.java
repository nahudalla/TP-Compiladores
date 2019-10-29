package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class ComparisonTree extends SyntacticTree {

    public ComparisonTree(String value, SyntacticTree leftTree, SyntacticTree rightTree) {
        super(value, leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }

    @Override
    public boolean isReferenceToMethod() {
        return false;
    }

    @Override
    public boolean isReferenceToVariable() {
        return false;
    }
}
