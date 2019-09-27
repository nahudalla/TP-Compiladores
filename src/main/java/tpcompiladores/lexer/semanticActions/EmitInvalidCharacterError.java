package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.lexer.LexerContext;

public class EmitInvalidCharacterError implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLogger().logError(
                this.generateError(lexerContext.getCharactersReader())
        );
    }

    private String generateError(CharactersReader charactersReader){
        return "Caracter invalido: " + charactersReader.getLastReadCharacter();
    }
}
