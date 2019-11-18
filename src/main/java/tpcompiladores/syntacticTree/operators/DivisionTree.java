package tpcompiladores.syntacticTree.operators;

import java.io.PrintStream;

import tpcompiladores.CompilerContext;
import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.assembler_generation.errors.DivisionByZero;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.ASMCodeTree;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.LeafTree;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.comparators.ComparisonTree;
import tpcompiladores.syntacticTree.conversions.SmallResultTypeTree;
import tpcompiladores.syntacticTree.if_tree.IfBranchesTree;
import tpcompiladores.syntacticTree.if_tree.IfComparisonTree;
import tpcompiladores.syntacticTree.if_tree.IfElseBranchTree;
import tpcompiladores.syntacticTree.if_tree.IfThenBranchTree;
import tpcompiladores.syntacticTree.if_tree.IfTree;

public class DivisionTree extends SmallResultTypeTree {
    private static boolean isFirstInstance = true;

    private SyntacticTree divisionByZeroCheck;

    public DivisionTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Division", leftTree, rightTree);

        SymbolsTableEntry zero = new SymbolsTableEntry();
        zero.setLexeme("0");
        zero.setUse(SymbolsTableEntryUse.CONSTANT);
        zero.setType(Type.INT);

        CompilerContext.getInstance().getSymbolsTable().addNumericConstant(zero);

        this.divisionByZeroCheck = new IfTree(
            new IfComparisonTree(ComparisonTree.equals(rightTree, new LeafTree(zero))),
            new IfBranchesTree(
                new IfThenBranchTree(new ASMCodeTree("JMP " + DivisionByZero.label)),
                new IfElseBranchTree(null)
            )
        );

        if (isFirstInstance) {
            isFirstInstance = false;
            CompilerContext.getInstance().getASMGenerator().addDumpable(
                new DivisionByZero()
            );
        }
    }

    @Override
    public ASMOperationResult generateCodeWithResult(PrintStream printStream, Registers registers) {
        this.divisionByZeroCheck.generateCodeWithResult(printStream, registers);

        if (Type.INT.equals(this.leftTree.resultType())) return this.generateDiv16(printStream, registers);
        else return this.generateDiv32(printStream, registers);
    }

    private ASMOperationResult generateDiv(
        Register xdx,
        Register xax,
        PrintStream printStream,
        Registers registers
    ) {
        ASMOperationResult lhsResult = this.leftTree.generateCodeWithResult(printStream, registers);
        ASMOperationResult rhsResult = this.rightTree.generateCodeWithResult(printStream, registers);

        registers.useRegister(xdx);

        if (xax.equals(rhsResult.getRegister())) {
            Register tmp = registers.useRegisterForType(this.rightTree.resultType());
            printStream.println("MOV " + tmp + ", " + xax);
            registers.freeRegister(xax);
            rhsResult = new ASMOperationResult(tmp);
        } else if (rhsResult.isConstant()) {
            Register tmp = registers.useRegisterForType(this.rightTree.resultType());
            printStream.println("MOV " + tmp + ", " + rhsResult);
            rhsResult = new ASMOperationResult(tmp);
        }

        if (!xax.equals(lhsResult.getRegister())) {
            registers.useRegister(xax);
            printStream.println("MOV " + xax + ", " + lhsResult);
            registers.freeRegister(lhsResult.getRegister());
            lhsResult = new ASMOperationResult(xax);
        }

        printStream.println("CWD");
        printStream.println("IDIV " + rhsResult);

        registers.freeRegister(rhsResult.getRegister());
        registers.freeRegister(xdx);

        return lhsResult;
    }

    private ASMOperationResult generateDiv16(
        PrintStream printStream,
        Registers registers
    ) {
        return this.generateDiv(Register.DX, Register.AX, printStream, registers);
    }

    private ASMOperationResult generateDiv32(
        PrintStream printStream,
        Registers registers
    ) {
        return this.generateDiv(Register.EDX, Register.EAX, printStream, registers);
    }
}
