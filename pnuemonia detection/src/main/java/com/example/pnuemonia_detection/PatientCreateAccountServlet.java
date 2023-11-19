package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/PatientCreateAccountServlet")
public class PatientCreateAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String description = request.getParameter("description");
        String googleDriveLink = request.getParameter("google_drive_link");
        int doctorId = Integer.parseInt(request.getParameter("doctor_id"));

        // Validate inputs if necessary

        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO patients (nom, prenom, email, password, description, google_drive_link, doctor_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, googleDriveLink);
            preparedStatement.setInt(7, doctorId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("patient-dashboard.jsp"); // Redirect to patient dashboard or another page
            } else {
                response.sendRedirect("create-account.jsp?error=1"); // Redirect back with an error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("create-account.jsp?error=1");
        }
    }
}