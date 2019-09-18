package tpcompiladores.lexer.stateMachine;

import tpcompiladores.lexer.CharacterFilter;

public class StateMachineBuilder {
    private int currentCharacterClass = 0;
    private CharacterFilter[] characterFilters;
    private StateTransition[][] stateTransitionMatrix;

    public StateMachineBuilder(int numberOfStates, int numberOfCharacterClasses) {
        this.stateTransitionMatrix = new StateTransition[numberOfStates][numberOfCharacterClasses];
        this.characterFilters = new CharacterFilter[numberOfCharacterClasses];
    }

    private int getNumberOfStates() {
        return this.stateTransitionMatrix.length;
    }

    private int getNumberOfCharacterClasses() {
        return this.stateTransitionMatrix[0].length;
    }

    public int addCharacterClass(CharacterFilter characterClass) {
        if (this.currentCharacterClass >= this.getNumberOfCharacterClasses()) {
            throw new IndexOutOfBoundsException("Se intentaron crear mas clases de las definidas inicialmente");
        }

        this.characterFilters[this.currentCharacterClass] = characterClass;

        return this.currentCharacterClass++;
    }

    public void addTransition(int startState, int characterClassID, StateTransition stateTransition) {
        this.stateTransitionMatrix[startState][characterClassID] = stateTransition;
    }

    public void addDefaultTransition(int startState, StateTransition defaultTransition) {
        if (startState >= this.getNumberOfStates() || startState < 0) {
            throw new IndexOutOfBoundsException("Se intentó usar un estado fuera del rango definido inicialmente");
        }

        for(int i = 0; i < this.getNumberOfCharacterClasses(); i++) {
            if (this.stateTransitionMatrix[startState][i] == null) {
                this.stateTransitionMatrix[startState][i] = defaultTransition;
            }
        }
    }

    public StateMachine build() {
        return new StateMachine(this.stateTransitionMatrix, this.characterFilters);
    }
}
