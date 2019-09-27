package tpcompiladores.lexer.stateMachine.aglunaCharacterFilters;

import tpcompiladores.lexer.stateMachine.CharacterFilter;

public class SingleCharacterFilter implements CharacterFilter {
    private char character;

    public SingleCharacterFilter(char character) {
        this.character = character;
    }

    @Override
    public boolean matches(Character character) {
        return character != null && this.character == character;
    }
}
