package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.CompilerContext;

public class EmitInvalidCharacterError implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        compilerContext.getLogger().logError(
                this.generateError(compilerContext.getCharactersReader())
        );
    }

    private String generateError(CharactersReader charactersReader){
        return "Caracter invalido: " + charactersReader.getLastReadCharacter();
    }
}
