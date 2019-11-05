package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;

public class NumericConstantTree extends SyntacticTree {
    private SymbolsTableEntry symbolsTableReference;

    public NumericConstantTree(SymbolsTableEntry symbolsTableReference) {
        super(null, null);

        this.symbolsTableReference = symbolsTableReference;
    }

    @Override
    public Type resultType() {
        return this.symbolsTableReference.getType();
    }
}
