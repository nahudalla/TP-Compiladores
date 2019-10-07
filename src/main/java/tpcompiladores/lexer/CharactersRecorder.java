package tpcompiladores.lexer;

public class CharactersRecorder implements CharactersObserver {
    private boolean isRecording = false;
    private String recording = "";
    private Character lastReadCharacter = null;

    public void turnOn(){
        this.recording = String.valueOf(this.lastReadCharacter);
        this.isRecording = true;
    }

    public void turnOff(){
        this.isRecording = false;
    }

    public boolean isRecording(){
        return this.isRecording;
    }

    public void forgetMostRecentCharacter() {
        if (this.recording.length() > 0){
            this.recording = this.recording.substring(0, this.recording.length() - 1);
        }
    }

    public String getRecordedString(){
        return this.recording;
    }

    @Override
    public void processReadCharacter(Character readCharacter) {
        if (readCharacter == null) readCharacter = 0x0;

        this.lastReadCharacter = readCharacter;

        if(this.isRecording){
            this.recording = this.recording.concat(readCharacter.toString());
        }
    }

    @Override
    public void processUngetedCharacter(Character ungetedCharacter) {
        this.forgetMostRecentCharacter();
    }
}
