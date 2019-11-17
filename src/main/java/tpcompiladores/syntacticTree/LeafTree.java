package tpcompiladores.syntacticTree;

import java.io.PrintStream;

import tpcompiladores.Logger;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;
import tpcompiladores.symbolsTable.Type;

public class LeafTree extends SyntacticTree {
    protected SymbolsTableEntry symbolsTableReference;

    public LeafTree(SymbolsTableEntry symbolsTableReference) {
        super(null, null);

        if(symbolsTableReference.getUse() == null) {
            Logger.getInstance().logSemanticError(
                    "El identificador '" +
                    symbolsTableReference.getLexeme() + "' no ha sido declarado"
            );
            this.symbolsTableReference = null;
        } else {
            this.symbolsTableReference = symbolsTableReference;
        }
    }

    public SymbolsTableEntry getSymbolsTableEntry () {
        return this.symbolsTableReference;
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

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        switch (this.symbolsTableReference.getUse()) {
            case CONSTANT: return new ASMOperationResult(this.symbolsTableReference);
            case VARIABLE: return new ASMOperationResult(this.symbolsTableReference.getIdentifier());
            default: return null;
        }
    }
}
