package tpcompiladores.syntacticTree;

import java.io.PrintStream;

public class TreePrinter {
    private static final int INDENTATION_INCREMENT = 2;

    private PrintStream stream;
    private int indentationLevel = 0;

    public TreePrinter (PrintStream stream) {
        this.stream = stream;
    }

    public PrintStream getStream () {
        return this.stream;
    }

    public void printIndentation () {
        for (int i = 0; i < this.indentationLevel; i++) {
            this.stream.print(" ");
        }
    }

    public void printClassName (SyntacticTree tree) {
        String className = tree.getClass().getName();
        className = className.substring(
            className.lastIndexOf(".") + 1
        );
        className = className.replaceAll("Tree$", "");

        this.stream.print(className);
    }

    public void printClass (SyntacticTree tree) {
        this.printIndentation();
        this.printClassName(tree);
        this.stream.println();
    }

    public void increaseIndentation () {
        this.indentationLevel += INDENTATION_INCREMENT;
    }

    public void decreaseIndentation () {
        this.indentationLevel -= INDENTATION_INCREMENT;
    }
}
