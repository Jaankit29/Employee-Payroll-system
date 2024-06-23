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
    public void setName(String name){
        this.name=name;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();
    public abstract void modifyEmp(Scanner scanner,Employee oldEmployee);
    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    double getMonthlySalary(){
        return monthlySalary;
    }
    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }
    public void setMonthlySalary(double monthlySalary){
        this.monthlySalary=monthlySalary;
    }
    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
    @Override
    public void modifyEmp(Scanner scanner,Employee oldEmployee) {
        if(oldEmployee instanceof  FullTimeEmployee){
            //retain old employee information
            String oldName=oldEmployee.getName();
            int oldId=oldEmployee.getId();
            double oldMonthlySalary=((FullTimeEmployee) oldEmployee).getMonthlySalary();
            System.out.println("modifying FullTime Employee ...");
            FullTimeEmployee fullTimeEmployee=(FullTimeEmployee) oldEmployee;
            System.out.print("enter new name: ");
            scanner.nextLine();
            String name=scanner.nextLine();
            System.out.print("enter montlyh salary: ");
            double monthlySalary=scanner.nextDouble();
            fullTimeEmployee.setName(name);
            fullTimeEmployee.setMonthlySalary(monthlySalary);
            System.out.println("modify employee successfully ");
            System.out.println("from");
            System.out.println(new FullTimeEmployee(oldName,oldId,oldMonthlySalary));
            System.out.println("to");
            System.out.println(fullTimeEmployee);
        }else{
            System.out.println("invalid type !");
        }

    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public int getHoursWorked(){
        return hoursWorked;
    }
    public double getHourlyRate(){
        return hourlyRate;
    }
    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    public void setHoursWorked(int hoursWorked){
        this.hoursWorked=hoursWorked;
    }
    public void setHourlyRate(double hourlyRate){
        this.hourlyRate=hourlyRate;
    }
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
    public void modifyEmp(Scanner scanner,Employee oldEmployee){
        //retain old employee information
        String oldName=oldEmployee.getName();
        int oldId=oldEmployee.getId();
        int oldHoursWorked=((PartTimeEmployee)oldEmployee).getHoursWorked();
        double oldHourlyRate=((PartTimeEmployee)oldEmployee).getHourlyRate();
        if(oldEmployee instanceof PartTimeEmployee){
           System.out.println("modifying PartTime employee ...");
           PartTimeEmployee partTimeEmployee=(PartTimeEmployee) oldEmployee;
           System.out.print("enter new name of employee: ");
           scanner.nextLine();
           String name=scanner.nextLine();
           System.out.print("enter number of hours Worked: ");
           int hoursWorked=scanner.nextInt();
           System.out.print("enter hourly rate: ");
           double hourlyRate=scanner.nextDouble();
           partTimeEmployee.setName(name);
           partTimeEmployee.setHoursWorked(hoursWorked);
           partTimeEmployee.setHourlyRate(hourlyRate);
           System.out.println("modify employee successfully");
           System.out.println("from");
           System.out.println(new PartTimeEmployee(oldName,oldId,oldHoursWorked,oldHourlyRate));
           System.out.println("to");
           System.out.println(partTimeEmployee);
       }else{
           System.out.println("invalid type !");
        }
}}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
    public void viewEmployee(int id){
        Employee employeeToDisplay=null;
        for(Employee employee:employeeList){
            if(employee.getId()==id){
                employeeToDisplay=employee;
                break;
            }
        }
        if(employeeToDisplay==null)
            System.out.println("failed to find employee");
        else System.out.println(employeeToDisplay);
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
        if(employeeList.size()==0){
            System.out.println("No employees");
        }
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        System.out.println();
    }
    public boolean isEmployeeExist(String name,int id){
        for(Employee emp:employeeList){
            if(emp.getId()==id && emp.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public void modifyEmployee(Scanner scanner,int id){
        Employee employeeToModify=null;
        for(Employee emp:employeeList){
            if(emp.getId()==id) employeeToModify=emp;
        }

        if(employeeToModify!=null){
            employeeToModify.modifyEmp(scanner,employeeToModify);
        }else{
            System.out.println("employee with id \""+id+"\" not found !");
        }
    }
    public boolean addEmployee(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("enter id of employee: ");
        int id=scanner.nextInt();
        for(Employee emp:employeeList){
            if(emp.getId()==id){
                System.out.println("id \""+id+"\" already exists");
                return false;
            }
        }
        int choice=0;
        while(choice!=1 && choice!=2){
            System.out.println("press (1) if employee is FullTime Employee");
            System.out.println("press (2) if employee is PartTime Employee");
            System.out.println("press (3) to cancel ");
            System.out.print("-> ");
            choice=scanner.nextInt();
            if(choice==3){
                System.out.println("cancel operation of adding new employee");
                return false;
            }
        }
        if(choice==1){
            System.out.println("adding a new FullTime Employee ...");
            System.out.print("enter a name : ");
            scanner.nextLine();
            String name=scanner.nextLine();
            System.out.print("enter month Salary: ");
            double monthlySalary=scanner.nextDouble();
            FullTimeEmployee fullTimeEmployee=new FullTimeEmployee(name,id,monthlySalary);
            boolean result=employeeList.add(fullTimeEmployee);
            return result;
        }else{
            System.out.println("adding a new PartTime Employee ...");
            System.out.print("enter a name : ");
            scanner.nextLine();
            String name=scanner.nextLine();
            System.out.print("enter month Salary: ");
            double monthlySalary=scanner.nextDouble();
            System.out.print("enter hours Worked : ");
            int hoursWorked=scanner.nextInt();
            System.out.print("enter hourly Rate: ");
            double hourlyRate=scanner.nextDouble();
            PartTimeEmployee partTimeEmployee=new PartTimeEmployee(name,id,hoursWorked,hourlyRate);
            boolean result=employeeList.add(partTimeEmployee);
            return result;
        }
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


       while(true){
           menu();

           Scanner scanner=new Scanner(System.in);
           System.out.print("enter your choice :  ");
           int choice=scanner.nextInt();

           switch (choice){

               case 1 : payrollSystem.displayEmployees();
                   break;
               case 2:
               {
                   System.out.print("enter the id of the employee: ");
                   int id=scanner.nextInt();
                   payrollSystem.viewEmployee(id);
                   break;
               }
               case 3 :{
                   System.out.print("enter the id of employee you want to delete : ");
                   int id=scanner.nextInt();
                   payrollSystem.removeEmployee(id);
                   break;
               }
               case 4 :{
                   System.out.print("enter the id of the employee: ");
                   int id=scanner.nextInt();
                   scanner.nextLine();
                   System.out.print("enter the name of the employee: ");
                   String name=scanner.nextLine();
                   boolean exist=payrollSystem.isEmployeeExist(name,id);
                   if(exist){
                       System.out.println("the employee exist");
                       payrollSystem.viewEmployee(id);
                   }else{
                       System.out.println("this employee doesn't exist !");
                   }
                   break;
               }
               case 5 :{
                   System.out.print("enter id of employee to modify: ");
                   int id=scanner.nextInt();
                   payrollSystem.modifyEmployee(scanner,id);
                   break;
               }
               case 6 :{
                   boolean result=payrollSystem.addEmployee();
                   if(result) System.out.println("employee added successfully !");
                   else System.out.println("failed to add employee");
                   break;
               }
               case 7 :{
                   System.out.println("THANK YOU FOR USING EMPLOYEE PAYROLL SYSTEM !");
                   return;
               }
               default:
                   System.out.println("oops! invalid choice !");
                   break;
           }

       }
        //payrollSystem.displayEmployees();
   /*     System.out.println("\nRemoving Employee...");
        payrollSystem.removeEmployee(101);

        System.out.println("\nRemaining Employee Details:");
        payrollSystem.displayEmployees();*/
    }
    static void menu(){
        System.out.println("|1| : View current employees");
        System.out.println("|2| : View an employee");
        System.out.println("|3| : delete an employee");
        System.out.println("|4| : is employee exists?");
        System.out.println("|5| : modify employee");
        System.out.println("|6| : add employee");
        System.out.println("|7| : remove multiple employees");
        System.out.println("|8| : exit");
    }
}

