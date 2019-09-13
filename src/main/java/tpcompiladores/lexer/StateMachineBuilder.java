package tpcompiladores.lexer;

public class StateMachineBuilder {
    private int currentCharacterClass = 0;
    private CharacterFilter[] characterClasses;
    private StateTransition[][] stateTransitionMatrix;

    public StateMachineBuilder(int states, int numberOfCharacterClasses) {
        this.stateTransitionMatrix = new StateTransition[states][numberOfCharacterClasses];
        this.characterClasses = new CharacterFilter[numberOfCharacterClasses];
    }

    public int getStates() {
        return this.stateTransitionMatrix.length;
    }

    public int getCharacterClasses() {
        return this.stateTransitionMatrix[0].length;
    }

    public int addCharacterClass(CharacterFilter characterClass) {
        if (this.currentCharacterClass >= this.getCharacterClasses()) return -1;

        this.characterClasses[this.currentCharacterClass] = characterClass;

        return this.currentCharacterClass++;
    }

    public void addTransition(int startState, int characterClassID, StateTransition stateTransition) {
        this.stateTransitionMatrix[startState][characterClassID] = stateTransition;
    }

    public void addDefaultTransition(int startState, StateTransition defaultTransition) {
        for(int i = 0; i < this.stateTransitionMatrix[startState].length; i++) {
            if (this.stateTransitionMatrix[startState][i] == null) {
                this.stateTransitionMatrix[startState][i] = defaultTransition;
            }
        }
    }

    public StateMachine build() {
        return new StateMachine(this.stateTransitionMatrix, this.characterClasses);
    }
}
