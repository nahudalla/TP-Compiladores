package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class EmitError implements SemanticAction {
    private String message;

    public EmitError(String message) {
        this.message = message;
    }

    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLogger().logError(this.message);
    }
}
