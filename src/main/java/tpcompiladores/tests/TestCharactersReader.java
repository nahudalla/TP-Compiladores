package tpcompiladores.tests;

import tpcompiladores.FileChooser;
import tpcompiladores.lexer.CharactersReader;
import tpcompiladores.lexer.CharactersRecorder;
import tpcompiladores.lexer.LinesCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestCharactersReader {
    public static void main (String args[]) throws IOException {
        File file = FileChooser.showFileChooser("Please select a text file");
        CharactersReader charactersReader = new CharactersReader(file);
        LinesCounter linesCounter = new LinesCounter();
        CharactersRecorder charactersRecorder = new CharactersRecorder();

        charactersReader.subscribeToCharacters(linesCounter);
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

        System.out.println("The total number of lines is: " + linesCounter.getCurrentLineNumber());

        System.out.println("The recording of the whole text file is:\n" + charactersRecorder.getRecordedString() + '\n');

        System.out.println("Now let's duplicate all 'i's using the unGetLastReadCharacter");

        charactersReader = new CharactersReader(file);
        linesCounter = new LinesCounter();

        charactersReader.subscribeToCharacters(linesCounter);
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

        System.out.println("The total number of lines is: " + linesCounter.getCurrentLineNumber());
        System.out.println("The recording of the whole text file is:\n" + charactersRecorder.getRecordedString());
    }
}
