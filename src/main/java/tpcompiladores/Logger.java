package tpcompiladores;

import tpcompiladores.lexer.LinesCounter;
import tpcompiladores.lexer.TokenDisplayName;

import java.io.PrintStream;

public class Logger {
    private static final int MAX_LEXEME_PRINT_LENGTH = 10;
    private static final String ELLIPSIS = String.valueOf(Character.toChars(0x2026));

    private boolean lastLoggedIsToken = false;
    private boolean hasEmittedErrors = false;
    private LinesCounter linesCounter;

    public Logger(LinesCounter linesCounter){
        this.linesCounter=linesCounter;
    }

    public void logWarning(String message){
        this.printLineNumber(System.out, "WARNING");
        this.lastLoggedIsToken = false;
        System.out.println(message);
    }

    public void logError(String message){
        this.hasEmittedErrors = true;
        this.printLineNumber(System.err, "ERROR");
        this.lastLoggedIsToken = false;
        System.err.println(message);
    }

    public boolean hasEmittedErrors() {
        return this.hasEmittedErrors;
    }

    public void logRecognizedToken(Integer nextToken, String lexeme) {
        if (lexeme != null && lexeme.length() > MAX_LEXEME_PRINT_LENGTH) {
            lexeme = lexeme.substring(0, MAX_LEXEME_PRINT_LENGTH) + ELLIPSIS;
        }

        if (!this.lastLoggedIsToken) this.printTokenHeader();
        else System.out.print(" ");

        System.out.print(TokenDisplayName.get(nextToken));

        if (lexeme != null) {
            System.out.print("(" + lexeme + ")");
        }

        this.lastLoggedIsToken = true;
    }

    private void printLineNumber (PrintStream stream, String header) {
        if (this.lastLoggedIsToken) stream.println();

        stream.print(header + ": Linea "+linesCounter.getCurrentLineNumber()+": ");
    }

    private void printTokenHeader () {
        System.out.print("Tokens reconocidos: ");
    }
}
