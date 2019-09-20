package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public interface SemanticAction {
    void run(LexerContext lexerContext);
}
