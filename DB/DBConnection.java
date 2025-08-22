package DB;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection{
    public static Connection gConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/Employee_DataBase";
            String user="root";
            String password=System.getenv("DB_PASSWORD");
            con=DriverManager.getConnection(url, user, password);
            }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
