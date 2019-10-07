package tpcompiladores.lexer.fileInput;

import tpcompiladores.lexer.CharactersObserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharactersReader {
    private List<CharactersObserver> subscribers = new ArrayList<>();
    private Character lastReadCharacter = null;
    private boolean unGetCalled = false;
    private FileLine fileLine;
    private String nextLineFromFile;
    private BufferedReader bufferedReader;


    public CharactersReader(File file) throws IOException {
        this.bufferedReader = new BufferedReader(new FileReader(file));
        this.readNewLine();
    }

    private boolean readNewLine() throws IOException {
        if (this.nextLineFromFile == null) {
            this.nextLineFromFile = this.bufferedReader.readLine();
        }

        if(this.nextLineFromFile == null) return false;

        String currentLine = this.nextLineFromFile;
        this.nextLineFromFile = this.bufferedReader.readLine();

        this.fileLine = new FileLine(currentLine, this.nextLineFromFile != null);

        return true;
    }

    private void notifyCharacterRead(Character readCharacter){
        for(CharactersObserver subscriber : this.subscribers) {
            subscriber.processReadCharacter(readCharacter);
        }
    }

    private void notifyCharacterUngeted(Character ungetedCharacter){
        for(CharactersObserver subscriber : this.subscribers) {
            subscriber.processUngetedCharacter(ungetedCharacter);
        }
    }

    public void subscribeToCharacters(CharactersObserver charactersObserver){
        this.subscribers.add(charactersObserver);
    }

    public Character getLastReadCharacter(){
        return this.lastReadCharacter;
    }

    public void unGetLastCharacter(){
        if (!this.unGetCalled) {
            this.unGetCalled = true;
            this.notifyCharacterUngeted(this.lastReadCharacter);
        }
    }

    public Character getNextCharacter() throws IOException {
        if (this.unGetCalled) {
            this.unGetCalled = false;

            Character lastReadCharacter = this.getLastReadCharacter();
            this.notifyCharacterRead(lastReadCharacter);

            return lastReadCharacter;
        }

        Character nextCharacter;

        if (this.fileLine == null) {
            nextCharacter = null;
        } else {
            nextCharacter = this.fileLine.getNextCharacter();

            if (nextCharacter == null && this.readNewLine()) {
                nextCharacter = this.fileLine.getNextCharacter();
            }
        }

        this.lastReadCharacter = nextCharacter;

        this.notifyCharacterRead(this.lastReadCharacter);

        return this.lastReadCharacter;
    }
}
