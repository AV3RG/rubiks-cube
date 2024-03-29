package dev.slohth.rubikscube.cubit;

import dev.slohth.rubikscube.cube.Cube;

public class Cubit {

    private final int id;
    private final byte[] c;
    private final Cube cube;

    public Cubit(Cube cube, int id) {
        this.cube = cube; this.id = id;
        this.c = new byte[] { 0, 1, 2, 3, 4, 5 };
    }

    public boolean isSolved() {
        for (byte i = 0; i < this.c.length; i++) if (this.c[i] != i) return false;
        return true;
    }

    public void rotate(CubitRotation rotation) {
        byte[] v = new byte[] { c[0], c[2], c[5], c[4] };
        byte[] h = new byte[] { c[1], c[2], c[3], c[4] };
        byte[] b;

        switch (rotation) {
            case UP -> {
                b = this.lShift(v);
                this.c[0] = b[0]; this.c[2] = b[1]; this.c[5] = b[2]; this.c[4] = b[3];
            }
            case DOWN -> {
                b = this.rShift(v);
                this.c[0] = b[0]; this.c[2] = b[1]; this.c[5] = b[2]; this.c[4] = b[3];
            }
            case LEFT -> {
                b = this.lShift(h);
                System.arraycopy(b, 0, this.c, 1, b.length);
            }
            case RIGHT -> {
                b = this.rShift(h);
                System.arraycopy(b, 0, this.c, 1, b.length);
            }
        }
    }

    private byte[] rShift(byte[] b) {
        byte temp = b[b.length - 1];
        System.arraycopy(b, 0, b, 1, b.length - 1);
        b[0] = temp;
        return b;
    }

    private byte[] lShift(byte[] b) {
        byte temp = b[0];
        System.arraycopy(b, 1, b, 0, b.length - 1);
        b[b.length - 1] = temp;
        return b;
    }

    public int getIndexInCube() {
        for (int i = 0; i < cube.getCubits().length; i++) if (this.id == cube.getCubits()[i].getId()) return i;
        return -1;
    }

    public byte[] getOrientation() { return this.c; }

    public int getId() { return this.id; }

}
