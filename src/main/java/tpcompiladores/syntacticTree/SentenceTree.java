package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class SentenceTree extends SyntacticTree {
    public SentenceTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    @Override
    public Type resultType() {
        return null;
    }
}
