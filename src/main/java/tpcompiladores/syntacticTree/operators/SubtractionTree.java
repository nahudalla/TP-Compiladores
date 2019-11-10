package tpcompiladores.syntacticTree.operators;

import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class SubtractionTree extends SmallResultTypeTree {
    public SubtractionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }
}
