package tpcompiladores.tests;

import java.util.ArrayList;
import java.util.List;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;

public class TestRegistersUsage {
  public static void main(String[] args) {
    Registers registers = new Registers();

    Register register = registers.useRegister16();

    List<Register> usedRegisters = new ArrayList<>(4);
    while (register != null) {
        System.out.println("Got register " + register);
        usedRegisters.add(register);
        try {
            register = registers.useRegister16();
        } catch (Error e) {
            register = null;
        }
    }

    for (Register register2 : usedRegisters) {
        registers.freeRegister(register2);
    }

    register = registers.useRegister32();
    while (register != null) {
        System.out.println("Got register " + register);
        usedRegisters.add(register);
        try {
            register = registers.useRegister32();
        } catch (Error e) {
            register = null;
        }
    }

    try {
        registers.useRegister16();
    } catch (Error err) {
        System.out.println(err.getMessage());
    }

    try {
        registers.useRegister32();
    } catch (Error err) {
        System.out.println(err.getMessage());
    }
}
}
