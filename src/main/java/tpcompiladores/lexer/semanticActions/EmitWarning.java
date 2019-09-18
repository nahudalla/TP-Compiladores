package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class EmitWarning implements SemanticAction {
    private String message;

    public EmitWarning(String message) {
        this.message = message;
    }

    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLogger().logWarning(this.message);
    }
}
