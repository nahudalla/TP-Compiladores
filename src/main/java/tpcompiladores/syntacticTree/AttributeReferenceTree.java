package tpcompiladores.syntacticTree;

import tpcompiladores.Logger;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;

public class AttributeReferenceTree extends LeafTree {
    public AttributeReferenceTree(SymbolsTableEntry symbolsTableReference) {
        super(symbolsTableReference);

        if (this.symbolsTableReference == null) return;

        if (!SymbolsTableEntryUse.ATTRIBUTE.equals(this.symbolsTableReference.getUse())) {
            String klassNameStr = "";

            if (this.symbolsTableReference.getKlass() != null) {
                klassNameStr = ", de la clase " + this.symbolsTableReference.getKlass().getName() + ",";
            }

            Logger.getInstance().logSemanticError(
                "Se esperaba un atributo y se encontro '" +
                this.symbolsTableReference.getLexeme() + "'" + klassNameStr +
                " (uso: "+ this.symbolsTableReference.getUse().name() +")."
            );

            this.symbolsTableReference = null;
        }
    }

    @Override
    public boolean isReferenceToVariable() {
        return this.symbolsTableReference != null;
    }
}
