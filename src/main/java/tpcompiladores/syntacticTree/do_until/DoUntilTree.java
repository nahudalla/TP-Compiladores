package tpcompiladores.syntacticTree.do_until;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class DoUntilTree extends SyntacticTree {
    public DoUntilTree(SentenceTree leftTree, DoUntilComparisonTree rightTree) {
        super(leftTree, rightTree);
    }
}
