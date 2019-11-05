package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public interface Conversion {
    SyntacticTree convert(SyntacticTree syntacticTree);
}
