package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public abstract class Conversion {
    protected final String requiredTypeStr;

    protected Conversion(Type requiredType) {
        if (requiredType == null) {
            this.requiredTypeStr = "DESCONOCIDO";
        } else {
            this.requiredTypeStr = requiredType.toString();
        }
    }

    public abstract SyntacticTree convert(SyntacticTree syntacticTree);
}
