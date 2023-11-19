package com.example.pnuemonia_detection;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDao {
    public boolean login(String email, String enteredPassword) {
        String query = "SELECT password FROM pneumonia.patients WHERE email = ?";
        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDb = resultSet.getString("password");

                    // Check if the entered password matches the hashed password
                    return BCrypt.checkpw(enteredPassword, hashedPasswordFromDb);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
