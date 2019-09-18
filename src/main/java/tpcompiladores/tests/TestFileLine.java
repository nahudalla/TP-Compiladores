package tpcompiladores.tests;

import tpcompiladores.lexer.fileInput.FileLine;

public class TestFileLine {
    public static void main(String args[]){
        FileLine fileLine = new FileLine("This is a new line");

        System.out.println("We'll print the content of the fileLine using its getNextCharacter method");
        Character character = fileLine.getNextCharacter();
        while(character != '\n'){
            System.out.print(character);
            character = fileLine.getNextCharacter();
        }
    }
}
