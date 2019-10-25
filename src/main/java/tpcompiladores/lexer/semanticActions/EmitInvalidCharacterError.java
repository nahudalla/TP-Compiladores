package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.fileInput.CharactersReader;

public class EmitInvalidCharacterError implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLogger().logLexerError(
                this.generateError(compilerContext.getCharactersReader())
        );
    }

    private String generateError(CharactersReader charactersReader){
        return "Caracter invalido: " + charactersReader.getLastReadCharacter();
    }

    @Override
    public String toString() {
        return "  -- EmitInvalidCharacterError";
    }
}
