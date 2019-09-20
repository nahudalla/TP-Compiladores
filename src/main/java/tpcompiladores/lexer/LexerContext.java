package tpcompiladores.lexer;

import tpcompiladores.Logger;
import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.symbolsTable.SymbolTableEntry;
import tpcompiladores.symbolsTable.SymbolsTable;

import java.util.Map;

public class LexerContext {
    private CharactersRecorder charactersRecorder;
    private CharactersReader charactersReader;
    private Lexer lexer;
    private Map<String,Integer> reservedWordsTable;
    private Map<String,Integer> specialTokensTable;
    private SymbolsTable symbolsTable;
    private Logger logger;

    public CharactersRecorder getCharactersRecorder() {
        return this.charactersRecorder;
    }

    public void setCharactersRecorder(CharactersRecorder charactersRecorder) {
        this.charactersRecorder = charactersRecorder;
    }

    public SymbolsTable getSymbolsTable() {
        return this.symbolsTable;
    }

    public void setSymbolsTable(SymbolsTable symbolsTable) {
        this.symbolsTable = symbolsTable;
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

    public Logger getLogger() {
        return this.logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
