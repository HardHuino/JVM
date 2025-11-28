package logic;

public class testGates {
    public static void printTruthTable(gate gate) throws ExceptionPorteAChanger {
        System.out.println("Table de vérité : ");
        System.out.println("A | B | Q ");
        System.out.println("-----------");

        // Toutes les combinaisons possibles des entrées A et B
        for (int A = 0; A <= 1; A++) {
            for (int B = 0; B <= 1; B++) {


                gate.setA(A == 1);
                gate.setB(B == 1);

                System.out.println(A + " | " + B + " | " + gate.getQ());
            }
        }
    }

    public static void main(String[] args) throws ExceptionPorteAChanger {

        gate nand = new nand();
        gate nor  = new nor();

        printTruthTable(nand);
        printTruthTable(nor);
    }
}
