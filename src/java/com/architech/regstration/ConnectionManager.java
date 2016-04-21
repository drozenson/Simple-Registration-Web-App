package com.architech.regstration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.org.objectweb.asm.commons.Method;

public class ConnectionManager {

    private static ConnectionManager instance = null;

    private final String USERNAME = ConfigManager.getInstance().process("Registration.properties").getProperty("username");
    // private final String USERNAME = "root";
    // private final String PASSWORD = "Password123";
    // private final String CONN_STRING = "jdbc:mysql://localhost:3306/architech

    private final String PASSWORD = ConfigManager.getInstance().process("Registration.properties").getProperty("password");

    private final String CONN_STRING = ConfigManager.getInstance().process("Registration.properties").getProperty("connstring");

    private final String DRIVER =  ConfigManager.getInstance().process("Registration.properties").getProperty("driver");
   
    private Connection conn = null;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private boolean openConnection() {
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: unable to load driver class!");
                System.exit(1);
            }
            //   DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try {
                System.out.println("trying to read the bundle");

            } catch (java.util.MissingResourceException e) {
                System.out.println("The specified file does not exist");
            }

            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public Connection getConnection() {
        if (conn == null) {
            if (openConnection()) {
                System.out.println("Connection Opened");
                return conn;
            } else {
                return null;
            }
        }
        return conn;
    }

    public void close() {
        System.out.println("Closing Connection");
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn = null;
    }
}
