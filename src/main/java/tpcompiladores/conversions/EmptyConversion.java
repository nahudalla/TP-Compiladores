package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class EmptyConversion extends Conversion {
    public EmptyConversion(Type requiredType) {
        super(requiredType);
    }

    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        return syntacticTree;
    }
}
