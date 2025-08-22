package DB;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection{
    public static Connection gConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee_DataBase","root","Monisha@123");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}