package com.example.pnuemonia_detection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UpdateAIModelServlet", value = "/UpdateAIModelServlet")
public class UpdateAIModelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String aiModelValue = request.getParameter("aiModel");

        // Convert the aiModelValue to 1 for "yes" and 0 for "no"
        int modelValue = "1".equals(aiModelValue) ? 1 : 0;
        System.out.println(modelValue);
        // Update the AI-Model value in the database
        updateAIModel(email, modelValue);

        // Redirect back to the patient information page
        response.sendRedirect(request.getContextPath() + "/DoctorPatientInfoServlet?email=" + email);
    }

    private void updateAIModel(String email, int modelValue) {
        // Implement the logic to update the AI-Model value in the database
        // Use the email to identify the patient and update the 'model_val' column
        // Use your jdbc_conn class to get a database connection

        String url = "jdbc:mysql://localhost:3306/pneumonia";
        String username = "root";
        String password = "root";

        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE pneumonia.patients SET model_val = ? WHERE email = ?")) {

            statement.setInt(1, modelValue);
            statement.setString(2, email);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        }
    }
}
