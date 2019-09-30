package tpcompiladores.lexer.stateMachine;

import tpcompiladores.CompilerContext;
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

    public void performTransition(Character readCharacter, CompilerContext compilerContext) {
        int characterClass = this.getCharacterClass(readCharacter, compilerContext);

        if (characterClass == -1) return;

        StateTransition stateTransition = this.transitionMatrix[this.currentState][characterClass];

        SemanticAction semanticAction = stateTransition.getSemanticAction();
        if (semanticAction != null) {
            stateTransition.getSemanticAction().run(compilerContext);
        }

        this.currentState = stateTransition.getNextState();
    }

    private int getCharacterClass (Character readCharacter, CompilerContext compilerContext) {
        for(int i = 0; i < this.characterClasses.length; i++) {
            if (this.characterClasses[i].matches(readCharacter)){
                return i;
            }
        }

        compilerContext.getLogger().logError("Caracter no esperado: " + readCharacter);

        return -1;
    }
}
