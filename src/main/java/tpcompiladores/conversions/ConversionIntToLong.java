package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.IntToLongConversionTree;

public class ConversionIntToLong extends Conversion {
    public ConversionIntToLong(Type requiredType) {
        super(requiredType);
    }

    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        return new IntToLongConversionTree(syntacticTree);
    }
}
