package tpcompiladores.lexer;

public class LinesCounter implements CharactersObserver {
    private long numberOfLines = 1;

    public long getCurrentLineNumber(){
        return this.numberOfLines;
    }

    @Override
    public void processReadCharacter(Character readCharacter) {
        if(readCharacter.equals('\n')) {
            this.numberOfLines += 1;
        }
    }
}
