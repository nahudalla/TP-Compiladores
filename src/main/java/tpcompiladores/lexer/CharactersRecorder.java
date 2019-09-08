package tpcompiladores.lexer;

public class CharactersRecorder implements CharactersObserver {
    private boolean isRecording = false;
    private String recording = new String();

    public void turnOn(){
        this.recording = new String();
        this.isRecording = true;
    }

    public void turnOff(){
        this.isRecording = false;
    }

    public boolean isRecording(){
        return this.isRecording;
    }

    public void forgetMostRecentCharacter(){
        this.recording = this.recording.substring(0,recording.length()-1);
    }

    public String getRecordedString(){
        return this.recording;
    }

    @Override
    public void processReadCharacter(Character readCharacter) {
        if(isRecording){
            this.recording = this.recording.concat(readCharacter.toString());
        }
    }
}