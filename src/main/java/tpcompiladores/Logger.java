package tpcompiladores;

import tpcompiladores.lexer.LinesCounter;

public class Logger {
    private boolean hasEmittedErrors = false;
    private LinesCounter linesCounter;

    public Logger(LinesCounter linesCounter){
        this.linesCounter=linesCounter;
    }

    public void logWarning(String message){
        System.out.println("Linea "+linesCounter.getCurrentLineNumber()+": "+  message);
    }

    public void logError(String message){
        this.hasEmittedErrors = true;
        System.err.println("Linea "+linesCounter.getCurrentLineNumber()+": "+ message);
    }

    public boolean hasEmittedErrors() {
        return hasEmittedErrors;
    }
}
