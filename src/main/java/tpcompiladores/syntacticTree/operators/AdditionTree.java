package tpcompiladores.syntacticTree.operators;

import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class AdditionTree extends SmallResultTypeTree {
    public AdditionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Suma", leftTree, rightTree);
    }
}
