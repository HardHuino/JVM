package logic;

public class nand extends gate {

    @Override
    protected void updateQ() {
        Q = !(A && B);
    }

    // CONSTRUCTEURS
    public nand(boolean A, boolean B, int cyclesMax) { super(A, B, cyclesMax); }
    public nand(boolean A, boolean B) {
        super(A, B);
    }
    public nand() {
        super();
    }
}
