package logic;

abstract class gate {

    protected boolean A,B;
    protected boolean Q;

    protected int cyclesMax;
    protected int cyclesCurrent;

    protected abstract void updateQ();

    private void incrementCycle() throws ExceptionPorteAChanger {
        cyclesCurrent++;
        if (cyclesCurrent >= cyclesMax) {
            throw new ExceptionPorteAChanger("Porte à changer : nombre de cycles maximum atteint (" + cyclesMax + ")");
        }
    }

    // CONSTRUCTEURS
    public gate(boolean A, boolean B, int cyclesMax) {
        this.A = A;
        this.B = B;
        this.cyclesMax = cyclesMax;
        this.cyclesCurrent = 0;
        updateQ();
    }
    public gate(boolean A, boolean B) {
        this.A = A;
        this.B = B;
        updateQ();  // Calcul initial de la sortie
    }
    public gate() {
        this(false,false);
    }

    // SETTERS
    public void setA(boolean A) throws ExceptionPorteAChanger {
        incrementCycle();
        this.A = A;
        updateQ();
    }
    public void setB(boolean B) throws ExceptionPorteAChanger {
        incrementCycle();
        this.B = B;
        updateQ();
    }

    // GETTERS
    public boolean getA() throws ExceptionPorteAChanger {
        incrementCycle();
        return this.A;
    }
    public boolean getB() throws ExceptionPorteAChanger {
        incrementCycle();
        return this.B;
    }
    public boolean getQ() throws ExceptionPorteAChanger {
        incrementCycle();
        return this.Q;
    }

    public int getCyclesActuels() {
        return cyclesCurrent;
    }

    public int getCyclesMax() {
        return cyclesMax;
    }
}
