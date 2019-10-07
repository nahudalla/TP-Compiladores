package tpcompiladores;

import tpcompiladores.lexer.LineNumber;
import tpcompiladores.lexer.TokenDisplayName;
import tpcompiladores.symbolsTable.SymbolsTable;

import java.io.PrintStream;

public class Logger {
    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_GREEN = "\u001b[32m";
    private static final String ANSI_YELLOW = "\u001b[33m";
    private static final String ANSI_BLUE = "\u001b[34m";
    private static final String ANSI_CYAN = "\u001b[36m";

    private static final int MAX_LEXEME_PRINT_LENGTH = 25;
    private static final String ELLIPSIS = String.valueOf(Character.toChars(0x2026));

    private Long lastLoggedTokenLine = null;
    private boolean hasEmittedErrors = false;
    private LineNumber lineNumber;

    public Logger(){
        this.lineNumber = new LineNumber();
    }

    public Logger(LineNumber lineNumber){
        this.lineNumber = lineNumber;
    }

    public int getCurrentLineNumber () {
        return (int) this.lineNumber.getCurrentLineNumber();
    }

    public void logMessage (String message) {
        if (this.lastLoggedTokenLine != null) System.out.println();
        this.lastLoggedTokenLine = null;
        System.out.println(message);
    }

    public void logErrorMessage (String message) {
        if (this.lastLoggedTokenLine != null) System.err.println();
        this.lastLoggedTokenLine = null;
        System.err.println(message);
    }

    public void logLexerWarning (String message) {
        this.logWarning("LEXER", message);
    }

    public void logParserWarning (String message) {
        this.logWarning("PARSER", message);
    }

    private void logWarning(String prefix, String message){
        this.printLineNumber(System.out, prefix + " WARNING", ANSI_YELLOW);
        this.lastLoggedTokenLine = null;
        System.out.println(message);
    }

    public void logSyntacticStructure(int line, String message){
        this.printLineNumber(System.out, line, "ESTRUCTURA SINTACTICA", ANSI_BLUE);
        this.lastLoggedTokenLine = null;
        System.out.println(message);
    }

    public void logLexerError (String message) {
        this.logError("LEXER", message);
    }

    public void logParserError (String message) {
        this.logError("PARSER", message);
    }

    private void logError(String prefix, String message){
        this.hasEmittedErrors = true;
        this.printLineNumber(System.err, prefix + " ERROR");
        this.lastLoggedTokenLine = null;
        System.err.println(message);
    }

    public void logSymbolsTable (SymbolsTable symbolsTable) {
        if (this.lastLoggedTokenLine != null) System.out.println();
        this.lastLoggedTokenLine = null;
        System.out.println(ANSI_CYAN + "TABLA DE SIMBOLOS:" + ANSI_RESET);
        symbolsTable.print(System.out);
    }

    public boolean hasEmittedErrors() {
        return this.hasEmittedErrors;
    }

    public void logRecognizedToken(Integer nextToken, String lexeme) {
        if (lexeme != null && lexeme.length() > MAX_LEXEME_PRINT_LENGTH) {
            lexeme = lexeme.substring(0, MAX_LEXEME_PRINT_LENGTH) + ELLIPSIS;
        }

        if (this.lastLoggedTokenLine == null || this.lineNumber.getCurrentLineNumber() != this.lastLoggedTokenLine) {
            this.printTokenHeader();
        } else System.out.print(" ");

        System.out.print(TokenDisplayName.get(nextToken));

        if (lexeme != null) {
            System.out.print("(" + lexeme + ")");
        }
    }

    private void printLineNumber (PrintStream stream, String header) {
        this.printLineNumber(stream, header, ANSI_RESET);
    }

    private void printLineNumber (PrintStream stream, String header, String color) {
        this.printLineNumber(stream, this.lineNumber.getCurrentLineNumber(), header, color);
    }

    private void printLineNumber (PrintStream stream, long lineNumber, String header, String color) {
        if (this.lastLoggedTokenLine != null) stream.println();

        stream.print(color + "Linea " + lineNumber + ": " + header + ": " + ANSI_RESET);
    }

    private void printTokenHeader () {
        this.printLineNumber(System.out, "TOKENS RECONOCIDOS", ANSI_GREEN);
        this.lastLoggedTokenLine = this.lineNumber.getCurrentLineNumber();
    }
}
