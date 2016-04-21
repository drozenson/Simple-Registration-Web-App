package com.architech.regstration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbManager {

    private static Connection conn = ConnectionManager.getInstance().getConnection();
   private static final String DRIVER = ConfigManager.getInstance().process("Registration.properties").getProperty("driver");
   

    public static void Insert(GetsSets set) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        

        String sql = "INSERT INTO registration (username,password) VALUES(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, set.getUsername());
        pstmt.setString(2, set.getPassword());
        pstmt.executeUpdate();
        //ConnectionManager.getInstance().close();
    }

    public static int checkUser(GetsSets get) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        String sql = "SELECT COUNT(*) FROM registration WHERE username=? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, get.getUsername());
        ResultSet rs = pstmt.executeQuery();

        int count = 0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        //ConnectionManager.getInstance().close();
        return count;

    }
     public static void close() throws ClassNotFoundException, SQLException {
         ConnectionManager.getInstance().close();
     }

}
