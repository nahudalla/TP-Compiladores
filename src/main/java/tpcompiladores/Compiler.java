package tpcompiladores;

import tpcompiladores.lexer.CharactersRecorder;
import tpcompiladores.lexer.Lexer;
import tpcompiladores.lexer.LinesCounter;
import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.lexer.stateMachine.AglunaStateMachine;
import tpcompiladores.parser.yacc_generated.Parser;
import tpcompiladores.symbolsTable.SymbolsTable;

import java.io.File;
import java.io.IOException;

public class Compiler {
    private CompilerContext context;

    public Compiler (File sourceFile) throws IOException {
        this.context = new CompilerContext();

        this.setupSharedContext(sourceFile);
        this.setupContextForLexer();
        this.setupContextForParser();
    }

    public void run () {
        int code = this.context.getParser().parse();

        if (code != 0) {
            this.context.getLogger().logError("Fallo en la etapa de parsing.");
        }
    }

    public CompilerContext getContext() {
        return this.context;
    }

    private void setupSharedContext(File sourceFile) throws IOException {
        this.context.setCharactersReader(new CharactersReader(sourceFile));
        this.setupLogger();
        this.context.setSymbolsTable(new SymbolsTable());
    }

    private void setupContextForLexer() {
        this.setupCharactersRecorder();
        this.setupLexer();
    }

    private void setupContextForParser() {
        this.context.setParser(new Parser(this.context));
    }

    private void setupCharactersRecorder() {
        CharactersRecorder recorder = new CharactersRecorder();
        this.context.getCharactersReader().subscribeToCharacters(recorder);
        this.context.setCharactersRecorder(recorder);
    }

    private void setupLogger () {
        LinesCounter linesCounter = new LinesCounter();

        this.context.getCharactersReader().subscribeToCharacters(linesCounter);
        this.context.setLogger(new Logger(linesCounter));
    }

    private void setupLexer () {
        Lexer lexer = new Lexer(
                AglunaStateMachine.getStateMachine(),
                this.context
        );
        this.context.setLexer(lexer);
    }
}
