package tpcompiladores.tests;

import tpcompiladores.lexer.LinesCounter;

public class TestLinesCounter {
    public static void main(String args[]){
        LinesCounter linesCounter = new LinesCounter();

        System.out.print("Amount of lines without reading any characters: " + linesCounter.getCurrentLineNumber() + '\n');
        String testString = new String("This is the first line\nThis is the second line\nThis one is the third and last");
        for(int i = 0; i < testString.length(); i++){
            Character readCharacter = testString.charAt(i);
            linesCounter.processReadCharacter(readCharacter);
        }
        System.out.print("Trying with:\n"+ testString + '\n' + "The amount of lines is: " + linesCounter.getCurrentLineNumber());
    }
}
