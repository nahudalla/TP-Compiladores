package tpcompiladores.syntacticTree.operators;

import java.io.PrintStream;

import tpcompiladores.CompilerContext;
import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.assembler_generation.errors.AdditionOverflow;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;

public class AdditionTree extends SmallResultTypeTree {
    private static boolean isFirstInstance = true;

    public AdditionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Suma", leftTree, rightTree);

        if (isFirstInstance) {
            isFirstInstance = false;
            CompilerContext.getInstance().getASMGenerator().addDumpable(
                new AdditionOverflow()
            );
        }
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

        printStream.println("ADD " + lhsResult + ", " + rhsResult);
        printStream.println("JO " + AdditionOverflow.label);

        registers.freeRegister(rhsResult.getRegister());

        return lhsResult;
    }
}
