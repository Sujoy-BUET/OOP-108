class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String toString(){
        return name + " is a " + getClass().getName()+" , aged "+age;
    }
}

interface Venomous {
    boolean isLethalToAdultHumans();
}

class Bird extends Animal{
    public Bird(String name,int age){
        super(name,age);
    }
}

class Mammal extends Animal{
    private String bloodtype;
    public Mammal(String name,int age,String bloodtype){
        super(name,age);
        this.bloodtype = bloodtype;
    }

    void printBloodType(){
        System.out.print(", "+ bloodtype+"!");
    };
}

class Reptile extends Animal{
    public Reptile(String name,int age){
        super(name,age);
    }
}

class Fish extends Animal{
    public Fish(String name,int age){
        super(name,age);
    }
}

class Arachnid extends Animal{
    public Arachnid(String name,int age){
        super(name,age);
    }
}

class Albatross extends Bird{
    public Albatross(String name,int age){
        super(name,age);
    }
}

class Dog extends Mammal{
    public Dog(String name,int age){
        super(name,age,"Warm-Blooded");
    }
}

class FruitBat extends Mammal{
    public FruitBat(String name,int age){
        super(name,age,"Warm-Blooded");
    }
}

class EasternBrownSnake extends Reptile implements Venomous{
    public EasternBrownSnake(String name,int age){
        super(name,age);
    }
    public boolean isLethalToAdultHumans(){
        return true;
    }
}

class GreatWhiteShark extends Fish{
    public GreatWhiteShark(String name,int age){
        super(name,age);
    }
}

class Platypus extends Mammal implements Venomous{
    public Platypus(String name,int age){
        super(name,age,"Warm-Blooded");
    }
    public boolean isLethalToAdultHumans(){
        return false;
    }
}

class RedBackSpider extends Arachnid implements Venomous{
    public RedBackSpider(String name,int age){
        super(name,age);
    }
    public boolean isLethalToAdultHumans(){
        return false;
    }
}

class Human extends Mammal{
    public Human(String name,int age){
        super(name,age,"Warm-Blooded");
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Bird alex = new Albatross("Alex", 39);
        Mammal spot = new Dog("Spot", 7);
        Mammal fred = new FruitBat("Fred", 10);
        Reptile steph = new EasternBrownSnake("Steph", 12);
        Fish bruce = new GreatWhiteShark("Bruce", 40);
        Arachnid charlotte = new RedBackSpider("Charlotte", 1);
        Mammal perry = new Platypus("Perry", 5);
        Mammal bob = new Human("Bob", 20);

        Animal [] animals = {alex, spot, fred, steph, bruce, charlotte, perry, bob};
        for (Animal a: animals) {
            System.out.print(a);
            if (a instanceof Mammal) {
                ((Mammal) a).printBloodType();
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("The following animals are venomous: ");
        for (Animal a: animals) {
            if (a instanceof Venomous) {
                boolean deadly = ((Venomous) a).isLethalToAdultHumans();
                System.out.println(a + (deadly? ", and it's lethal!" : ", and fortunately non-lethal"));
            }
        }
    }
}

/* main output
Alex is a Albatross, aged 39
Spot is a Dog, aged 7, Warm-Blooded!
Fred is a FruitBat, aged 10, Warm-Blooded!
Steph is a EasternBrownSnake, aged 12
Bruce is a GreatWhiteShark, aged 40
Charlotte is a RedBackSpider, aged 1
Perry is a Platypus, aged 5, Warm-Blooded!
Bob is a Human, aged 20, Warm-Blooded!

The following animals are venomous: 
Steph is a EasternBrownSnake, aged 12, and it's lethal!
Charlotte is a RedBackSpider, aged 1, and fortunately non-lethal
Perry is a Platypus, aged 5, and fortunately non-lethal
*/