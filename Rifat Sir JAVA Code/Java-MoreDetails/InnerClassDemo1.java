class Outer1 {
    private int outer_x = 100;

    Outer1() {
        System.out.println("Outer1 Constructor");
    }

    void test() {
        Inner inner = new Inner();
        inner.display(); // Outer class can't directly access inner members or methods
    }

    // this is an inner class
    class Inner {
        int inner_x = 200;

        Inner() {
            System.out.println("Inner Constructor");
        }

        void display() {
            System.out.println(outer_x); // Innter class can access outer class private members
        }
    }
}

public class InnerClassDemo1 {
    public static void main(String[] args) {
        Outer1 outer = new Outer1();
        outer.test();
        // To create an instance of the inner class, we need an instance of the outer class
        // So, outer.new Inner() is used to create an instance of Inner
        Outer1.Inner innerObj = outer.new Inner(); 
        innerObj.display();
    }
}
