package com.feteforraine;

public class PisteAutotamponneuses {
    private Autotamponneuses[] listeAutotamponneuses;

    public PisteAutotamponneuses(){
        this(0);
    }
    public PisteAutotamponneuses(int nbAutotamponneuses){
        this.listeAutotamponneuses = new Autotamponneuses[nbAutotamponneuses];
    }

    public static void main(String[] args) {
        PisteAutotamponneuses piste = new PisteAutotamponneuses(5);
        for (int i = 0; i < piste.listeAutotamponneuses.length; i++) {
            Autotamponneuses a = new Autotamponneuses();
            boolean placementImpossible;
            do {
                placementImpossible = false;
                a.place((float) java.lang.Math.random(),(float) java.lang.Math.random());
                for (int j = 0; j < piste.listeAutotamponneuses.length; j++) {
                    if (a.collision(piste.listeAutotamponneuses[j])) {
                        placementImpossible = true;
                    }
                }
            } while (placementImpossible);
            piste.listeAutotamponneuses[i] = a;
        }
    }
}
