package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class AsignationTree extends SyntacticTree {

    public AsignationTree(String value, SyntacticTree leftTree, SyntacticTree rightTree) {
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
