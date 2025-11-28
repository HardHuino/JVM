package logic;

public class basculeRS {
    private nor Qgate;
    private nor notQgate;

    private boolean S;  // entrée Set
    private boolean R;  // entrée Reset

    private void updateQ() throws ExceptionBasculeAReparer {
        boolean stable = false;
        boolean oldQ, oldNotQ;

        try {
            // Boucle jusqu’à stabilisation des deux NOR (nécessaire car elles sont croisées)
            while (!stable) {
                oldQ = Qgate.getQ();
                oldNotQ = notQgate.getQ();

                Qgate.setA(R);
                Qgate.setB(notQgate.getQ());

                notQgate.setA(S);
                notQgate.setB(Qgate.getQ());

                // Stabilisé si aucune des deux sorties ne change
                stable = (oldQ == Qgate.getQ() &&
                        oldNotQ == notQgate.getQ());
            }
        } catch (ExceptionPorteAChanger e) {
            // Déterminer quelle porte a généré l'exception
            String porteDefectueuse = "Inconnue";
            try {
                Qgate.getQ();
            } catch (ExceptionPorteAChanger ex) {
                porteDefectueuse = "Qgate (porte produisant Q)";
            }
            try {
                notQgate.getQ();
            } catch (ExceptionPorteAChanger ex) {
                if (porteDefectueuse.equals("Inconnue")) {
                    porteDefectueuse = "notQgate (porte produisant Q̄)";
                } else {
                    porteDefectueuse = "Les deux portes";
                }
            }
            throw new ExceptionBasculeAReparer(porteDefectueuse);
        }
    }

    // CONSTRUCTEURS
    public basculeRS(boolean S, boolean R, int cyclesMaxQgate, int cyclesMaxNotQgate) throws ExceptionBasculeAReparer  {
        if (R == true && S == true) {
            throw new RuntimeException("R et S invalides");
        }
        this.Qgate = new nor(true,true, cyclesMaxQgate);
        this.notQgate = new nor(false,false, cyclesMaxNotQgate);
        this.S = S;
        this.R = R;
        updateQ();
    }
    public basculeRS(boolean S, boolean R) throws ExceptionBasculeAReparer  {
        this(S, R, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    public basculeRS() throws ExceptionBasculeAReparer {
        this(false, false);
    }

    // SETTERS
    public void setS(boolean S) throws ExceptionBasculeAReparer  {
        this.S = S;
        updateQ();
    }
    public void setR(boolean R) throws ExceptionBasculeAReparer  {
        this.R = R;
        updateQ();
    }

    // GETTERS
    public boolean getS() {
        return S;
    }
    public boolean getR() {
        return R;
    }
    public boolean getQ() throws ExceptionBasculeAReparer  {
        try {
            return Qgate.getQ();
        } catch (ExceptionPorteAChanger e) {
            throw new ExceptionBasculeAReparer("Qgate (porte produisant Q)");
        }
    }
    public boolean getNonQ() throws ExceptionBasculeAReparer  {
        try {
            return notQgate.getQ();
        } catch (ExceptionPorteAChanger e) {
            throw new ExceptionBasculeAReparer("notQgate (porte produisant Q̄)");
        }
    }

    // AFFICHAGE
    public void print() throws ExceptionBasculeAReparer {
        System.out.println("Bascule RS : ");
        System.out.println("S = " + getS() + "   R = " + getR());
        System.out.println("Q = " + getQ() + "   Q̄ = " + getNonQ());
        System.out.println("Cycles Qgate : " + Qgate.getCyclesActuels() + "/" + Qgate.getCyclesMax());
        System.out.println("Cycles notQgate : " + notQgate.getCyclesActuels() + "/" + notQgate.getCyclesMax());
        System.out.println("-------------------------");
    }
}
