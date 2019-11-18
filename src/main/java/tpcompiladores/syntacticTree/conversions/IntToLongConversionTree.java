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
        } else if (!Register.AX.equals(result.getRegister())){
            Register extendedResult;

            if (result.isInRegister()) {
                extendedResult = result.getRegister().toRegister32();
            } else {
                extendedResult = registers.useRegister32();
            }

            registers.useRegister(Register.AX);

            printStream.println("MOV " + Register.AX + ", " + result);
            printStream.println("CWDE");
            printStream.println("MOV " + extendedResult + ", " + Register.EAX);

            registers.freeRegister(Register.AX);

            return new ASMOperationResult(extendedResult);
        } else {
            printStream.println("CWDE");

            return new ASMOperationResult(Register.EAX);
        }
    }
}
