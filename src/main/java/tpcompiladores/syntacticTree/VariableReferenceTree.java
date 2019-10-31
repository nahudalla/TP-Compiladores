package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class VariableReferenceTree extends SyntacticTree {
    public VariableReferenceTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
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
