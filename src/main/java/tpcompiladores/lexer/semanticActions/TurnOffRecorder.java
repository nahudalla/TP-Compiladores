package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class TurnOffRecorder implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getCharactersRecorder().turnOff();
    }
}
