package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class EmitSpecialCharacter implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        Character readCharacter = lexerContext.getCharactersReader().getLastReadCharacter();
        lexerContext.getLexer().setNextToken(readCharacter);
    }
}
