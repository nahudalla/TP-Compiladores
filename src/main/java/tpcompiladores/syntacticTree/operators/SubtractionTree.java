package tpcompiladores.syntacticTree.operators;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class SubtractionTree extends SmallResultTypeTree {
    public SubtractionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Resta", leftTree, rightTree);
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult lhsResult = this.leftTree.generateCodeWithResult(printStream, registers);
        ASMOperationResult rhsResult = this.rightTree.generateCodeWithResult(printStream, registers);

        if (!lhsResult.isInRegister()) {
            Register tmpRegister = registers.useRegisterForType(this.leftTree.resultType());
            printStream.println("MOV " + tmpRegister + ", " + lhsResult);
            lhsResult = new ASMOperationResult(tmpRegister);
        }

        printStream.println("SUB " + lhsResult + ", " + rhsResult);

        registers.freeRegister(rhsResult.getRegister());

        return lhsResult;
    }
}
