package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/PatientCreateAccountServlet")
public class PatientCreateAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String plainTextPassword = request.getParameter("password"); // Get plain-text password
        String description = request.getParameter("description");
        String googleDriveLink = request.getParameter("google_drive_link");
        int doctorId = Integer.parseInt(request.getParameter("doctor_id"));

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

        // Check if the email already exists
        if (isEmailExists(email)) {
            request.setAttribute("emailError", "Email already exists!");
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "create_account" +
                            ".jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Retrieve selected symptoms from the hidden field
        String selectedSymptoms = request.getParameter("selectedSymptoms");
        System.out.println(selectedSymptoms);

        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO patients (nom, prenom, email, password, description, google_drive_link, doctor_id, symptoms) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, hashedPassword); // Store the hashed password
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, googleDriveLink);
            preparedStatement.setInt(7, doctorId);
            preparedStatement.setString(8, selectedSymptoms);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("patient.jsp");
            } else {
                response.sendRedirect("create-account.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("create-account.jsp?error=1");
        }
    }

    private boolean isEmailExists(String email) {
        String query = "SELECT email FROM patients WHERE email = ?";
        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
