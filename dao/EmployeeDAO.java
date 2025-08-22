package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
public class EmployeeDAO {
    private static final int Id=0;
    public void addEmployees(Employee e,Connection con){
        String sql="INSERT INTO Employees(Id,Name,Age,Department,Designation,Salary,Join_Date) VALUES(?,?,?,?,?,?,?)";
    try{
     PreparedStatement pst=con.prepareStatement(sql);
     pst.setInt(1,e.getId());
     pst.setString(2, e.getName());
     pst.setInt(3,e.getAge());
     pst.setString(4,e.getDepartment());
     pst.setString(5,e.getDesignation());
     pst.setDouble(6,e.getSalary());
     pst.setDate(7, java.sql.Date.valueOf(e.getJoinDate()));
     pst.executeUpdate();
    }
    catch (SQLException sq){
        sq.printStackTrace();
    }
}

    public List<Employee> viewEmployees(Connection con){
        List<Employee> employees=new ArrayList<>();
        String sql="SELECT *FROM EMPLOYEES";
        try{
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                Employee e=new Employee(rs.getInt("Id"),
                                        rs.getString("Name"),
                                        rs.getInt("Age"),
                                        rs.getString("Department"),
                                        rs.getString("designation"),
                                        rs.getDouble("salary"),
                                        rs.getDate("join_date").toString());
                employees.add(e);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    public void updateEmployee(Employee e,Connection con){
        String sql="UPDATE EMPLOYEES SET Name=?,Age=?,Department=?,Designation=?,Salary=?,Join_Date=? WHERE Id=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,e.getName());
            ps.setInt(2,e.getAge());
            ps.setString(3,e.getDepartment());
            ps.setString(4,e.getDesignation());
            ps.setDouble(5,e.getSalary());
            ps.setDate(6,Date.valueOf(e.getJoinDate()));
            ps.setInt(7,e.getId());
            int rows=ps.executeUpdate();
            if (rows>0) {
                System.out.println("Employee updated successfully!");
            } 
            else {
                System.out.println("No employee found with ID: "+e.getId());
            }
        }
            catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void DeleteEmployee(int delId,Connection con){
        String sql="DELETE FROM EMPLOYEES WHERE ID=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1, delId);
            int rows=ps.executeUpdate();
                if (rows>0) {
                System.out.println("Employee deleted successfully!");
            } 
            else{
                    System.out.println("No employee found with ID: " +delId);
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
    }
