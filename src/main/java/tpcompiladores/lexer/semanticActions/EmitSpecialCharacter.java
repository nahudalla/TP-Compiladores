package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class EmitSpecialCharacter implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        Character readCharacter = compilerContext.getCharactersReader().getLastReadCharacter();
        compilerContext.getLexer().setNextToken(readCharacter);
    }
}
