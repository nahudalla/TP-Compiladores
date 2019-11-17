package tpcompiladores.conversions;

import tpcompiladores.syntacticTree.SyntacticTree;

public interface Conversion {
    SyntacticTree convert(SyntacticTree syntacticTree);
}
