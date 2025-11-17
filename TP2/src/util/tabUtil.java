package util;

import java.util.Arrays;

public class tabUtil {
    public static int[] stringToInt (String[] tabString) {
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
    public static void printTab(int[] tab) {
        java.lang.StringBuilder p = new StringBuilder();
        p.append("[");
        for (int i=0; i<tab.length; i++) {
            p.append(tab[i]);
            if (i!=tab.length-1) p.append(",");
        }
        p.append("]");
        System.out.println(p);
    }

    public static int max(int[] tab) {
        if (tab.length==0) return Integer.MIN_VALUE; // Si tableau vide, renvoie valeur minimum d'un entier, pour ne pas fausser les comparaisons
        int max = tab[0];
        for (int i=0; i<tab.length; i++) {
            if (tab[i]>max) max = tab[i];
        }
        return max;
    }

    public static int min(int[] tab) {
        if (tab.length==0) return Integer.MAX_VALUE; // Si tableau vide, renvoie valeur maximum d'un entier, pour ne pas fausser les comparaisons
        int min = tab[0];
        for (int i=0; i<tab.length; i++) {
            if (tab[i]<min) min = tab[i];
        }
        return min;
    }

    public static void minToMax(int[] tabMin, int[] tabMax) {
        for (int i=0; i<tabMin.length; i++) {
            if (tabMin[i]==min(tabMin)) tabMin[i]=max(tabMax);
        }
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
    }
}
