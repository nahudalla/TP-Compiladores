package tpcompiladores.assembler_generation;

public enum Register {
    // PELIGRO!! No cambiar el orden de definición de los registros,
    // eliminar o agregar nuevos sin adaptar los métodos de abajo.

    // 32-bit
    EAX, EBX, ECX, EDX,
    // 16-bit
    AX, BX, CX, DX;

    public int getIndex () {
        return this.ordinal() % 4;
    }

    public static Register bits32FromIndex (int index) {
        return Register.values()[index % 4];
    }

    public static Register bits16FromIndex (int index) {
        return Register.values()[(index % 4) + 4];
    }
}
