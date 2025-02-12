package tpcompiladores;

import tpcompiladores.lexer.LineNumber;
import tpcompiladores.lexer.TokenDisplayName;
import tpcompiladores.symbolsTable.SymbolsTable;
import tpcompiladores.syntacticTree.SyntacticTree;

import java.io.PrintStream;

public class Logger {
    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_GREEN = "\u001b[32m";
    private static final String ANSI_YELLOW = "\u001b[33m";
    private static final String ANSI_BLUE = "\u001b[34m";
    private static final String ANSI_CYAN = "\u001b[36m";

    private static final int MAX_LEXEME_PRINT_LENGTH = 25;
    private static final String ELLIPSIS = String.valueOf(Character.toChars(0x2026));

    private static Logger instance;

    private Long lastLoggedTokenLine = null;
    private boolean hasEmittedErrors = false;
    private LineNumber lineNumber;

    private boolean isPrintRecognizedTokensEnabled = false;
    private boolean isPrintRecognizedSyntacticStructuresEnabled = false;

    private Logger () {}

    public static Logger getInstance () {
        if (Logger.instance == null) {
            Logger.instance = new Logger();
        }

        return Logger.instance;
    }

    public void setLineNumber (LineNumber lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void enablePrintRecognizedTokens () {
        this.isPrintRecognizedTokensEnabled = true;
    }

    public void enablePrintRecognizedSyntacticStructures () {
        this.isPrintRecognizedSyntacticStructuresEnabled = true;
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
        if (!this.isPrintRecognizedSyntacticStructuresEnabled) return;

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

    private void logResultsSectionHeader (String name) {
        if (this.lastLoggedTokenLine != null) System.out.println();
        this.lastLoggedTokenLine = null;
        System.out.println(ANSI_CYAN + name + ":" + ANSI_RESET);
    }

    public void logSymbolsTable (SymbolsTable symbolsTable) {
        this.logResultsSectionHeader("TABLA DE SIMBOLOS");
        symbolsTable.print(System.out);
    }

    public boolean hasEmittedErrors() {
        return this.hasEmittedErrors;
    }

    public void logRecognizedToken(Integer nextToken, String lexeme) {
        if (!this.isPrintRecognizedTokensEnabled) return;

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

    public void logSemanticError(String message) {
        this.logError("SEMANTIC", message);
    }

    public void logSyntacticTree(SyntacticTree syntacticTree) {
        this.logSyntacticTree("", syntacticTree);
    }

	public void logSyntacticTree(String name, SyntacticTree syntacticTree) {
        if (this.hasEmittedErrors()) return;

        if (name.length() > 0) name = " (" + name + ")";

        this.logResultsSectionHeader("ARBOL SINTACTICO" + name);

        if (syntacticTree == null) System.out.println("ARBOL VACIO");
        else syntacticTree.print(System.out);
	}
}
