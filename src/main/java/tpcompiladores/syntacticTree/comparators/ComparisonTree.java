package tpcompiladores.syntacticTree.comparators;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class ComparisonTree extends SmallResultTypeTree {
    private String jumpStr;

    private ComparisonTree(String jumpStr, SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Comparacion", leftTree, rightTree);

        this.jumpStr = jumpStr;
    }

    public static ComparisonTree equals (SyntacticTree leftTree, SyntacticTree rightTree) {
        return new ComparisonTree("JNE", leftTree, rightTree);
    }

    public static ComparisonTree greaterOrEquals (SyntacticTree leftTree, SyntacticTree rightTree) {
        return new ComparisonTree("JL", leftTree, rightTree);
    }

    public static ComparisonTree greater (SyntacticTree leftTree, SyntacticTree rightTree) {
        return new ComparisonTree("JLE", leftTree, rightTree);
    }

    public static ComparisonTree lessOrEquals (SyntacticTree leftTree, SyntacticTree rightTree) {
        return new ComparisonTree("JG", leftTree, rightTree);
    }

    public static ComparisonTree less (SyntacticTree leftTree, SyntacticTree rightTree) {
        return new ComparisonTree("JGE", leftTree, rightTree);
    }

    public static ComparisonTree notEquals (SyntacticTree leftTree, SyntacticTree rightTree) {
        return new ComparisonTree("JE", leftTree, rightTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult leftHandSide = this.leftTree.generateCodeWithResult(printStream, registers);
        ASMOperationResult rightHandSide = this.rightTree.generateCodeWithResult(printStream, registers);

        Register tmpRegister = null;

        if (
            leftHandSide.isConstant() ||
            (leftHandSide.isInVariable() && rightHandSide.isInVariable())
        ) {
            tmpRegister = registers.useRegisterForType(this.leftTree.resultType());
            printStream.println("MOV " + tmpRegister + ", " + leftHandSide);
            leftHandSide = new ASMOperationResult(tmpRegister);
        }

        printStream.println("CMP " + leftHandSide + ", " + rightHandSide);

        if (tmpRegister != null) registers.freeRegister(tmpRegister);

        return ASMOperationResult.incompleteJump(this.jumpStr);
    }
}
