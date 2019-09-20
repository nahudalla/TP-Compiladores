package tpcompiladores.tests;

import tpcompiladores.Logger;
import tpcompiladores.lexer.*;

public class TestLogger {
    public static void main(String args[]){
        LinesCounter linesCounter = new LinesCounter();
        Logger logger = new Logger(linesCounter);

        System.out.print("Amount of lines without reading any characters: " + linesCounter.getCurrentLineNumber() + '\n');
        logger.logError("Constante entera fuera del rango permitido");
        logger.logWarning("Truncated of text");

    }
}
