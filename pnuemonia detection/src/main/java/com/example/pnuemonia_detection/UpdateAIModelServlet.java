package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "UpdateAIModelServlet", value = "/UpdateAIModelServlet")
public class UpdateAIModelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        int modelValue = "yes".equalsIgnoreCase(request.getParameter("aiModel")) ? 1 : 0;

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
        String password = "hamza";

        try {
            Connection connection = jdbc_conn.getConnection();

            String sql = "UPDATE pneumonia.patients SET model_val = ? WHERE email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, modelValue);
                statement.setString(2, email);
                statement.executeUpdate();
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        }
    }
}
