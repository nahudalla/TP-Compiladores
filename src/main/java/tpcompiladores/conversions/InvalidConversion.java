package tpcompiladores.conversions;

import tpcompiladores.Logger;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class InvalidConversion implements Conversion {
    private final String otherTypeStr;

    public InvalidConversion(Type otherType) {
        if (otherType == null) {
            this.otherTypeStr = "DESCONOCIDO";
        } else {
            this.otherTypeStr = otherType.toString();
        }
    }

    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        Type type = syntacticTree.resultType();
        String typeStr = "DESCONOCIDO";

        if (type != null) typeStr = type.toString();

        Logger.getInstance().logSemanticError("No se puede operar entre los tipos " + typeStr + " y " + this.otherTypeStr);

        return syntacticTree;
    }
}
