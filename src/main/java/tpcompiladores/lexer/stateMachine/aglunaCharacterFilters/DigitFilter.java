package tpcompiladores.lexer.stateMachine.aglunaCharacterFilters;

import tpcompiladores.lexer.stateMachine.CharacterFilter;

public class DigitFilter implements CharacterFilter {
    @Override
    public boolean matches(Character character) {
        return character != null && Character.isDigit(character);
    }

    @Override
    public String toString() {
        return "  -- DigitFilter (character is a digit [0-9])";
    }
}
