package tpcompiladores.tests;

import tpcompiladores.FileChooser;
import tpcompiladores.lexer.CharactersRecorder;
import tpcompiladores.lexer.LineNumber;
import tpcompiladores.lexer.fileInput.CharactersReader;

import java.io.File;
import java.io.IOException;

public class TestCharactersReader {
    public static void main (String[] args) throws IOException {
        File file = FileChooser.showFileChooser("Please select a text file");
        CharactersReader charactersReader = new CharactersReader(file);
        LineNumber lineNumber = new LineNumber();
        CharactersRecorder charactersRecorder = new CharactersRecorder();

        charactersReader.subscribeToCharacters(lineNumber);
        charactersReader.subscribeToCharacters(charactersRecorder);

        System.out.println("We'll test the notification system between this class and its observers after reading a test file");

        charactersRecorder.turnOn();
        Character lastReadCharacter;

        do {
            lastReadCharacter = charactersReader.getNextCharacter();
            if (lastReadCharacter != null) {
                System.out.print(lastReadCharacter);
            }
        } while (lastReadCharacter != null);

        System.out.println("The total number of lines is: " + lineNumber.getCurrentLineNumber());

        System.out.println("The recording of the whole text file is:\n" + charactersRecorder.getRecordedString() + '\n');

        System.out.println("Now let's duplicate all 'i's using the unGetLastReadCharacter");

        charactersReader = new CharactersReader(file);
        lineNumber = new LineNumber();

        charactersReader.subscribeToCharacters(lineNumber);
        charactersReader.subscribeToCharacters(charactersRecorder);

        charactersRecorder.turnOn();

        boolean unGetCalled = false;

        do {
            lastReadCharacter = charactersReader.getNextCharacter();
            if (lastReadCharacter != null) {
                if(lastReadCharacter.equals('i') && !unGetCalled){
                    charactersReader.unGetLastCharacter();
                    unGetCalled = true;
                } else if (!lastReadCharacter.equals('i') && unGetCalled){
                    unGetCalled = false;
                }
                System.out.print(lastReadCharacter);
            }
        } while (lastReadCharacter != null);

        System.out.println("The total number of lines is: " + lineNumber.getCurrentLineNumber());
        System.out.println("The recording of the whole text file is:\n" + charactersRecorder.getRecordedString());
    }
}
