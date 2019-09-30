package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class TurnOnRecorder implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getCharactersRecorder().turnOn();
    }
}
