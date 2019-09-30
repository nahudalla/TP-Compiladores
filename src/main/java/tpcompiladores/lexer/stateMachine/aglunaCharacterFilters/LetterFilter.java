package tpcompiladores.lexer.stateMachine.aglunaCharacterFilters;

import tpcompiladores.lexer.stateMachine.CharacterFilter;

public class LetterFilter implements CharacterFilter {
    @Override
    public boolean matches(Character character) {
        return character != null && Character.isLetter(character);
    }
}
