import java.util.Random;

class Account {
    private int number;
    private String customer;
    private double balance;
    // you are not allowed to add any more class variables here

    // you are not allowed to write any other constructor
    public Account(int number, String customer, double balance) {
        this.number = number;
        this.customer = customer;
        this.balance = balance;
    }

    // add your code here
    public double get_balance(){
        return balance;
    }
    public String getCustomer(){
        return customer;
    }
    public void add_balance(double b){
        balance += b;
    }
    public void printAccount(){
        System.out.println("Account number: " + number + ", Customer Name: "+ customer+", Balance: " + balance);
    }
}


class Branch {

    private int id;
    private String name;
    private Account []  accounts;
    private int accountCount;
    // add your code here

    private static Branch[] allBranches = new Branch[20];
    private static int branchCount = 0;// static variables are not property of the object, they are accessible without creating an object

    // there can be at most 20 branches

    // you are not allowed to write any other constructor

    public Branch(int id, String name) {
        this.id = id;
        this.name = name;
        this.accounts = new Account[10];
        // add your code here
        allBranches[branchCount++] = this;

    }
    public void addAccount(Account a) {
        accounts[accountCount++] = a;
    }

    // add your code here
    public double getBranchBalance(){
        double total_balance = 0;
        for(int i=0;i<accountCount;i++){
            total_balance += accounts[i].get_balance();
        }
        return total_balance;
    }
    public Account getMinBalanceAccount(){
        Account r = accounts[0];
        for(int i=1;i<accountCount;i++){
            if(accounts[i].get_balance()<r.get_balance()){
                r = accounts[i];
            }
        }
        return r;
    }
    public static void transferBalance(Account a,Account b,double bal){
        a.add_balance(bal);
        b.add_balance(-1*bal);
    }
    public static void printAllBranchesInfo(){
        for(int i=0;i<branchCount;i++){
            System.out.println("Branch " + i+1);
            for(int j=0;j<allBranches[i].accountCount;j++){
                allBranches[i].accounts[j].printAccount();
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Account []a = new Account[6];
        Random r = new Random();
        for (int i = 0; i < 6; i++)
        {
            a[i] = new Account(i+1, "ACC" + (i+1), 10000 * r.nextDouble());
        }
        Branch []b = new Branch[2];
        for (int i = 0; i < 2; i++) {
            b[i] = new Branch(i+1, "BR" + (i+1));
        }
        b[0].addAccount(a[0]); // add the Account to this Branch
        b[0].addAccount(a[1]); // add the Account to this Branch
        b[1].addAccount(a[2]); // add the Account to this Branch
        b[1].addAccount(a[3]); // add the Account to this Branch
        b[1].addAccount(a[4]); // add the Account to this Branch
        b[1].addAccount(a[5]); // add the Account to this Branch
        System.out.println(b[0].getBranchBalance()); // prints the total Balance of this Branch
        System.out.println(b[1].getBranchBalance()); // prints the total Balance of this Branch
        System.out.println(b[0].getMinBalanceAccount().getCustomer()); // prints the name of the Account with lowest Balance in this Branch
        System.out.println(b[1].getMinBalanceAccount().getCustomer()); // prints the name of the Account with lowest Balance in this Branch
        Branch.transferBalance(a[4], a[3], 100); // transfer 100 from balance of Account a[4] to Account a[3] 
        Branch.printAllBranchesInfo(); // prints all branches info as follows (this is just a sample, you can't hardcode this output)
        /*Branch Id: 1, Branch Name: BR1
        Account Number: 1, Customer Name: ACC1, Balance: actual balance
        Account Number: 2, Customer Name: ACC2, Balance: actual balance
        Branch Id: 2, Branch Name: BR2
        Account Number: 3, Customer Name: ACC3, Balance: actual balance
        Account Number: 4, Customer Name: ACC4, Balance: actual balance
        Account Number: 5, Customer Name: ACC5, Balance: actual balance
        Account Number: 6, Customer Name: ACC6, Balance: actual balance*/
    }
}