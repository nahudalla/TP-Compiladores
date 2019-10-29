package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class MethodCallTree extends SyntacticTree {

    public MethodCallTree(String value, SyntacticTree leftTree, SyntacticTree rightTree) {
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
