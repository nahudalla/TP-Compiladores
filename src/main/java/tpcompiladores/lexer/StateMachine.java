package tpcompiladores.lexer;


import java.util.ArrayList;
import java.util.List;

public class StateMachine {
    private int currentState = 0;
    private StateTransition[][] transitionMatrix;
    private List<CharacterFilter> characterClasses = new ArrayList<>();

    public StateMachine (StateTransition[][] transitionMatrix, List characterClasses) {
        this.transitionMatrix = transitionMatrix;
        this.characterClasses = characterClasses;
    }

    public int getCurrentState() {
        return this.currentState;
    }

    public void performTransition(Character readCharacter, LexerContext lexerContext) {
        boolean matchedClass = false;
        int characterClass = -1;

        for(int i = 0; i < this.characterClasses.size() && !matchedClass; i++) {
            if (this.characterClasses.get(i).matches(readCharacter)){
                matchedClass = true;
                characterClass = i;
            }
        }

        this.transitionMatrix[this.currentState][characterClass].getSemanticAction().run(lexerContext);
        this.currentState = this.transitionMatrix[this.currentState][characterClass].getNextState();
    }
}
