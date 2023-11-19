package com.example.pnuemonia_detection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDao {
    public boolean login(String email, String password) {
        String query = "SELECT * FROM pneumonia.doctors WHERE email = ? AND password = ?";
        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If a row is returned, login successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
