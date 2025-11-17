package com.feteforraine;

public class PisteAutotamponneuses {
    private Autotamponneuses[] listeAutotamponneuses;
    private static int largeur = 10;
    private static int longueur = 10;

    public PisteAutotamponneuses() {
        this(0);
    }

    public PisteAutotamponneuses(int nbAutotamponneuses) {
        this.listeAutotamponneuses = new Autotamponneuses[nbAutotamponneuses];
    }

    @Override public String toString() {
        java.lang.StringBuilder rep = new StringBuilder();
        rep.append("Liste Autotamponneuses de la piste :\n");
        for (int i = 0; i < this.listeAutotamponneuses.length; i++) {
            rep.append(this.listeAutotamponneuses[i].toString());
            rep.append("\n");
        }
        return rep.toString();
    }

    public static void main(String[] args) {
        PisteAutotamponneuses piste = new PisteAutotamponneuses(5);
        //System.out.println("Liste Autotamponneuses de la piste :");
        for (int i = 0; i < piste.listeAutotamponneuses.length; i++) {
            Autotamponneuses a = new Autotamponneuses();
            boolean placementImpossible;
            do {
                placementImpossible = false;
                a.place((float) java.lang.Math.random()*largeur, (float) java.lang.Math.random()*longueur);
                for (int j = 0; j < i; j++) {
                    if (a.collision(piste.listeAutotamponneuses[j])) {
                        placementImpossible = true;
                        break;
                    }
                }
            } while (placementImpossible);
            piste.listeAutotamponneuses[i] = a;
            //System.out.println(a.toString());
        }
        System.out.println(piste.toString());
    }
}
