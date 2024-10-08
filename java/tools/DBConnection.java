package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static Connection connection;
    
    public DBConnection() {
        try {
            String pilote = "com.mysql.cj.jdbc.Driver";
            String connetion = "jdbc:mysql://localhost/jWear?useSSL=false&serverTimezone=";
            String DBlogin = "root";
            String DBpassword = "root";
            Class.forName(pilote);
            connection = DriverManager.getConnection(connetion
                    + TimeZone.getDefault().getID(), DBlogin, DBpassword);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static Connection getConnection() {
        return connection;
    }
}