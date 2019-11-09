package tpcompiladores;

import tpcompiladores.lexer.CharactersRecorder;
import tpcompiladores.lexer.Lexer;
import tpcompiladores.lexer.LineNumber;
import tpcompiladores.lexer.fileInput.CharactersReader;
import tpcompiladores.lexer.stateMachine.AglunaStateMachine;
import tpcompiladores.lexer.stateMachine.StateMachine;
import tpcompiladores.parser.ParsingResult;
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
        ParsingResult result = this.context.getParser().parse();

        if (result.getCode() != 0) {
            this.context.getLogger().logParserError("Fallo en la etapa de parsing.");
        }

        this.context.getLogger().logSymbolsTable(
            this.context.getSymbolsTable()
        );

        this.context.getLogger().logSyntacticTree(
            result.getSyntacticTree()
        );
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
        LineNumber lineNumber = new LineNumber();
        Logger logger = Logger.getInstance();

        this.context.getCharactersReader().subscribeToCharacters(lineNumber);
        logger.setLineNumber(lineNumber);
        this.context.setLogger(logger);
    }

    private void setupLexer () {
        Lexer lexer = new Lexer(
                AglunaStateMachine.getStateMachine(),
                this.context
        );
        this.context.setLexer(lexer);
    }

    public static void printStateTransitionMatrix() {
        Logger logger = Logger.getInstance();
        StateMachine stateMachine = AglunaStateMachine.getStateMachine();

        logger.logMessage(stateMachine.toString());
    }

	public void enableVerboseMode() {
        this.context.getLogger().enablePrintRecognizedTokens();
        this.context.getLogger().enablePrintRecognizedSyntacticStructures();
	}
}
