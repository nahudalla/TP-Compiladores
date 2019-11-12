package tpcompiladores.syntacticTree;

import java.io.PrintStream;

import tpcompiladores.Logger;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;

public class MethodCallTree extends LeafTree {
    public MethodCallTree(SymbolsTableEntry symbolsTableEntry) {
        super(symbolsTableEntry);

        if (this.symbolsTableReference == null) return;

        if (!SymbolsTableEntryUse.METHOD.equals(this.symbolsTableReference.getUse())) {
            String klassNameStr = "";

            if (this.symbolsTableReference.getKlass() != null) {
                klassNameStr = ", de la clase " + this.symbolsTableReference.getKlass().getName() + ",";
            }

            Logger.getInstance().logSemanticError(
                "Se esperaba un metodo y se encontro '" +
                this.symbolsTableReference.getLexeme() + "'" + klassNameStr +
                " (uso: "+ this.symbolsTableReference.getUse().name() +")."
            );

            this.symbolsTableReference = null;
        }
    }

    @Override
    public boolean isReferenceToMethod(){
        return this.symbolsTableReference != null;
    }

    @Override
    public void generateCode(PrintStream printStream, Registers registers) {
        printStream.println("CALL " + this.symbolsTableReference.getIdentifier());
    }
}
