package concurrentserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class Authenticator {

    static String url = "jdbc:mysql://localhost:3306/projectdata?zeroDateTimeBehavior=convertToNull";
    static Connection conn;
    static Statement stat;

    static {
        try {
            conn = DriverManager.getConnection(url,"root","root");
            stat = conn.createStatement();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Connectivity Failed.");
        }
    }

    public static boolean authenticate(String Username, String Password) {
        ResultSet rs;
        try {
            rs = stat.executeQuery("SELECT * FROM login");
            while (rs.next()) {
                if (rs.getString("uname").equals(Username)) {
                    if (rs.getString("password").equals(Password)) {
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in Result Set Obtaining.");
        }
        return false;
    }
}
