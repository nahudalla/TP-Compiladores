package tpcompiladores.lexer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CharactersReader {
    private List<CharactersObserver> subscribers = new ArrayList<>();
    private Character lastReadCharacter = null;
    private boolean unGetCalled = false;
    private FileLine fileLine;
    private BufferedReader bufferedReader;


    public CharactersReader(File file) throws IOException {
        this.bufferedReader = new BufferedReader(new FileReader(file));
        this.readNewLine();
    }

    private boolean readNewLine() throws IOException {
        String line = bufferedReader.readLine();

        if(line == null) return false;

        this.fileLine = new FileLine(line);

        return true;
    }

    private void notifyCharacterRead(Character readCharacter){
        for(CharactersObserver subscriber: this.subscribers) {
            subscriber.processReadCharacter(readCharacter);
        }
    }

    public void subscribeToCharacters(CharactersObserver charactersObserver){
        this.subscribers.add(charactersObserver);
    }

    public Character getLastReadCharacter(){
        return this.lastReadCharacter;
    }

    public void unGetLastCharacter(){
        this.unGetCalled = true;
    }

    public Character getNextCharacter() throws IOException {
        if (this.unGetCalled) {
            this.unGetCalled = false;
            this.notifyCharacterRead(this.getLastReadCharacter());
            return getLastReadCharacter();
        }

        this.lastReadCharacter = this.fileLine.getNextCharacter();

        if(this.lastReadCharacter == null) {
            if(!this.readNewLine()) return null;

            this.lastReadCharacter = this.fileLine.getNextCharacter();
        }

        this.notifyCharacterRead(lastReadCharacter);
        return this.lastReadCharacter;
    }
}
