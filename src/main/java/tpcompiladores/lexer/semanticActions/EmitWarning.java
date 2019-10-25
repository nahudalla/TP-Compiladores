package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class EmitWarning implements SemanticAction {
    private String message;

    public EmitWarning(String message) {
        this.message = message;
    }

    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLogger().logLexerWarning(this.message);
    }

    @Override
    public String toString() {
        return "  -- EmitWarning: " + message;
    }
}
