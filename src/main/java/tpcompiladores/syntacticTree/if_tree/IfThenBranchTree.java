package tpcompiladores.syntacticTree.if_tree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IfThenBranchTree extends SyntacticTree {
    private static int labelCounter = 0;

    public IfThenBranchTree(SyntacticTree ifThenBranchTree) {
        super(ifThenBranchTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        this.leftTree.generateCodeWithResult(printStream, registers);

        String labelElseEnd = "IfElseBranchEnd_" + labelCounter++;

        printStream.println("JMP " + labelElseEnd);
        printStream.println(IfTree.jumpLabels.pop() + ":");

        IfTree.jumpLabels.push(labelElseEnd);

        return null;
    }
}
