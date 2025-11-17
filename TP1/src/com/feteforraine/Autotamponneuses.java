package com.feteforraine;

import java.util.Objects; // pour equals()

public class Autotamponneuses {
    private final int id;
    private static int last_id=0;
    float x; // package-private pour ma classe de test
    float y; // package-private pour ma classe de test
    private boolean occupee;
    private String nomOccupant;
    private boolean allumee;
    private boolean clignotante;
    private static final int DISTANCE_MINIMAL=1;

    public Autotamponneuses() {

        /* Version sans appel du deuxieme constructeur :
        x = -1.0f;
        y = -1.0f;
        occupee = false;
        nomOccupant = NULL;
        allumee = false;
        clignotante = false;
         */

        // Version avec appel du deuxieme constructeur (plus concis) :
        this (-1.0f,-1.0f);
    }

    public Autotamponneuses(float coordX, float coordY) {
        id = last_id++;
        x = coordX;
        y = coordY;
        occupee = false;
        nomOccupant = "";
        allumee = false;
        clignotante = false;
    }

    public boolean estOccupee() {
        return occupee;
    }

    public String getNomOccupant() {
        return nomOccupant;
    }

    public boolean estAllumee() {
        return allumee;
    }

    public boolean estClignotante() {
        return clignotante;
    }

    @Override public String toString() {
        return "[" + this.id + "] ("
                + this.x + "," + this.y + ")"
                + (this.estOccupee() ? " occupée " + "(" + this.nomOccupant + ") " : " libre")
                + (this.estAllumee() ? " allumée" : " éteinte")
                + (this.estClignotante() ? " clignotante" : " non clignotante");
    }

    boolean place(float coordX, float coordY) {
        if (x == coordX && y == coordY) {
            return false;
        }
        x = coordX;
        y = coordY;
        return true;
    }
    boolean ajouteOccupant(String nom) {
        if (occupee) {
            return false;
        }
        occupee = true;
        nomOccupant = nom;
        return true;
    }
    boolean allume() {
        if (allumee) {
            return false;
        }
        allumee = true;
        return true;
    }
    boolean eteint() {
        if (!allumee) {
            return false;
        }
        allumee = false;
        return true;
    }
    boolean demarreClignotement() {
        if (clignotante) {
            return false;
        }
        clignotante = true;
        return true;
    }
    boolean arreteClignotement() {
        if (!clignotante) {
            return false;
        }
        clignotante = false;
        return true;
    }

    // Collision en méthode d'instance
    public double calculeDistance(Autotamponneuses autreAuto) {
        if (autreAuto==null) {
            return -1.0;
        }
        return Math.sqrt(Math.pow(this.x-autreAuto.x, 2) + Math.pow(this.y-autreAuto.y, 2)); // Ajout du this. par soucis de clareté
    }
    public boolean collision(Autotamponneuses autreAuto) {
        if (autreAuto==null) {
            return false;
        }
        return this.calculeDistance(autreAuto) < DISTANCE_MINIMAL;
    }

    // Collision en méthode de classe
    public static double calculeDistance(Autotamponneuses auto1, Autotamponneuses auto2) {
        if (auto1 == null || auto2 == null) {
            return Double.NaN;
        }
        return Math.sqrt(Math.pow(auto1.x-auto2.x, 2) + Math.pow(auto1.y-auto2.y, 2));
    }
    public static boolean collision(Autotamponneuses auto1, Autotamponneuses auto2) {
        if (auto1 == null || auto2 == null) {
            return false;
        }
        return calculeDistance(auto1, auto2) < DISTANCE_MINIMAL;
    }

    @Override public boolean equals(Object autreObjet) {
        if (this == autreObjet) return true;
        if (autreObjet == null || getClass() != autreObjet.getClass()) return false;
        Autotamponneuses autreAuto = (Autotamponneuses) autreObjet; // On transtype vers Autotamponneuses
        return this.id == autreAuto.id; // On considerera egale toute autotamponneuses ayant le même ID
    }

    public static void main(String[] args) {
        // TEST CONSTRUCTEURS
        Autotamponneuses constructeur1 = new Autotamponneuses();
        System.out.println("TEST CONSTRUCTEUR VIDE : " + (constructeur1.x == -1.0f && constructeur1.y == -1.0f ? "OK" : "FAIL"));
        Autotamponneuses constructeur2 = new Autotamponneuses(5.0f,6.2f);
        System.out.println("TEST CONSTRUCTEUR : " + (constructeur2.x == 5.0f && constructeur2.y == 6.2f ? "OK" : "FAIL"));

        // TEST GETTERS
        System.out.println("TEST estOccupee : " + (constructeur1.estOccupee() ? "FAIL" : "OK"));
        System.out.println("TEST getNomOccupant : " + (Objects.equals(constructeur1.getNomOccupant(),"") ? "OK" : "FAIL"));
        System.out.println("TEST estAllumee : " + (constructeur1.estAllumee() ? "FAIL" : "OK"));
        System.out.println("TEST estClignotante : " + (constructeur1.estClignotante() ? "FAIL" : "OK"));

        // TEST ID
        System.out.println("TEST id : " + (constructeur1.id == last_id+1 ? "OK" : "FAIL"));

        // TEST toString
        //System.out.println(constructeur1.toString()); // Test manuel
        System.out.println("TEST toString : " + (Objects.equals(constructeur1.toString(),"[1] (-1.0,-1.0) libre éteinte non clignotante") ? "OK" : "FAIL"));

        // TEST METHODES 2
        System.out.println("TEST place 1 : " + (constructeur1.place(-1.0f, -1.0f) ? "FAIL" : "OK"));
        System.out.println("TEST place 2 : " + (constructeur1.place(2.0f, 6.0f) ? "OK" : "FAIL"));
        System.out.println("TEST ajouteOccupant 1 : " + (constructeur1.ajouteOccupant("Etienne") ? "OK" : "FAIL"));
        System.out.println("TEST ajouteOccupant 2 : " + (constructeur1.ajouteOccupant("Etienne") ? "FAIL" : "OK"));
        System.out.println("TEST allume 1 : " + (constructeur1.allume() ? "OK" : "FAIL"));
        System.out.println("TEST allume 2 : " + (constructeur1.allume() ? "FAIL" : "OK"));
        System.out.println("TEST eteint 1 : " + (constructeur1.eteint() ? "OK" : "FAIL"));
        System.out.println("TEST eteint 2 : " + (constructeur1.eteint() ? "FAIL" : "OK"));
        System.out.println("TEST demarreClignotement 1 : " + (constructeur1.demarreClignotement() ? "OK" : "FAIL"));
        System.out.println("TEST demarreClignotement 2 : " + (constructeur1.demarreClignotement() ? "FAIL" : "OK"));
        System.out.println("TEST arreteClignotement 1 : " + (constructeur1.arreteClignotement() ? "OK" : "FAIL"));
        System.out.println("TEST arreteClignotement 2 : " + (constructeur1.arreteClignotement() ? "FAIL" : "OK"));
    }
}