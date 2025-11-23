package util;

import java.util.Arrays;

public class tabUtil {
    public static int[] stringToInt (String[] tabString) { // Transtype un tableau de String en tableau d'entier
        int[] tabInt = new int[tabString.length];
        for (int i=0; i<tabString.length; i++) {
            try {
                tabInt[i] = Integer.parseInt(tabString[i]);
            } catch (NumberFormatException e) {
                System.out.println("Chaine de charactère inconvertible trouvée, changée en 0");
                tabInt[i] = 0;
            }
        }
        return tabInt;
    }
    public static void printTab(int[] tab) { // Affiche un tableau
        java.lang.StringBuilder p = new StringBuilder();
        p.append("[");
        for (int i=0; i<tab.length; i++) {
            p.append(tab[i]);
            if (i!=tab.length-1) p.append(",");
        }
        p.append("]");
        System.out.println(p);
    }

    public static int max(int[] tab) { // Retourne la valeur maximum d'un tableau
        if (tab.length==0) return Integer.MIN_VALUE; // Si tableau vide, renvoie valeur minimum d'un entier, pour ne pas fausser les comparaisons
        int max = tab[0];
        for (int current : tab) {
            if (current > max) max = current;
        }
        return max;
    }

    public static int min(int[] tab) { // Retourne la valeur minimum d'un tableau
        if (tab.length==0) return Integer.MAX_VALUE; // Si tableau vide, renvoie valeur maximum d'un entier, pour ne pas fausser les comparaisons
        int min = tab[0];
        for (int current : tab) {
            if (current < min) min = current;
        }
        return min;
    }

    public static void minToMax(int[] tabMin, int[] tabMax) { // Echange min de tabMin avec max de tabMax
        for (int i=0; i<tabMin.length; i++) {
            if (tabMin[i]==min(tabMin)) tabMin[i]=max(tabMax); // Complexité médiocre
        }
    }

    public static int sumItems(int... items) { // Retourne la somme de tout les elements passées en paramètre
        int sum = 0;
        for (int item : items) {
            sum += item;
        }
        return sum;
    }


    public static void main(String[] args) {
        // TEST stringToInt
        System.out.println("TEST stringToInt STRING VALIDE : " + (Arrays.equals(stringToInt(new String[]{"34", "4", "50342"}), new int[]{34, 4, 50342}) ? "OK" : "FAIL"));
        System.out.println("TEST stringToInt STRING NON VALIDE : " + (Arrays.equals(stringToInt(new String[]{"34", "e", "50342"}), new int[]{34, 0, 50342}) ? "OK" : "FAIL"));
        printTab(stringToInt(args));


        // TEST printTab
        printTab(new int[]{2,4,3905});

        // TEST max
        System.out.println("TEST max : " + (max(new int[]{34, 4, 50342}) == 50342 ? "OK" : "FAIL"));
        System.out.println("TEST max TABLEAU VIDE : " + (max(new int[]{}) == Integer.MIN_VALUE ? "OK" : "FAIL"));

        // TEST min
        System.out.println("TEST min : " + (min(new int[]{34, 4, 50342}) == 4 ? "OK" : "FAIL"));
        System.out.println("TEST min TABLEAU VIDE : " + (min(new int[]{}) == Integer.MAX_VALUE ? "OK" : "FAIL"));

        // TEST minToMax
        int[] tabMin = new int[]{34, 4, 50342};
        int[] tabMax = new int[]{459, 35, 143};
        minToMax(tabMin,tabMax);
        System.out.println("TEST minToMax : " + (Arrays.equals(tabMin, new int[]{34, 459, 50342}) ? "OK" : "FAIL"));

        // TEST sum
        System.out.println("TEST sumItems : " + (sumItems(4,3,3,95,39481)==4+3+3+95+39481 ? "OK" : "FAIL"));

    }
}
