package tpcompiladores;

import tpcompiladores.lexer.LinesCounter;
import tpcompiladores.lexer.TokenDisplayName;

public class Logger {
    private boolean lastLoggedIsToken = false;
    private boolean hasEmittedErrors = false;
    private LinesCounter linesCounter;

    public Logger(LinesCounter linesCounter){
        this.linesCounter=linesCounter;
    }

    public void logWarning(String message){
        this.printLineNumber();
        this.lastLoggedIsToken = false;
        System.out.println(message);
    }

    public void logError(String message){
        this.hasEmittedErrors = true;
        this.lastLoggedIsToken = false;
        this.printLineNumber();
        System.err.println(message);
    }

    public boolean hasEmittedErrors() {
        return this.hasEmittedErrors;
    }

    public void logRecognizedToken(Integer nextToken) {
        if (!this.lastLoggedIsToken) this.printTokenHeader();
        else System.out.print(" ");

        System.out.print(TokenDisplayName.get(nextToken));

        this.lastLoggedIsToken = true;
    }

    private void printLineNumber () {
        if (this.lastLoggedIsToken) System.out.println();

        System.out.print("Linea "+linesCounter.getCurrentLineNumber()+": ");
    }

    private void printTokenHeader () {
        System.out.print("Tokens reconocidos: ");
    }
}
