package com.feteforraine;

public class Autotamponneuses {
    private final int id;
    private static int last_id=0;
    float x; // package-private pour ma classe de test
    float y; // package-private pour ma classe de test
    private boolean occupee;
    private String nomOccupant;
    private boolean allumee;
    private boolean clignotante;

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
        id = last_id+1;
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
        return "[" + id + "] (" + x + "," + y + ")" + (estOccupee() ? " occupée " + "(" + nomOccupant + ") " : " libre") + (estAllumee() ? " allumée" : " éteinte") + (estClignotante() ? " clignotante" : " non clignotante");
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

    public static void main(String[] args) {
        // TEST CONSTRUCTEURS
        Autotamponneuses constructeur1 = new Autotamponneuses();
        System.out.println("TEST CONSTRUCTEUR VIDE : " + (constructeur1.x == -1.0f && constructeur1.y == -1.0f ? "OK" : "FAIL"));
        Autotamponneuses constructeur2 = new Autotamponneuses(5.0f,6.2f);
        System.out.println("TEST CONSTRUCTEUR : " + (constructeur2.x == 5.0f && constructeur2.y == 6.2f ? "OK" : "FAIL"));

        // TEST METHODES 1
        System.out.println("TEST estOccupee : " + (constructeur1.estOccupee() ? "FAIL" : "OK"));
        System.out.println("TEST getNomOccupant : " + (constructeur1.getNomOccupant()=="" ? "OK" : "FAIL"));
        System.out.println("TEST estAllumee : " + (constructeur1.estAllumee() ? "FAIL" : "OK"));
        System.out.println("TEST estClignotante : " + (constructeur1.estClignotante() ? "FAIL" : "OK"));

        // TEST ID
        System.out.println("TEST id : " + (constructeur1.id == last_id+1 ? "OK" : "FAIL"));

        // TEST toString
        System.out.println(constructeur1.toString());
        //System.out.println("TEST toString : " + (constructeur1.toString() == "[1] (-1.0,-1.0) libre éteinte non clignotante" ? "OK" : "FAIL"));

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