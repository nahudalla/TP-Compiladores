package tpcompiladores.lexer.stateMachine;

import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.semanticActions.SemanticAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

        compilerContext.getLogger().logLexerError("Caracter no esperado: " + readCharacter);

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder semanticActionsStringBuilder = new StringBuilder();
        StringBuilder statesMatrixStringBuilder = new StringBuilder();

        statesMatrixStringBuilder
                .append("MATRIZ DE TRANSICION DE ESTADOS:")
                .append(System.lineSeparator());

        for (int i = 0; i < this.transitionMatrix.length; i++) {
            StateTransition[] stateTransitions = this.transitionMatrix[i];

            List<Integer> repeatedSemanticActionStates = new ArrayList<>();
            SemanticAction lastSemanticAction = null;

            for (int j = 0; j < stateTransitions.length; j++) {
                StateTransition transition = stateTransitions[j];

                statesMatrixStringBuilder
                        .append(transition.getNextState())
                        .append(",");

                SemanticAction semanticAction = transition.getSemanticAction();

                if (semanticAction != lastSemanticAction) {
                    if (lastSemanticAction != null) {
                        semanticActionsStringBuilder
                                .append(i)
                                .append(" --> ")
                                .append(Arrays.toString(repeatedSemanticActionStates.toArray()))
                                .append(": ")
                                .append(System.lineSeparator())
                                .append(lastSemanticAction.toString())
                                .append(System.lineSeparator())
                                .append(System.lineSeparator());
                    }

                    lastSemanticAction = semanticAction;
                    repeatedSemanticActionStates = new ArrayList<>();
                }

                repeatedSemanticActionStates.add(j);
            }

            if (lastSemanticAction != null) {
                semanticActionsStringBuilder
                        .append(i)
                        .append(" --> ")
                        .append(Arrays.toString(repeatedSemanticActionStates.toArray()))
                        .append(": ")
                        .append(System.lineSeparator())
                        .append(lastSemanticAction.toString())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());
            }

            statesMatrixStringBuilder.delete(
                    statesMatrixStringBuilder.length() - 1,
                    statesMatrixStringBuilder.length()
            );
            statesMatrixStringBuilder.append(System.lineSeparator());
        }

        StringBuilder characterClassesBuilder = new StringBuilder();
        for (int i = 0; i < this.characterClasses.length; i++) {
            CharacterFilter filter = this.characterClasses[i];

            characterClassesBuilder
                    .append("[")
                    .append(i)
                    .append("]: ")
                    .append(System.lineSeparator())
                    .append(filter.toString())
                    .append(System.lineSeparator());
        }

        statesMatrixStringBuilder
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("CLASES DE CARACTERES:")
                .append(System.lineSeparator())
                .append(characterClassesBuilder.toString())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("ACCIONES SEMANTICAS (ESTADO --> [CLASES DE CARACTERES]):")
                .append(System.lineSeparator())
                .append(semanticActionsStringBuilder.toString());

        return statesMatrixStringBuilder.toString();
    }
}
