package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class EmitToken implements SemanticAction {
    private int token;

    public EmitToken(int token) {
        this.token = token;
    }

    protected Integer getToken() {
        return this.token;
    }

    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLexer().setNextToken(this.token);
    }
}
