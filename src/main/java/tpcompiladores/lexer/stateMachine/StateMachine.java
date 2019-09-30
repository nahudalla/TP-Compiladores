package tpcompiladores.lexer.stateMachine;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.semanticActions.SemanticAction;

public class StateMachine {
    private int currentState = 0;
    private StateTransition[][] transitionMatrix;
    private CharacterFilter[] characterClasses;

    public StateMachine (StateTransition[][] transitionMatrix, CharacterFilter[] characterClasses) {
        this.transitionMatrix = transitionMatrix;
        this.characterClasses = characterClasses;
    }

    public int getCurrentState() {
        return this.currentState;
    }

    public void performTransition(Character readCharacter, LexerContext lexerContext) {
        int characterClass = this.getCharacterClass(readCharacter, lexerContext);

        if (characterClass == -1) return;

        StateTransition stateTransition = this.transitionMatrix[this.currentState][characterClass];

        SemanticAction semanticAction = stateTransition.getSemanticAction();
        if (semanticAction != null) {
            stateTransition.getSemanticAction().run(lexerContext);
        }

        this.currentState = stateTransition.getNextState();
    }

    private int getCharacterClass (Character readCharacter, LexerContext lexerContext) {
        for(int i = 0; i < this.characterClasses.length; i++) {
            if (this.characterClasses[i].matches(readCharacter)){
                return i;
            }
        }

        lexerContext.getLogger().logError("Caracter no esperado: " + readCharacter);

        return -1;
    }
}
