package tpcompiladores.syntacticTree;

import java.io.PrintStream;

import tpcompiladores.Logger;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;

public class AttributeReferenceTree extends LeafTree {
    private SymbolsTableEntry objReference;

    public AttributeReferenceTree(SymbolsTableEntry objReference, SymbolsTableEntry attributeReference) {
        super(attributeReference);

        this.objReference = objReference;

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

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        return new ASMOperationResult(
            this.objReference.generateAttributeIdentifier(this.symbolsTableReference)
        );
    }
}
