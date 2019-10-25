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

    @Override
    public String toString() {
        return "  -- SingleCharacterFilter: " + this.getFormattedCharacter();
    }

    private String getFormattedCharacter() {
        switch (this.character) {
            case '\n': return "ENDLINE";
            case ' ': return "SPACE";
            case '\t': return "TAB";
            default: return String.valueOf(this.character);
        }
    }
}
