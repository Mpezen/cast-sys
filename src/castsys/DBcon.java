package castsys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {

    public static Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cast-sys", "root", "");   
            // walang pass

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}