package tpcompiladores.lexer;

public class LineNumber implements CharactersObserver {
    private long numberOfLines = 1;

    public long getCurrentLineNumber(){
        return this.numberOfLines;
    }

    @Override
    public void processReadCharacter(Character readCharacter) {
        if(readCharacter != null && readCharacter.equals('\n')) {
            this.numberOfLines += 1;
        }
    }

    @Override
    public void processUngetedCharacter(Character ungetedCharacter) {
        if(ungetedCharacter != null && ungetedCharacter.equals('\n')) {
            this.numberOfLines -= 1;
        }
    }
}
