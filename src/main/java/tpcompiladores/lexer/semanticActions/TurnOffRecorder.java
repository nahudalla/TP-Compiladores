package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;

public class TurnOffRecorder implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getCharactersRecorder().turnOff();
    }
}
