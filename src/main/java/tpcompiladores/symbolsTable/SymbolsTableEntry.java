package tpcompiladores.symbolsTable;

public class SymbolsTableEntry {
    public static final String[] COLUMN_NAMES = {
            "Identificador", "Lexema", "Tipo"
    };

    private String identifier;
    private String lexeme;
    private Type type;

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String[] toTableRow () {
        return new String[] {
                this.preprocessFieldValue(this.identifier),
                this.preprocessFieldValue(this.lexeme),
                this.preprocessFieldValue(this.type)
        };
    }

    private String preprocessFieldValue (Type value) {
        if (value == null) return "";

        return this.preprocessFieldValue(value.getName());
    }

    private String preprocessFieldValue (String value) {
        if (value == null) return "";

        return value;
    }
}
