abstract class Fig {
    double d1, d2;

    Fig(double a, double b) {
        this.d1 = a;
        this.d2 = b;
    }

    abstract double area();
    // ei method na likhle error dekhabe
    void print() {
        System.out.println(d1 + " " + d2);
    }
}

public class FindAreas3 {
    public static void main(String[] args) {
        Fig rec = new Fig(10, 20) {     // Anonymous class
            double area() {
                return d1 * d2;
            }
            // abstract method chara onno method likhte pari
            // abstract method chara onno method gula overriding korte pari
            // kintu new method likhte pari na karon ei class ta anonymous
            @Override
            void print() {
                System.out.println("Rectangle with d1: " + d1 + " and d2: " + d2);
            }
        };
        System.out.println(rec.area());
        rec.print(); // Calling the non-abstract method
        Fig tr = new Fig(10, 20) {     // Anonymous class
            double area() {
                return d1 * d2 / 2;
            }
        };
        System.out.println(tr.area());
    }
}
