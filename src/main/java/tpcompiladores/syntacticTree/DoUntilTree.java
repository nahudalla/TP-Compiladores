package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class DoUntilTree extends SyntacticTree {
    public DoUntilTree(SentenceTree leftTree, DoUntilComparisonTree rightTree) {
        super(leftTree, rightTree);
    }
}
