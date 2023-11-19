package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet(name = "logingpatient", value = "/logingpatient")
public class logingpatient extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate inputs if necessary

        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM patients WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (((ResultSet) resultSet).next()) {
                // Patient credentials are correct, set session attributes or perform other actions as needed
                HttpSession session = request.getSession();
                session.setAttribute("patientId", resultSet.getInt("patient_id"));
                session.setAttribute("patientEmail", email);

                response.sendRedirect("patient-dashboard.jsp"); // Redirect to patient dashboard or another page
            } else {
                response.sendRedirect("login.jsp?error=1"); // Redirect back with an error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");
        }
    }
}