package tpcompiladores.lexer;

import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.stateMachine.StateMachine;
import tpcompiladores.symbolsTable.SymbolsTableEntry;

import java.io.IOException;

public class Lexer {
    private Integer nextToken = null;
    private StateMachine stateMachine;
    private CompilerContext compilerContext;
    private String symbolsTableReference;

    public Lexer(StateMachine stateMachine, CompilerContext compilerContext){
        this.stateMachine = stateMachine;
        this.compilerContext = compilerContext;
        compilerContext.setLexer(this);
    }

    public int getNextToken() throws IOException {
        this.nextToken = null;

        while(this.nextToken == null){
            Character readCharacter = this.compilerContext.getCharactersReader().getNextCharacter();
            this.stateMachine.performTransition(readCharacter, this.compilerContext);
        }

        this.compilerContext.getLogger().logRecognizedToken(
                this.nextToken,
                this.getCurrentLexeme()
        );

        return this.nextToken;
    }

    private String getCurrentLexeme() {
        if (this.symbolsTableReference == null) return null;

        return this.compilerContext
                .getSymbolsTable()
                .getEntry(this.symbolsTableReference)
                .getLexeme();
    }

    public void setNextToken(int nextToken) {
        this.nextToken = nextToken;
        this.symbolsTableReference = null;
    }

    public void setNextToken(int nextToken, String symbolsTableReference){
        this.nextToken = nextToken;
        this.symbolsTableReference = symbolsTableReference;
        SymbolsTableEntry tableReference = this.compilerContext.getSymbolsTable().getEntry(symbolsTableReference);
        this.compilerContext.getParser().setSymbolsTableReference(tableReference);
    }
}
