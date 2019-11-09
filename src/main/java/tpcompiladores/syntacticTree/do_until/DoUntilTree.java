package tpcompiladores.syntacticTree.do_until;

import tpcompiladores.syntacticTree.SyntacticTree;

public class DoUntilTree extends SyntacticTree {
    public DoUntilTree(SyntacticTree leftTree, DoUntilComparisonTree rightTree) {
        super(leftTree, rightTree);
    }
}
