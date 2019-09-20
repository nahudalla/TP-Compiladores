package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class UngetLastReadCharacter implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getCharactersReader().unGetLastCharacter();
    }
}
