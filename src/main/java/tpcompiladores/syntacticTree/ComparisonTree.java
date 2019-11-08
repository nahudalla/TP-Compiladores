package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class ComparisonTree extends SmallResultTypeTree {
    public ComparisonTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }
}
