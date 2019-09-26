package tpcompiladores;

import tpcompiladores.lexer.Lexer;
import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.TokenNumbers;

import java.io.File;
import java.io.IOException;

public class Main {
    private final static int EXIT_CODE_ON_ERRORS_EMITTED = 1;

    public static void main(String[] args) {
        File sourceFile = Main.askForSourceFile();
        LexerContext context = Main.createContext(sourceFile);

        // Aca va el codigo para ejecutar el compilador
        Main.consumeTokens(context);

        Main.setExitCodeIfErrorsEmitted(context);
    }

    private static void consumeTokens (LexerContext lexerContext) {
        int token, num = 1;
        do {
            try {
                token = lexerContext.getLexer().getNextToken();
            } catch (IOException e) {
                e.printStackTrace();
                token = TokenNumbers.EOF;
            }
        } while (token != TokenNumbers.EOF);
    }

    private static File askForSourceFile () {
        File sourceFile = FileChooser.showFileChooser("Seleccione el archivo fuente del programa");

        if (sourceFile == null) {
            System.out.println("Ejecución cancelada");
            System.exit(0);
        }

        return sourceFile;
    }

    private static LexerContext createContext (File sourceFile) {
        if (sourceFile == null) return null;

        LexerContext context = null;
        try {
            context = Lexer.createContext(sourceFile);
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo");
            System.exit(1);
        }

        return context;
    }

    private static void setExitCodeIfErrorsEmitted (LexerContext context) {
        Logger logger = context.getLogger();

        if (logger.hasEmittedErrors()) {
            System.exit(Main.EXIT_CODE_ON_ERRORS_EMITTED);
        }
    }
}
