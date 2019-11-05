package tpcompiladores.conversions;

import tpcompiladores.Logger;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class InvalidConversion extends Conversion {
    public InvalidConversion(Type requiredType) {
        super(requiredType);
    }

    @Override
    public SyntacticTree convert(SyntacticTree syntacticTree) {
        Type type = syntacticTree.resultType();
        String typeStr = "DESCONOCIDO";

        if (type != null) typeStr = type.toString();

        Logger.getInstance().logSemanticError("No se puede convertir el tipo " + typeStr + " a " + super.requiredTypeStr);

        return syntacticTree;
    }
}
