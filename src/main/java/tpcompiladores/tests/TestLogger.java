package tpcompiladores.tests;

import tpcompiladores.lexer.*;

public class TestLogger {
    public static void main(String args[]){
        LinesCounter linesCounter = new LinesCounter();
        Logger logger = new Logger(linesCounter);

        System.out.print("Amount of lines without reading any characters: " + linesCounter.getCurrentLineNumber() + '\n');
        long line = linesCounter.getCurrentLineNumber() +1;
        System.out.print("line:" + line); logger.logError("Lexico error");
        System.out.print("line:" + linesCounter.getCurrentLineNumber()); logger.logWarning("Truncated of text");


    }
}
