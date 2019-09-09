package tpcompiladores.lexer;

public class Logger {
    LinesCounter linesCounter;

    public Logger(LinesCounter linesCounter){
        this.linesCounter=linesCounter;
    }

    public void logWarning(String message){
        System.out.println("Linea "+linesCounter.getCurrentLineNumber()+": "+  message);
    }

    public void logError(String message){
        System.err.println("Linea "+linesCounter.getCurrentLineNumber()+": "+ message);
    }

}