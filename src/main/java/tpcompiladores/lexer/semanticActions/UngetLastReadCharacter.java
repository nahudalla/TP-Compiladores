package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class UngetLastReadCharacter implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getCharactersReader().unGetLastCharacter();
    }
}
