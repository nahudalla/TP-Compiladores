package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class TurnOffRecorder implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getCharactersRecorder().turnOff();
    }
}
