package tpcompiladores.syntacticTree;

import tpcompiladores.Logger;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;
import tpcompiladores.symbolsTable.Type;

public class MethodCallTree extends LeafTree {
    public MethodCallTree(SymbolsTableEntry methodTableEntry) {
        super(methodTableEntry);

        if (!this.resultType().equals(Type.INVALID) && !SymbolsTableEntryUse.METHOD.equals(methodTableEntry.getUse())) {
            Logger.getInstance().logSemanticError(
                    methodTableEntry.getLexeme() + " en la clase " + methodTableEntry.getKlass().getName()
                            + " no es un metodo, por lo que no se puede llamar."
            );
        }
    }
}
