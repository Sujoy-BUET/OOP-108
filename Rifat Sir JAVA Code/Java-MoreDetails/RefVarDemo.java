class MyClass{
    private int x;
    MyClass() {
        this.x = 0;
    }

    void setX(int x) {
        this.x = x;
    }

    int getX() {
        return x;
    }
}

public class RefVarDemo {
    public static void main(String[] args) {
        var mc = new MyClass();
        System.out.println(mc.getX());
        mc.setX(10);
        System.out.println(mc.getX());
        var str = "Hello World";
        System.out.println(str);
        //var[] mc2 = new MyClass[5];  Error: cannot use var for array type
        // var mc3[] = new MyClass[5];  Error: cannot use var for array type
        var mc4 = new MyClass[5]; // This is valid, but not recommended
        mc4[0] = new MyClass();
        mc4[0].setX(20);
        System.out.println(mc4[0].getX());
        //var X ; // Error: cannot use var without initialization
        var X = 5; // Valid usage of var with initialization
        System.out.println(X);
        
    }   
}
