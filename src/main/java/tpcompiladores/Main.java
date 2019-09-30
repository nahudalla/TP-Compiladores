package tpcompiladores;

import tpcompiladores.lexer.TokenNumbers;
import tpcompiladores.parser.yacc_generated.Parser;

import java.io.File;
import java.io.IOException;

public class Main {
    private final static int EXIT_CODE_ON_ERRORS_EMITTED = 1;

    public static void main(String[] args) {
        File sourceFile = Main.askForSourceFile();
        Compiler compiler = Main.createCompilerInstance(sourceFile);

        // Aca va el codigo para ejecutar el compilador
        compiler.run();
//        Main.consumeTokens(compiler);

        Main.setExitCodeIfErrorsEmitted(compiler);
    }

    private static void consumeTokens (Compiler compiler) {
        int token;
        do {
            try {
                token = compiler.getContext().getLexer().getNextToken();
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

    private static Compiler createCompilerInstance(File sourceFile) {
        if (sourceFile == null) return null;

        Compiler compiler = null;
        try {
            compiler = new Compiler(sourceFile);
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo");
            System.exit(1);
        }

        return compiler;
    }

    private static void setExitCodeIfErrorsEmitted (Compiler compiler) {
        Logger logger = compiler.getContext().getLogger();

        if (logger.hasEmittedErrors()) {
            System.exit(Main.EXIT_CODE_ON_ERRORS_EMITTED);
        }
    }
}
