package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.TokenDisplayName;

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

    @Override
    public String toString() {
        return "  -- EmitToken: " + TokenDisplayName.get(this.token);
    }
}
