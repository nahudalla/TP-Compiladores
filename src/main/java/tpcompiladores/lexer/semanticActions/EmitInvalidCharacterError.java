package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.CharactersReader;
import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;

public class EmitInvalidCharacterError implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLogger().logError(
                this.generateError(lexerContext.getCharactersReader())
        );
    }

    private String generateError(CharactersReader charactersReader){
        return "Character invalid: " + charactersReader.getLastReadCharacter();
    }
}