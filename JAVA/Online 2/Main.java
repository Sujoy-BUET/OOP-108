
import java.util.Scanner;

class Employee{
    private String name;
    private int id;
    public int days_present;
    public Employee(String name,int id,int prensent){
        this.name = name;
        this.id = id;
        this.days_present = prensent;
    }
    public void set_days_present(){
        days_present++;
    }
    public void decrease_days_present(){
        days_present--;
    }
    public int get_id(){
        return id;
    }
    public void print_employee(){
        System.out.println("Name : " + name);
        System.out.println("ID: "+ id);
        System.out.println("Days Present: "+ days_present);
    }
}

class Department{
    public int Id;
    public Employee[] employees;
    public Department(int id,int total_emp){
        this.Id = id;
        employees = new Employee[total_emp];
    }

    public Department() {
    }
    public int find_employee_index(int id){
        for(int i=0;i<employees.length;i++){
            if(employees[i].get_id()==id) return i;
        }
        return -1;
    }
    public void print(){
        for(int i=0;i<employees.length;i++){
            employees[i].print_employee();
            System.out.println("------------------------------");
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total_department = Integer.parseInt(args[0]);
        Department[] departments = new Department[total_department];
        for (int idx = 1; idx <= total_department; idx++) {
            System.out.print("How many employee in department "+idx+" : ");
            int emp = sc.nextInt();
            departments[idx-1] = new Department(idx,emp);
            for(int i=0;i<emp;i++){
                System.out.println("Give details of employee "+(i+1));
                String name;
                int id,days_present;
                System.out.print("Id: ");
                id = sc.nextInt();
                System.out.print("Name: ");
                name = sc.next();
                System.out.print("Days Present: ");
                days_present = sc.nextInt();
                departments[idx-1].employees[i] = new Employee(name, id, days_present);

            }
        }
        
        while(true){
            System.out.println("1)Increase attendence: \n2)Decrease attendance\n3)Show details of department\n4)Exit\n");
            int m = sc.nextInt();
            if(m==4){ 
                break;
            }else if(m==1){
                System.out.print("Give department Id and employee id: ");
                int d = sc.nextInt();
                int id = sc.nextInt();
                int i = departments[d-1].find_employee_index(id);
                departments[d-1].employees[i].set_days_present();
                System.out.println("Department "+ d + " employee "+ id + " attendance increased by 1");

            }else if(m==2){
                System.out.print("Give department Id and employee id: ");
                int d = sc.nextInt();
                int id = sc.nextInt();
                int i = departments[d-1].find_employee_index(id);
                if(departments[d-1].employees[i].days_present>=1){
                    departments[d-1].employees[i].decrease_days_present();
                    System.out.println("Department "+ d + " employee "+ id + " attendance increased by 1");
                }else{
                    System.out.println("Attendance is already 0");
                }
            }else if(m==3){
                for(int i=0;i<total_department;i++){
                    System.out.println("================================\nEmployees of department "+ i+1);
                    departments[i].print();
                    System.out.println("==================================");
                }
            }
        }
    }
}