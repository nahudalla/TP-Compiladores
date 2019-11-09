package tpcompiladores.conversions;

import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.IntToLongConversionTree;

public class ConversionIntToLong implements Conversion {
    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        return new IntToLongConversionTree(syntacticTree);
    }
}
