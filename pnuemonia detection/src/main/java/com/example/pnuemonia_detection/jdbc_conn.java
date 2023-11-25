package com.example.pnuemonia_detection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class jdbc_conn {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pneumonia";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}


