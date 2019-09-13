package tpcompiladores.lexer;

import java.util.Map;

public class SymbolTableEntry {
    private String lexeme;

    public SymbolTableEntry(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public void setLexema(String lexeme) {
        this.lexeme = lexeme;
    }
}
