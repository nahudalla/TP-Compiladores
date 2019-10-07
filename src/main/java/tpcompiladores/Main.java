package tpcompiladores;


import tpcompiladores.parser.yacc_generated.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    private final static int EXIT_CODE_ON_ERRORS_EMITTED = 1;

    public static void main(String[] args) {
        if (args.length > 0) {
            Main.runSpecialCommand(args[0]);
        } else {
            File sourceFile = Main.askForSourceFile();
            Compiler compiler = Main.createCompilerInstance(sourceFile);

            compiler.run();

            Main.setExitCodeIfErrorsEmitted(compiler);
        }
    }

    private static void runSpecialCommand(String command) {
        switch (command) {
            case "--transitions": Compiler.printStateTransitionMatrix(); break;
            default:
                System.err.println("Unknown switch: " + command);
                System.exit(1);
        }
    }

    private static void consumeTokens (Compiler compiler) {
        int token;
        do {
            try {
                token = compiler.getContext().getLexer().getNextToken();
            } catch (IOException e) {
                e.printStackTrace();
                token = Parser.EOF;
            }
        } while (token != Parser.EOF);
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
