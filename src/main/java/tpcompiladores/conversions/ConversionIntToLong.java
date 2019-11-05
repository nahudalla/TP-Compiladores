package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.IntToLongConversionTree;

public class ConversionIntToLong implements Conversion {
    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        return new IntToLongConversionTree(syntacticTree);
    }
}
