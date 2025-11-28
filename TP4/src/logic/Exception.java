package logic;

class ExceptionPorteLogique extends Exception {
    public ExceptionPorteLogique(String message) {
        super(message);
    }
}

class ExceptionPorteAChanger extends ExceptionPorteLogique {
    public ExceptionPorteAChanger(String message) {
        super(message);
    }
}

class ExceptionBasculeAReparer extends Exception {
    private String porteDefectueuse;

    public ExceptionBasculeAReparer(String porteDefectueuse) {
        super("La bascule doit être réparée. Porte défectueuse : " + porteDefectueuse);
        this.porteDefectueuse = porteDefectueuse;
    }

    public String getPorteDefectueuse() {
        return porteDefectueuse;
    }
}
