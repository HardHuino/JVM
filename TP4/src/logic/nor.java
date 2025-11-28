package logic;

public class nor extends gate {

    @Override
    protected void updateQ() {
        Q = !(A || B);
    }

    // CONSTRUCTEURS
    public nor(boolean A, boolean B, int cyclesMax) { super(A, B, cyclesMax); }
    public nor(boolean A, boolean B) {
        super(A,B);
    }
    public nor() {
        super();
    }
}
