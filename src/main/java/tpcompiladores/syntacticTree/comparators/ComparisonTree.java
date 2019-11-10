package tpcompiladores.syntacticTree.comparators;

import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class ComparisonTree extends SmallResultTypeTree {
    public ComparisonTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }
}
