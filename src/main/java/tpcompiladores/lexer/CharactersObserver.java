package tpcompiladores.lexer;

public interface CharactersObserver {
    void processReadCharacter(Character readCharacter);
    void processUngetedCharacter(Character ungetedCharacter);
}
