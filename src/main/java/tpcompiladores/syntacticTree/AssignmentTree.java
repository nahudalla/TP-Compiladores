package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class AssignmentTree extends SyntacticTree {
    public AssignmentTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
