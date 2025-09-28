package model;
public class Employee{
    private int Id;
    private String Name;
    private int Age;
    private String Department;
    private String Designation;
    private double Salary;
    private String joinDate;
    public Employee(){}
    public Employee(int Id,String Name,int Age,String Department,String Designation,double Salary,String joinDate){
        this.Id=Id;
        this.Name=Name;
        this.Age=Age;
        this.Department=Department;
        this.Designation=Designation;
        this.Salary=Salary;
        this.joinDate=joinDate;
}
    public int getId(){ 
        return Id;
    }
    public String getName(){ 
        return Name; 
    }
    public int getAge(){ 
        return Age; 
    }
    public String getDepartment(){ 
        return Department; 
    }
    public String getDesignation(){ 
        return Designation; 
    }
    public double getSalary(){ 
        return Salary; 
    }
    public String getJoinDate(){ 
        return joinDate; 
    }
}

