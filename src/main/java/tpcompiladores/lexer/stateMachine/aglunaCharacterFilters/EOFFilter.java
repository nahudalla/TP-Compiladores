package tpcompiladores.lexer.stateMachine.aglunaCharacterFilters;

import tpcompiladores.lexer.stateMachine.CharacterFilter;

public class EOFFilter implements CharacterFilter {
    @Override
    public boolean matches(Character character) {
        return character == null;
    }
}
