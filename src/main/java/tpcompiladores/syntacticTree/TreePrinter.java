package tpcompiladores.syntacticTree;

import java.io.PrintStream;

public class TreePrinter {
    private static final int INDENTATION_INCREMENT = 2;

    private PrintStream stream;
    private int indentationLevel = 0;

    public TreePrinter (PrintStream stream) {
        this.stream = stream;
    }

    public void printClass (SyntacticTree tree) {
        for (int i = 0; i < this.indentationLevel; i++) {
            this.stream.print(" ");
        }

        String className = tree.getClass().getName();
        className = className.substring(
            className.lastIndexOf(".") + 1
        );
        className = className.replaceAll("Tree$", "");

        this.stream.println(className);
    }

    public void increaseIndentation () {
        this.indentationLevel += INDENTATION_INCREMENT;
    }

    public void decreaseIndentation () {
        this.indentationLevel -= INDENTATION_INCREMENT;
    }
}
