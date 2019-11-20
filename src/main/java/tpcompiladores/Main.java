package tpcompiladores;


import tpcompiladores.parser.yacc_generated.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private final static int EXIT_CODE_ON_ERRORS_EMITTED = 1;

    public static void main(String[] args) {
        String fileName = Main.getFileNameFromArgs(args);
        String specialCommand = Main.getSpecialCommandFromArgs(args);

        try {
            if (specialCommand != null) {
                Main.runSpecialCommand(specialCommand, fileName);
            } else {
                Main.runCompilation(fileName);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static String getSpecialCommandFromArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                return args[i];
            }
        }

        return null;
    }

    private static String getFileNameFromArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("--")) {
                return args[i];
            }
        }

        return null;
    }

    private static void runCompilation(String fileName) throws FileNotFoundException {
        Main.runCompilation(false, fileName);
    }

    private static void runCompilation(boolean verbose, String fileName) throws FileNotFoundException {
        File sourceFile;

        if (fileName != null) {
            sourceFile = new File(fileName);

            if (!sourceFile.exists() || ! sourceFile.isFile()) {
                System.err.println("No se ecuentra el archivo: " + fileName);
                System.exit(1);
            }
        } else {
            sourceFile = Main.askForSourceFile();
        }

        Compiler compiler = Main.createCompilerInstance(sourceFile);

        if (verbose) compiler.enableVerboseMode();

        compiler.run();

        Main.setExitCodeIfErrorsEmitted(compiler);
    }

    private static void runSpecialCommand(String command, String fileName) throws FileNotFoundException {
        switch (command) {
            case "--transitions": Compiler.printStateTransitionMatrix(); break;
            case "--verbose": Main.runCompilation(true, fileName); break;
            case "--help": Main.printHelpMessage(); break;
            default:
                System.err.println("Unknown switch: " + command);
                System.exit(1);
        }
    }

    private static void printHelpMessage() {
        System.out.println("HELP not implemented yet.");
    }

    private static void consumeTokens(Compiler compiler) {
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
