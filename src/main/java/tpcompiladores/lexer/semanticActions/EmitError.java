package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;

public class EmitError implements SemanticAction {
    private String message;

    public EmitError(String message) {
        this.message = message;
    }

    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLogger().logError(this.message);
    }
}
