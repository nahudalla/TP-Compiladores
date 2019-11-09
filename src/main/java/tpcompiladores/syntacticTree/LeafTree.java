package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;

public class LeafTree extends SyntacticTree {
    private SymbolsTableEntry symbolsTableReference;

    public LeafTree(SymbolsTableEntry symbolsTableReference) {
        super(null, null);

        this.symbolsTableReference = symbolsTableReference;
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
        return this.symbolsTableReference.getType();
    }
}
