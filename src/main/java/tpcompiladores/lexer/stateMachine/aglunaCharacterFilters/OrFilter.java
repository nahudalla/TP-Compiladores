package tpcompiladores.lexer.stateMachine.aglunaCharacterFilters;

import tpcompiladores.lexer.stateMachine.CharacterFilter;

public class OrFilter implements CharacterFilter {
    private CharacterFilter filterA;
    private CharacterFilter filterB;

    public OrFilter(CharacterFilter filterA, CharacterFilter filterB) {
        this.filterA = filterA;
        this.filterB = filterB;
    }

    @Override
    public boolean matches(Character character) {
        return this.filterA.matches(character) || this.filterB.matches(character);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.filterA.toString())
                .append(System.lineSeparator())
                .append(this.filterB.toString());

        return builder.toString();
    }
}
