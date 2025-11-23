package test;

import util.tabUtil;

import java.util.Arrays;

public class testUtil {
    public static void main(String[] args) {
        // TEST stringToInt
        System.out.println("TEST stringToInt STRING VALIDE : " + (Arrays.equals(tabUtil.stringToInt(new String[]{"34", "4", "50342"}), new int[]{34, 4, 50342}) ? "OK" : "FAIL"));
        System.out.println("TEST stringToInt STRING NON VALIDE : " + (Arrays.equals(tabUtil.stringToInt(new String[]{"34", "e", "50342"}), new int[]{34, 0, 50342}) ? "OK" : "FAIL"));
        tabUtil.printTab(tabUtil.stringToInt(args));


        // TEST printTab
        tabUtil.printTab(new int[]{2,4,3905});

        // TEST max
        System.out.println("TEST max : " + (tabUtil.max(new int[]{34, 4, 50342}) == 50342 ? "OK" : "FAIL"));
        System.out.println("TEST max TABLEAU VIDE : " + (tabUtil.max(new int[]{}) == Integer.MIN_VALUE ? "OK" : "FAIL"));

        // TEST min
        System.out.println("TEST min : " + (tabUtil.min(new int[]{34, 4, 50342}) == 4 ? "OK" : "FAIL"));
        System.out.println("TEST min TABLEAU VIDE : " + (tabUtil.min(new int[]{}) == Integer.MAX_VALUE ? "OK" : "FAIL"));

        // TEST minToMax
        int[] tabMin = new int[]{34, 4, 50342};
        int[] tabMax = new int[]{459, 35, 143};
        tabUtil.minToMax(tabMin,tabMax);
        System.out.println("TEST minToMax : " + (Arrays.equals(tabMin, new int[]{34, 459, 50342}) ? "OK" : "FAIL"));

        // TEST sum
        System.out.println("TEST sumItems : " + (tabUtil.sumItems(4,3,3,95,39481)==4+3+3+95+39481 ? "OK" : "FAIL"));

    }
}
