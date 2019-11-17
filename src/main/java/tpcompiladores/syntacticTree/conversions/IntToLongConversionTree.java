package tpcompiladores.syntacticTree.conversions;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.LeafTree;
import tpcompiladores.syntacticTree.SyntacticTree;

public class IntToLongConversionTree extends SyntacticTree {
    public IntToLongConversionTree(SyntacticTree node) {
        super(node);
    }

    @Override
    public Type resultType() {
        return Type.LONG;
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult result = this.leftTree.generateCodeWithResult(printStream, registers);

        if (result.isConstant()) {
            Register resultRegister = registers.useRegister32();
            printStream.println("MOV " + resultRegister + ", " + result);
            return new ASMOperationResult(resultRegister);
        } else {
            Register extendedResult;

            if (result.isInRegister()) {
                extendedResult = result.getRegister().toRegister32();
            } else {
                extendedResult = registers.useRegister32();
            }

            Register ax = registers.useAXRegister();
            printStream.println("MOV " + ax + ", " + result);
            printStream.println("CWDE");
            printStream.println("MOV " + extendedResult + ", " + ax.toRegister32());
            registers.freeRegister(ax);
            return new ASMOperationResult(extendedResult);
        }
    }
}
