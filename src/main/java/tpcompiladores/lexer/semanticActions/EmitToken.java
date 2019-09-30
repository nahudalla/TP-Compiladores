package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class EmitToken implements SemanticAction {
    private int token;

    public EmitToken(int token) {
        this.token = token;
    }

    protected Integer getToken() {
        return this.token;
    }

    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLexer().setNextToken(this.token);
    }
}
