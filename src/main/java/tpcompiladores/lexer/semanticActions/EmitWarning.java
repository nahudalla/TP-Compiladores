package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class EmitWarning implements SemanticAction {
    private String message;

    public EmitWarning(String message) {
        this.message = message;
    }

    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLogger().logWarning(this.message);
    }
}
