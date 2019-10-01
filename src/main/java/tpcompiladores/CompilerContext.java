package tpcompiladores;

import tpcompiladores.lexer.CharactersRecorder;
import tpcompiladores.lexer.Lexer;
import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.parser.yacc_generated.Parser;
import tpcompiladores.symbolsTable.SymbolsTable;

import java.util.Map;

public class CompilerContext {
    private CharactersRecorder charactersRecorder;
    private CharactersReader charactersReader;
    private Lexer lexer;
    private Map<String,Integer> reservedWordsTable;
    private SymbolsTable symbolsTable;
    private Logger logger;
    private Parser parser;

    public Parser getParser() {
        return this.parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

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

    public Logger getLogger() {
        return this.logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
