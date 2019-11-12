package tpcompiladores.assembler_generation;

import java.util.Arrays;

public class Registers {
    private final Boolean[] registersUsage = new Boolean[4];

    public Registers() {
        Arrays.fill(this.registersUsage, false);
    }

    private int useFreeRegister () {
        int index = Arrays.asList(this.registersUsage).indexOf(false);

        if (index == -1) throw new Error("Could not find empty register to use!");

        this.registersUsage[index] = true;

        return index;
    }

    public Register useRegister32 () {
        return Register.bits32FromIndex(this.useFreeRegister());
    }

    public Register useRegister16 () {
        return Register.bits16FromIndex(this.useFreeRegister());
    }

    public void freeRegister (Register register) {
        this.registersUsage[register.getIndex()] = false;
    }

    public boolean isUsed (Register register) {
        return this.registersUsage[register.getIndex()];
    }

    public boolean isFree (Register register) {
        return !this.isUsed(register);
    }
}
