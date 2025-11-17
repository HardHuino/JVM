package com.feteforraine;

import java.util.Objects;

public class TestAutotamponneuse {

    public static void testConstructeurs() {
        Autotamponneuses a1 = new Autotamponneuses();
        System.out.println("TEST CONSTRUCTEUR VIDE : " + (a1.x == -1.0f && a1.y == -1.0f ? "OK" : "FAIL"));
        Autotamponneuses a2 = new Autotamponneuses(5.0f,6.2f);
        System.out.println("TEST CONSTRUCTEUR : " + (a2.x == 5.0f && a2.y == 6.2f ? "OK" : "FAIL"));
    }

    public static void testGetter() {
        // Tests sur une autotamponeuse dont l'on connait les valeurs des attributs
        Autotamponneuses a = new Autotamponneuses();
        System.out.println("TEST estOccupee : " + (a.estOccupee() ? "FAIL" : "OK"));
        System.out.println("TEST getNomOccupant : " + (Objects.equals(a.getNomOccupant(), "") ? "OK" : "FAIL"));
        System.out.println("TEST estAllumee : " + (a.estAllumee() ? "FAIL" : "OK"));
        System.out.println("TEST estClignotante : " + (a.estClignotante() ? "FAIL" : "OK"));
    }

    public static void testSetter() {
        Autotamponneuses a = new Autotamponneuses();
        System.out.println("TEST place 1 : " + (a.place(-1.0f, -1.0f) ? "FAIL" : "OK"));
        System.out.println("TEST place 2 : " + (a.place(2.0f, 6.0f) ? "OK" : "FAIL"));
        System.out.println("TEST ajouteOccupant 1 : " + (a.ajouteOccupant("Etienne") ? "OK" : "FAIL"));
        System.out.println("TEST ajouteOccupant 2 : " + (a.ajouteOccupant("Etienne") ? "FAIL" : "OK"));
        System.out.println("TEST allume 1 : " + (a.allume() ? "OK" : "FAIL"));
        System.out.println("TEST allume 2 : " + (a.allume() ? "FAIL" : "OK"));
        System.out.println("TEST eteint 1 : " + (a.eteint() ? "OK" : "FAIL"));
        System.out.println("TEST eteint 2 : " + (a.eteint() ? "FAIL" : "OK"));
        System.out.println("TEST demarreClignotement 1 : " + (a.demarreClignotement() ? "OK" : "FAIL"));
        System.out.println("TEST demarreClignotement 2 : " + (a.demarreClignotement() ? "FAIL" : "OK"));
        System.out.println("TEST arreteClignotement 1 : " + (a.arreteClignotement() ? "OK" : "FAIL"));
        System.out.println("TEST arreteClignotement 2 : " + (a.arreteClignotement() ? "FAIL" : "OK"));
    }

    public static void testCollision() {
        Autotamponneuses a = new Autotamponneuses(4.0f,5.0f);
        Autotamponneuses b = new Autotamponneuses(4.0f,5.0f);
        // Tests méthode d'instance
        System.out.println("TEST collision Instance : " + (a.collision(b) ? "OK" : "FAIL"));
        // Tests méthode de classe
        System.out.println("TEST collision Class : " + (Autotamponneuses.collision(a,b) ? "OK" : "FAIL"));
    }

    public static void testEquals() {
        Autotamponneuses a = new Autotamponneuses();
        Autotamponneuses b = new Autotamponneuses();
        System.out.println("TEST equals (Instance) 1 : " + (a.equals(a) ? "OK" : "FAIL"));
        System.out.println("TEST equals (Instance) 2 : " + (a.equals(b) ? "FAIL" : "OK"));
    }

    public static void main(String[] args) {
        // TEST CONSTRUCTEURS
        testConstructeurs();

        // TEST GETTERS
        testGetter();

        // TEST SETTERS
        testSetter();

        // TEST COLLISION
        testCollision();

        // TEST EQUALS
        testEquals();
    }
}
