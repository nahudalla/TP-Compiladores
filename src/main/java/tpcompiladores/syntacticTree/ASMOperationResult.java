package tpcompiladores.syntacticTree;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;

public class ASMOperationResult {
    private final Register register;
    private final String variableIdentifier;
    private final String constantLexeme;

    public ASMOperationResult (Register register) {
        this.register = register;
        this.variableIdentifier = null;
        this.constantLexeme = null;
    }

    public ASMOperationResult (String variableIdentifier) {
        this.register = null;
        this.variableIdentifier = variableIdentifier;
        this.constantLexeme = null;
    }

    public ASMOperationResult (SymbolsTableEntry constantReference) {
        this.register = null;
        this.variableIdentifier = null;
        this.constantLexeme = constantReference.getLexeme();
    }

    public Register getRegister () {
        return this.register;
    }

    public String getVariableIdentifier () {
        return this.variableIdentifier;
    }

    public String getConstantLexeme () {
        return this.constantLexeme;
    }

    public boolean isInRegister () {
        return this.getRegister() != null;
    }

    public boolean isInVariable () {
        return this.getVariableIdentifier() != null;
    }

    public boolean isConstant () {
        return this.getConstantLexeme() != null;
    }

    @Override
    public String toString () {
        if (this.isInRegister()) return this.register.toString();
        else if(this.isInVariable()) return this.variableIdentifier;
        else return this.constantLexeme;
    }
}
