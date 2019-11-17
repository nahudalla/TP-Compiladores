package tpcompiladores.conversions;

import tpcompiladores.syntacticTree.SyntacticTree;

public class EmptyConversion implements Conversion {
    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        return syntacticTree;
    }
}
