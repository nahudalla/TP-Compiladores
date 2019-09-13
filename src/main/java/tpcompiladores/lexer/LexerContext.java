package tpcompiladores.lexer;

import java.util.Map;

public class LexerContext {
    private CharactersRecorder charactersRecorder;
    private CharactersReader charactersReader;
    private Lexer lexer;
    private Map<String,Integer> reservedWordsTable;
    private Map<String,Integer> specialTokensTable;
    private Map<String, SymbolTableEntry> symbolsTable;
    private Map<String, SymbolTableEntry> constantsTable;
    private Logger logger;

    public CharactersRecorder getCharactersRecorder() {
        return this.charactersRecorder;
    }

    public void setCharactersRecorder(CharactersRecorder charactersRecorder) {
        this.charactersRecorder = charactersRecorder;
    }

    public CharactersReader getCharactersReader() {
        return this.charactersReader;
    }

    public void setCharactersReader(CharactersReader charactersReader) {
        this.charactersReader = charactersReader;
    }

    public Lexer getLexer() {
        return this.lexer;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    public Map<String, Integer> getReservedWordsTable() {
        return this.reservedWordsTable;
    }

    public void setReservedWordsTable(Map<String, Integer> reservedWordsTable) {
        this.reservedWordsTable = reservedWordsTable;
    }

    public Map<String, Integer> getSpecialTokensTable() {
        return this.specialTokensTable;
    }

    public void setSpecialTokensTable(Map<String, Integer> specialTokensTable) {
        this.specialTokensTable = specialTokensTable;
    }

    public Map<String, SymbolTableEntry> getSymbolsTable() {
        return this.symbolsTable;
    }

    public void setSymbolsTable(Map<String, SymbolTableEntry> symbolsTable) {
        this.symbolsTable = symbolsTable;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Map<String, SymbolTableEntry> getConstantsTable() {
        return this.constantsTable;
    }

    public void setConstantsTable(Map<String, SymbolTableEntry> constantsTable) {
        this.constantsTable = constantsTable;
    }
}
