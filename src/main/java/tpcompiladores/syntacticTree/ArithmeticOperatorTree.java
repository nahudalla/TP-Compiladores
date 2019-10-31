package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class ArithmeticOperatorTree extends SyntacticTree {
    public ArithmeticOperatorTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }

    @Override
    public boolean isReferenceToMethod() {
        return true;
    }

    @Override
    public boolean isReferenceToVariable() {
        return true;
    }
}
