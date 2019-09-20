package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class TurnOnRecorder implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getCharactersRecorder().turnOn();
    }
}
