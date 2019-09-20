package tpcompiladores.lexer;

import tpcompiladores.Logger;
import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.lexer.stateMachine.StateMachine;

import java.io.File;
import java.io.IOException;

public class Lexer {
    private Integer nextToken = null;
    private StateMachine stateMachine;
    private LexerContext lexerContext;

    public Lexer(StateMachine stateMachine, LexerContext lexerContext){
        this.stateMachine = stateMachine;
        this.lexerContext = lexerContext;
    }

    public Integer getNextToken() throws IOException {
        this.nextToken = null;

        while(this.nextToken == null){
            Character readCharacter = this.lexerContext.getCharactersReader().getNextCharacter();
            this.stateMachine.performTransition(readCharacter, this.lexerContext);
        }

        return this.nextToken;
    }

    public void setNextToken(int nextToken) {
        this.nextToken = nextToken;
    }

    public void setNextToken(int nextToken, String symbolsTableReference){
        this.setNextToken(nextToken);
    }

    public static LexerContext createContext (File sourceFile) throws IOException {
        LexerContext context = new LexerContext();

        context.setCharactersReader(new CharactersReader(sourceFile));
        Lexer.setupLogger(context);

        return context;
    }

    private static void setupLogger (LexerContext context) {
        LinesCounter linesCounter = new LinesCounter();

        context.getCharactersReader().subscribeToCharacters(linesCounter);

        context.setLogger(new Logger(linesCounter));
    }
}
