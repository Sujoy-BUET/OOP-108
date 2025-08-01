class Printer{
    void print(String name, int pages){
        System.out.println(name+"'s Printing started...");
        for(int i = 1; i <= pages; i++){
            System.out.println(name + " is printing page " + i);
        }
        System.out.println(name+"'s Printing ended");
    }
}

class Person implements Runnable{
    Printer printer;
    String name;
    int pages;
    Person(Printer printer,String name,int pages){
        this.printer = printer;
        this.name = name;
        this.pages = pages;
        new Thread(this,name).start();
    }
    @Override
    public void run(){
        synchronized (printer) {
            printer.print(name, pages);
        }
    }
}

public class SynchronizationTest {
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Person(printer, "Sujoy", 5);
        new Person(printer, "Beston", 10);
        new Person(printer, "Pritom", 8);
    }
}
