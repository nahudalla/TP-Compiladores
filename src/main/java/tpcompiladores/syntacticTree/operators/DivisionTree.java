package tpcompiladores.syntacticTree.operators;

import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class DivisionTree extends SmallResultTypeTree {
    public DivisionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Division", leftTree, rightTree);
    }
}
