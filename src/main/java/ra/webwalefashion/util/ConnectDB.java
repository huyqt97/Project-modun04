package ra.webwalefashion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/wale_fashion";
    private static String USER = "root";
    private static String PASSWORD = "01685647312";
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception e) {
            e.printStackTrace();

        }
        return conn;
    }
    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}