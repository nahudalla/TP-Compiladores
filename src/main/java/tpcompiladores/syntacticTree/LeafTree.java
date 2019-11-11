package tpcompiladores.syntacticTree;

import tpcompiladores.Logger;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;

public class LeafTree extends SyntacticTree {
    private SymbolsTableEntry symbolsTableReference;

    public LeafTree(SymbolsTableEntry symbolsTableReference) {
        super(null, null);

        if(symbolsTableReference.getUse() == null) {
            Logger.getInstance().logSemanticError(
                    "El identificador " +
                    symbolsTableReference.getLexeme() + " no ha sido declarado"
            );
            this.symbolsTableReference = null;
        } else {
            this.symbolsTableReference = symbolsTableReference;
        }
    }

    public SymbolsTableEntry getSymbolsTableEntry () {
        return this.getSymbolsTableEntry();
    }

    @Override
    protected void printSelf(TreePrinter printer) {
        printer.printIndentation();
        printer.printClassName(this);
        printer.getStream().println(" (" + this.symbolsTableReference.getIdentifier() + ")");
    }

    @Override
    public Type resultType() {
        if (this.symbolsTableReference == null) return Type.INVALID;

        return this.symbolsTableReference.getType();
    }
}
