package tpcompiladores.syntacticTree.operators;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.conversions.ConversionIntToLong;
import tpcompiladores.conversions.ConversionMatrix;
import tpcompiladores.conversions.ConversionMatrixCell;
import tpcompiladores.conversions.EmptyConversion;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SyntacticTreeWithConversions;

public class MultiplicationTree extends SyntacticTreeWithConversions {
    private static final ConversionMatrix matrix = new ConversionMatrix();

    public MultiplicationTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Multiplicacion", MultiplicationTree.matrix, leftTree, rightTree);
    }

    static {
        matrix.setCell(Type.INT, Type.INT, new ConversionMatrixCell(
            new ConversionIntToLong(),
            new ConversionIntToLong(),
            Type.LONG
        ));

        matrix.setCell(Type.INT, Type.LONG, new ConversionMatrixCell(
            new ConversionIntToLong(),
            new EmptyConversion(),
            Type.LONG
        ));

        matrix.setCell(Type.LONG, Type.INT, new ConversionMatrixCell(
            new EmptyConversion(),
            new ConversionIntToLong(),
            Type.LONG
        ));
        matrix.setCell(Type.LONG, Type.LONG, new ConversionMatrixCell(
            new EmptyConversion(),
            new EmptyConversion(),
            Type.LONG
        ));
    }

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult lhsResult = this.leftTree.generateCodeWithResult(printStream, registers);
        ASMOperationResult rhsResult = this.rightTree.generateCodeWithResult(printStream, registers);

        registers.useRegister(Register.EDX);

        if (!Register.EAX.equals(lhsResult.getRegister())) {
            if (Register.EAX.equals(rhsResult.getRegister())) {
                ASMOperationResult tmp = lhsResult;
                lhsResult = rhsResult;
                rhsResult = tmp;
            } else {
                registers.useRegister(Register.EAX);
                printStream.println("MOV " + Register.EAX + ", " + lhsResult);
                registers.freeRegister(lhsResult.getRegister());
                lhsResult = new ASMOperationResult(Register.EAX);
            }
        }

        printStream.println("IMUL " + lhsResult + ", " + rhsResult);

        registers.freeRegister(rhsResult.getRegister());
        registers.freeRegister(Register.EDX);

        return lhsResult;
    }
}
