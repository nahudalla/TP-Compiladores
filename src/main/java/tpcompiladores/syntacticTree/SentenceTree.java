package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class SentenceTree extends SyntacticTree {
    public SentenceTree(SyntacticTree leftTree, SentenceTree rightTree) {
        super(leftTree, rightTree);
    }
}
