package tpcompiladores.syntacticTree;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.symbolsTable.SymbolsTableEntry;

public class ASMOperationResult {
    private final Register register;
    private final String variableIdentifier;
    private final String constantLexeme;
    private final String incompleteJump;

    public ASMOperationResult (Register register) {
        this.register = register;
        this.variableIdentifier = null;
        this.constantLexeme = null;
        this.incompleteJump = null;
    }

    public ASMOperationResult (String variableIdentifier) {
        this.register = null;
        this.variableIdentifier = variableIdentifier;
        this.constantLexeme = null;
        this.incompleteJump = null;
    }

    public ASMOperationResult (SymbolsTableEntry constantReference) {
        this.register = null;
        this.variableIdentifier = null;
        this.constantLexeme = constantReference.getLexeme();
        this.incompleteJump = null;
    }

    private ASMOperationResult (String incompleteJump, boolean isIncompleteJump) {
        this.register = null;
        this.variableIdentifier = null;
        this.constantLexeme = null;
        this.incompleteJump = incompleteJump;
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

    public String completeJump (String label) {
        return this.incompleteJump + " " + label;
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

    public boolean isIncompleteJump () {
        return this.incompleteJump != null;
    }

    @Override
    public String toString () {
        if (this.isInRegister()) return this.register.toString();
        else if(this.isInVariable()) return this.variableIdentifier;
        else return this.constantLexeme;
    }

	public static ASMOperationResult incompleteJump(String jumpStr) {
		return new ASMOperationResult(jumpStr, true);
	}
}
