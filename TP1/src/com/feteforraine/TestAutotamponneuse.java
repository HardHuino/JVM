package com.feteforraine;

public class TestAutotamponneuse {
    public static void main(String[] args) {
        // TEST CONSTRUCTEURS
        Autotamponneuses constructeur1 = new Autotamponneuses();
        System.out.println("TEST CONSTRUCTEUR VIDE : " + (constructeur1.x == -1.0f && constructeur1.y == -1.0f ? "OK" : "FAIL"));
        Autotamponneuses constructeur2 = new Autotamponneuses(5.0f,6.2f);
        System.out.println("TEST CONSTRUCTEUR : " + (constructeur2.x == 5.0f && constructeur2.y == 6.2f ? "OK" : "FAIL"));

        // TEST METHODES
        System.out.println("TEST estOccupee : "+ (constructeur1.estOccupee() ? "FAIL" : "OK"));
        System.out.println("TEST getNomOccupant : "+ (constructeur1.getNomOccupant()=="" ? "OK" : "FAIL"));
        System.out.println("TEST estAllumee : "+ (constructeur1.estAllumee() ? "FAIL" : "OK"));
        System.out.println("TEST estClignotante : "+ (constructeur1.estClignotante() ? "FAIL" : "OK"));
    }
}
