package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class EmitTokenWithSymbolTableReference extends EmitToken {
    private String reference;

    public EmitTokenWithSymbolTableReference(int valueToken, String reference) {
        super(valueToken);
        this.reference = reference;
    }

    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLexer().setNextToken(super.getToken(), this.reference);
    }
}
