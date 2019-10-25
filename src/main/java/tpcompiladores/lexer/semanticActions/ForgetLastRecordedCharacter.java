package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class ForgetLastRecordedCharacter implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getCharactersRecorder().forgetMostRecentCharacter();
    }

    @Override
    public String toString() {
        return "  -- ForgetLastRecordedCharacter";
    }
}
