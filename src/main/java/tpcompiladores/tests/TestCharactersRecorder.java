package tpcompiladores.tests;

import tpcompiladores.lexer.CharactersRecorder;

public class TestCharactersRecorder {
    public static void main(String args[]) {
        CharactersRecorder charactersRecorder = new CharactersRecorder();

        //Testing for all functions in CharactersRecorder except for "forgetMostRecentCharacter" and "reset"
        String testString = new String("This is the first line\nThis is the second line\nThis one is the third and last");

        charactersRecorder.turnOn();
        for(int i = 0; i < testString.length(); i++){
            Character readCharacter = testString.charAt(i);

            if(readCharacter.equals('\n') && charactersRecorder.isRecording()) {
                charactersRecorder.turnOff();
            }else if (readCharacter.equals('\n')) {
                charactersRecorder.turnOn();
            }

            charactersRecorder.processReadCharacter(readCharacter);
        }
        System.out.println("The recorder will record the first and last line from the test String:");
        System.out.println(testString);
        System.out.println("This is the recording:");
        System.out.println(charactersRecorder.getRecordedString());

        //Testing for reset and forgetMostRecentCharacter
        System.out.println("Now the recorder will reset");
        charactersRecorder.reset();
        System.out.println("With a second take it will forget every whitespace from the test Spring:");
        System.out.println(testString);

        charactersRecorder.turnOn();
        for(int i = 0; i < testString.length(); i++){
            Character readCharacter = testString.charAt(i);

            charactersRecorder.processReadCharacter(readCharacter);

            if(Character.isWhitespace(readCharacter)) {
                charactersRecorder.forgetMostRecentCharacter();
            }
        }
        System.out.println("This is the recording:");
        System.out.println(charactersRecorder.getRecordedString());
    }
}
