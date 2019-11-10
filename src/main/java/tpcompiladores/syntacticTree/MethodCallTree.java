package tpcompiladores.syntacticTree;

import tpcompiladores.Logger;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;

public class MethodCallTree extends LeafTree {
    private MethodCallTree(SymbolsTableEntry methodTableEntry) {
        super(methodTableEntry);
    }

    public static MethodCallTree create(SymbolsTableEntry methodTableEntry) {
        String methodName = methodTableEntry.getLexeme();

        if (methodTableEntry.getUse().equals(SymbolsTableEntryUse.METHOD)) {
            return new MethodCallTree(methodTableEntry);
        }

        Logger.getInstance().logSemanticError(
                methodName + " en la clase " + methodTableEntry.getKlass().getName()
                + " no es un método, por lo que no se puede llamar."
        );

        return null;
    }
}
