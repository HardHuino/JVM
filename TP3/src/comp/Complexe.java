package comp;

public class Complexe {
    private final double re;
    private final double im;

    // CONSTRUCTEUR
    public Complexe(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public Complexe(Complexe complex) {
        this (complex.re, complex.im);
    }

    // OVERRIDES
    @Override
    public String toString() {
        return this.re+"+"+this.im+"i";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Complexe autreComplexe = (Complexe) obj; // On transtype vers com.Complexe
        return (this.re==autreComplexe.re && this.im==autreComplexe.im);
    }

    // GETTERS
    public double getRe() { return this.re; }
    public double getIm() { return this.im; }

    public static double module(Complexe c) {
        return Math.sqrt(c.re*c.re+c.im*c.im);
    }
    public static double argumuent(Complexe c) {
        return Math.acos(c.re/module(c));
    }

    public Complexe addition(Complexe c) {
        return new Complexe(this.re+c.re, this.im+c.im);
    }
    public Complexe multiplication(Complexe c) {
        return new Complexe(this.re*c.re-this.im*c.im,this.re*c.im+this.im*c.re);
    }

    public static void main(String[] args) {
        // --- Test des constructeurs ---
        Complexe a = new Complexe(5.5, -6);
        Complexe b = new Complexe(5.5, -6);
        Complexe c = new Complexe(0, 6.8);
        Complexe d = new Complexe(a); // constructeur par copie

        System.out.println("a = " + a); // test toString
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d + " (copie de a)");

        // --- Test equals ---
        System.out.println("\n--- Test equals ---");
        System.out.println("a.equals(b) ? " + (a.equals(b) ? "OK" : "FAIL")); // true
        System.out.println("a.equals(c) ? " + (a.equals(c) ? "FAIL" : "OK")); // false
        System.out.println("a.equals(d) ? " + (a.equals(d) ? "OK" : "FAIL")); // true

        // --- Test addition ---
        System.out.println("\n--- Test addition ---");
        Complexe somme = a.addition(c);
        System.out.println(a + " + " + c + " = " + somme);

        // --- Test multiplication ---
        System.out.println("\n--- Test multiplication ---");
        Complexe produit = a.multiplication(c);
        System.out.println(a + " * " + c + " = " + produit);

        // --- Test module ---
        System.out.println("\n--- Test module ---");
        System.out.println("module(" + a + ") = " + Complexe.module(a));
        System.out.println("module(" + c + ") = " + Complexe.module(c));

        // --- Test argument ---
        System.out.println("\n--- Test argument ---");
        System.out.println("argument(" + a + ") = " + Complexe.argumuent(a));
        System.out.println("argument(" + c + ") = " + Complexe.argumuent(c));

    }
}
