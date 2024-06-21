import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("\""+employeeToRemove.getName()+"\" deleted successfully !");
        }else{
            System.out.println("failed to delete employee !");
        }
    }

    public void displayEmployees() {
        System.out.println();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        FullTimeEmployee fullTimeEmployee1 = new FullTimeEmployee("John Doe", 1, 5000.0);
        FullTimeEmployee fullTimeEmployee2 = new FullTimeEmployee("Asmae Aouassar", 2, 8800.0);
        FullTimeEmployee fullTimeEmployee3 = new FullTimeEmployee("Ikram Tiziazine", 3, 7500.0);

        PartTimeEmployee partTimeEmployee1 = new PartTimeEmployee("Jane Smith", 101, 30, 15.0);
        PartTimeEmployee partTimeEmployee2 = new PartTimeEmployee("Houssin Aoussar", 102, 15, 12.0);
        PartTimeEmployee partTimeEmployee3 = new PartTimeEmployee("James Won", 103, 40, 16.0);

        payrollSystem.addEmployee(fullTimeEmployee1);
        payrollSystem.addEmployee(fullTimeEmployee2);
        payrollSystem.addEmployee(fullTimeEmployee3);
        payrollSystem.addEmployee(partTimeEmployee1);
        payrollSystem.addEmployee(partTimeEmployee2);
        payrollSystem.addEmployee(partTimeEmployee3);

        System.out.print("\n--------- Initial Employee Details ---------");
        payrollSystem.displayEmployees();
        System.out.println();

        menu();

        Scanner scanner=new Scanner(System.in);
        System.out.print("enter your choice :  ");
        int choice=scanner.nextInt();
        switch (choice){

            case 1 : payrollSystem.displayEmployees();
                    break;

            case 2 :{
                System.out.print("enter the id of employee you want to delete : ");
                int id=scanner.nextInt();
                payrollSystem.removeEmployee(id);
                break;
            }
            default:
                System.out.println("oops! invalid choice !");
                break;
        }

        payrollSystem.displayEmployees();
   /*     System.out.println("\nRemoving Employee...");
        payrollSystem.removeEmployee(101);

        System.out.println("\nRemaining Employee Details:");
        payrollSystem.displayEmployees();*/
    }
    static void menu(){
        System.out.println("|1| : View current employees");
        System.out.println("|2| : delete an employee");
    }
}
