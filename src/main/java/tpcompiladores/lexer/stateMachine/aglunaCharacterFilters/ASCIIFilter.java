package tpcompiladores.lexer.stateMachine.aglunaCharacterFilters;

import tpcompiladores.lexer.stateMachine.CharacterFilter;

public class ASCIIFilter implements CharacterFilter {
    @Override
    public boolean matches(Character character) {
        return Character.getNumericValue(character) < 256;
    }

    @Override
    public String toString() {
        return "  -- ASCIIFilter (character numeric representation < 256)";
    }
}
