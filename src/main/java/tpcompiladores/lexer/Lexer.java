package tpcompiladores.lexer;

import tpcompiladores.Logger;
import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.lexer.stateMachine.AglunaStateMachine;
import tpcompiladores.lexer.stateMachine.StateMachine;
import tpcompiladores.symbolsTable.SymbolsTable;

import java.io.File;
import java.io.IOException;

public class Lexer {
    private Integer nextToken = null;
    private StateMachine stateMachine;
    private LexerContext lexerContext;
    private String symbolsTableReference;

    public Lexer(StateMachine stateMachine, LexerContext lexerContext){
        this.stateMachine = stateMachine;
        this.lexerContext = lexerContext;
    }

    public int getNextToken() throws IOException {
        this.nextToken = null;

        while(this.nextToken == null){
            Character readCharacter = this.lexerContext.getCharactersReader().getNextCharacter();
            this.stateMachine.performTransition(readCharacter, this.lexerContext);
        }

        this.lexerContext.getLogger().logRecognizedToken(
                this.nextToken,
                this.getCurrentLexeme()
        );

        return this.nextToken;
    }

    private String getCurrentLexeme() {
        if (this.symbolsTableReference == null) return null;

        return this.lexerContext
                .getSymbolsTable()
                .getEntry(this.symbolsTableReference)
                .getLexeme();
    }

    public void setNextToken(int nextToken) {
        this.setNextToken(nextToken, null);
    }

    public void setNextToken(int nextToken, String symbolsTableReference){
        this.nextToken = nextToken;
        this.symbolsTableReference = symbolsTableReference;
    }

    public static LexerContext createContext (File sourceFile) throws IOException {
        LexerContext context = new LexerContext();
        context.setCharactersReader(new CharactersReader(sourceFile));
        Lexer.setupLogger(context);
        Lexer.setupCharactersRecorder(context);
        Lexer.setupSymbolsTable(context);
        Lexer.setupLexer(context);

        return context;
    }

    private static void setupSymbolsTable(LexerContext context) {
        context.setSymbolsTable(new SymbolsTable());
    }

    private static void setupCharactersRecorder(LexerContext context) {
        CharactersRecorder recorder = new CharactersRecorder();
        context.getCharactersReader().subscribeToCharacters(recorder);
        context.setCharactersRecorder(recorder);
    }

    private static void setupLogger (LexerContext context) {
        LinesCounter linesCounter = new LinesCounter();

        context.getCharactersReader().subscribeToCharacters(linesCounter);

        context.setLogger(new Logger(linesCounter));
    }

    private static void setupLexer (LexerContext context) {
        Lexer lexer = new Lexer(
                AglunaStateMachine.getStateMachine(),
                context
        );
        context.setLexer(lexer);
    }
}
