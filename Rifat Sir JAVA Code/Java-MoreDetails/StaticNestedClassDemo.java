class OuterStaticInner {
    private int outer_x = 100;

    OuterStaticInner() {
        System.out.println("OuterStaticInner Constuctor");
    }

    void test() {
        Inner inner = new Inner();
        inner.display(this);
    }

    // this is a static nested class
    static class Inner {

        Inner() {
            System.out.println("Inner Constuctor");
        }

        void display(OuterStaticInner outer) {
            System.out.println(outer.outer_x);
        }
    }
}

public class StaticNestedClassDemo {
    public static void main(String[] args) {
        OuterStaticInner outer = new OuterStaticInner();
        outer.test();
        // Static Nested create korar somoy Outer class instance dorkar nai
        OuterStaticInner.Inner x = new OuterStaticInner.Inner();
        x.display(outer); // kintu method call korar somoy Outer class instance dorkar
        // x.display(new OuterStaticInner()); // ekhane new OuterStaticInner() diyeo
        // nested class instance create kora jabe, but eta not recommended  
        // tai etar used kora hoy na widely 
        //OuterStaticInner.Inner.display(outer); // ekhane new OuterStaticInner() diyeo
    }
}