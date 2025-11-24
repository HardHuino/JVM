package mem;

import comp.Complexe;

import java.util.ArrayList;
import java.util.List;

public class ComplexeMemoire extends Complexe {
    private List<String> historique;
    private static List<String> memoireCollective = new ArrayList<>();

    // CONSTRUCTEURS

    public ComplexeMemoire(double re, double im) {
        super(re, im);
        this.historique = new ArrayList<>();
    }

    public ComplexeMemoire(Complexe c) {
        this (c.getRe(), c.getIm());
    }

    public ComplexeMemoire(ComplexeMemoire cm) {
        this (cm.getRe(), cm.getIm());
    }

    public ComplexeMemoire() {
        this(0.0, 0.0);
    }

    // GETTERS

    public double getRe() { return super.getRe(); }
    public double getIm() { return super.getIm(); }

    public Complexe getComplexe() {
        return new Complexe(super.getRe(), super.getIm());
    }

    public List<String> getHistorique() {
        return new ArrayList<>(historique);
    }

    public static List<String> getMemoireCollective() {
        return new ArrayList<>(memoireCollective); // copie défensive
    }

    // SETTERS

    private void ajouterHistorique(String message) {
        historique.add(message);
    }

    private static void ajouterMemoireCollective(String message) {
        memoireCollective.add(message);
    }

    // OPERATIONS

    @Override
    public Complexe addition(Complexe autre) {
        Complexe avant = this;
        Complexe resultat = super.addition(autre);

        String message = "Addition : " + avant + " + " + autre + " = " + resultat;
        this.ajouterHistorique(message);
        ajouterMemoireCollective(message);

        return resultat;
    }

    @Override
    public Complexe multiplication(Complexe autre) {
        Complexe avant = this;
        Complexe resultat = super.multiplication(autre);

        String message = "Multiplication : " + avant + " * " + autre + " = " + resultat;
        this.ajouterHistorique(message);
        ajouterMemoireCollective(message);

        return resultat;
    }

    public static void main(String[] args) {
        ComplexeMemoire a = new ComplexeMemoire(1,2);
        ComplexeMemoire b = new ComplexeMemoire(1,2);
        Complexe c = new Complexe(1,2);

        // Verification que des differences d'historiques ne changent pas equals
        a.addition(new Complexe(0,0));

        System.out.println("TEST equals ComplexeMemoire ComplexeMemoire : " + (a.equals(b) ? "OK" : "FAIL"));
        System.out.println("TEST equals ComplexeMemoire Complexe : " + (a.equals(c) ? "OK" : "FAIL")); // retourne FAIL car types differents
    }
}
