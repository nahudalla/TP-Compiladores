package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.SymbolsTableEntry;

public class VariableReferenceTree extends LeafTree {
    public VariableReferenceTree(SymbolsTableEntry symbolsTableReference) {
        super(symbolsTableReference);
    }

    @Override
    public boolean isReferenceToVariable() {
        return true;
    }
}
