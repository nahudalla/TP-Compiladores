package tpcompiladores.lexer;

public class StateTransition {
    private int nextState = 0;
    private SemanticAction semanticAction;

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
