package tpcompiladores.tests;

import tpcompiladores.lexer.LineNumber;

public class TestLinesCounter {
    public static void main(String[] args){
        LineNumber lineNumber = new LineNumber();

        System.out.print("Amount of lines without reading any characters: " + lineNumber.getCurrentLineNumber() + '\n');
        String testString = "This is the first line\nThis is the second line\nThis one is the third and last";
        for(int i = 0; i < testString.length(); i++){
            Character readCharacter = testString.charAt(i);
            lineNumber.processReadCharacter(readCharacter);
        }
        System.out.print("Trying with:\n"+ testString + '\n' + "The amount of lines is: " + lineNumber.getCurrentLineNumber());
    }
}
