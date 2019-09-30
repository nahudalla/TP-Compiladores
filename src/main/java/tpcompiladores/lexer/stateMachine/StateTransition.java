package tpcompiladores.lexer.stateMachine;

import tpcompiladores.lexer.semanticActions.SemanticAction;

public class StateTransition {
    private int nextState = 0;
    private SemanticAction semanticAction = null;

    public StateTransition(int nextState) {
        this.nextState = nextState;
    }

    public StateTransition(int nextState, SemanticAction semanticAction) {
        this.nextState = nextState;
        this.semanticAction = semanticAction;
    }

    public int getNextState() {
        return this.nextState;
    }

    public SemanticAction getSemanticAction() {
        return this.semanticAction;
    }
}
