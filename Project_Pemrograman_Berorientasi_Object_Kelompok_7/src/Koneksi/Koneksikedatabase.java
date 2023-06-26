package Koneksi;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksikedatabase 
{
    private static Connection koneksi_db = null;
    private static MysqlDataSource dbgomarket;
    
    public static Connection ambilKoneksiDatabase()
    {
        dbgomarket = new MysqlDataSource();
        dbgomarket.setDatabaseName("db_gomarket");
        dbgomarket.setPassword("");
        dbgomarket.setUser("root");
        
        try
        {
            koneksi_db = dbgomarket.getConnection();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal, Silahkan Cek Koneksi Terlebih Dahulu !" + e.getMessage(), "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        }
        
        return koneksi_db;
    }
       
        
}
