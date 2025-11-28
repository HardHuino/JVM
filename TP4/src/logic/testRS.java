package logic;

public class testRS {
    public static void main(String[] args) {
        System.out.println("=== Test 1 : Porte logique avec cycles limités ===");
        try {
            nand porteTest = new nand(false, false, 5);
            for (int i = 0; i < 10; i++) {
                System.out.println("Cycle " + (i+1) + " : Q = " + porteTest.getQ());
            }
        } catch (ExceptionPorteAChanger e) {
            System.out.println("Exception attrapée : " + e.getMessage());
        }

        System.out.println("\n=== Test 2 : Bascule RS avec cycles limités ===");
        try {
            basculeRS bascule = new basculeRS(false, false, 10, 10);
            bascule.print();

            bascule.setS(true);
            bascule.print();

            bascule.setS(false);
            bascule.print();

            bascule.setR(true);
            bascule.print();

        } catch (ExceptionBasculeAReparer e) {
            System.out.println("Exception bascule : " + e.getMessage());
            System.out.println("Porte à réparer : " + e.getPorteDefectueuse());
        }

        System.out.println("\n=== Test 3 : État interdit S=R=1 ===");
        try {
            basculeRS basculeInvalide = new basculeRS(true, true);
        } catch (RuntimeException e) {
            System.out.println("Exception initialisation : " + e.getMessage());
        } catch (ExceptionBasculeAReparer e) {
            System.out.println("Exception bascule : " + e.getMessage());
        }
    }
}