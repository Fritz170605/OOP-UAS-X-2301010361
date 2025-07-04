package ujian;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class dbkoneksi {
    static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_HOST = "jdbc:mysql://localhost:3308/smarstock"; 
    static String DB_USER = "root";
    static String DB_PASSWORD = ""; // 

    public static Connection koneksi() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_HOST, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}

