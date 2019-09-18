package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class ForgetLastRecordedCharacter implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getCharactersRecorder().forgetMostRecentCharacter();
    }
}
