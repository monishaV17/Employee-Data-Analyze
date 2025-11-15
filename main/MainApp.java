package main;
import java.sql.Connection;
import java.util.Scanner;
import dao.EmployeeDAO;
import model.Employee;
import DB.DBConnection;

public class MainApp {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        EmployeeDAO dao=new EmployeeDAO();
        try(Connection con=DBConnection.gConnection()){
            if(con==null){
                System.out.println("Database connection failed!");
                return;
            }
            System.out.println("Connected to database!");
            boolean running=true;
            while(running){
                System.out.println("\n===== Employee Management Menu =====");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice=sc.nextInt();
                sc.nextLine(); 

                switch(choice){
                    case 1:                                 //Add Employees
                        System.out.print("Enter ID: ");
                        int id=sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name=sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age=sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Department: ");
                        String dept=sc.nextLine();
                        System.out.print("Enter Designation: ");
                        String desig=sc.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary=sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Join Date (YYYY-MM-DD): ");
                        String joinDate=sc.nextLine();
                        model.Employee emp=new model.Employee(id,name,age,dept,desig,salary,joinDate);
                        dao.addEmployees(emp,con);
                        System.out.println("Employee added!");
                        break;
                    case 2:                                                                 //View Employees
                        java.util.List<model.Employee> employees=dao.viewEmployees(con);
                        System.out.println("\n--- Employee List ---");
                        for(model.Employee employee:employees) {
                            System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge() + ", Dept: " + employee.getDepartment() + ", Designation: " + employee.getDesignation() + ", Salary: " + employee.getSalary() + ", Join Date: " + employee.getJoinDate());
                        }
                        if(employees.isEmpty()){
                            System.out.println("No employees found.");
                        }
                        break;
                    case 3:                                                              // Update specific field for an employee
                        System.out.print("Enter ID of employee to update: ");
                        int upId=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Which field do you want to update?");
                        System.out.println("1. Name");
                        System.out.println("2. Age");
                        System.out.println("3. Department");
                        System.out.println("4. Designation");
                        System.out.println("5. Salary");
                        System.out.println("6. Join Date");
                        System.out.print("Enter choice: ");
                        int fieldChoice=sc.nextInt();
                        sc.nextLine();                                                              
                        java.util.List<model.Employee>allEmployees=dao.viewEmployees(con);      // Fetch existing employee details
                        model.Employee found=null;
                        for(model.Employee employee:allEmployees){
                            if(employee.getId()==upId){
                                found=employee;
                                break;
                            }
                        }
                        if(found==null){
                            System.out.println("No employee found with ID: " + upId);
                            break;
                        }
                        String newName=found.getName();
                        int newAge=found.getAge();
                        String newDept=found.getDepartment();
                        String newDesig=found.getDesignation();
                        double newSalary=found.getSalary();
                        String newJoinDate=found.getJoinDate();
                        switch(fieldChoice){
                            case 1:
                                System.out.print("Enter new Name: ");
                                newName=sc.nextLine();
                                break;
                            case 2:
                                System.out.print("Enter New Age: ");
                                newAge=sc.nextInt();
                                sc.nextLine();
                                break;
                            case 3:
                                System.out.print("Enter New Department: ");
                                newDept=sc.nextLine();
                                break;
                            case 4:
                                System.out.print("Enter New Designation: ");
                                newDesig=sc.nextLine();
                                break;
                            case 5:
                                System.out.print("Enter New Salary: ");
                                newSalary=sc.nextDouble();
                                sc.nextLine();
                                if (newSalary<=0) {
                                    System.out.println("Salary must be greater than 0.");
                                    break;
                                }
                                break;
                            case 6:
                                System.out.print("Enter new Join Date (YYYY-MM-DD): ");
                                newJoinDate=sc.nextLine();
                                break;
                            default:
                                System.out.println("Invalid field choice!");
                                break;
                        }
                        model.Employee upEmp=new model.Employee(upId,newName,newAge,newDept,newDesig,newSalary,newJoinDate);
                        dao.updateEmployee(upEmp,con);
                        break;
                    case 4:                                                        //Delete Employee
                        System.out.print("Enter ID of employee to delete: ");
                        int delId=sc.nextInt();
                        dao.DeleteEmployee(delId,con);
                        System.out.println("Employee deleted!");
                        break;
                    case 5:
                        running=false;
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        sc.close();
    }
}

